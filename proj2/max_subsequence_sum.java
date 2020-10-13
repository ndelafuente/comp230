import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

class max_subsequence_sum {
    public static void main(String[] args) {
        try {
            // Testing prototol
            if (args.length == 3 && args[0].equals("-t")) {
                int n = Integer.valueOf(args[1]);
                int iter = Integer.valueOf(args[2]);

                int testList[] = new int[n];
                Random rand = new Random();
                for (int len = 1; len <= n; len++) {
                    testList[len] = rand.nextInt() % 201 + 100;
                    test_mss_algorithms(testList, len, iter);
                }
            }
            // Default protocol
            else if (args.length == 0) {
                do {
                    // Read in the list of numbers
                    int[] numList = null;
                    while (numList == null) {
                        numList = readNums(); // returns null if there was an error
                    }
                    // Test each algorithm on the list
                    System.out.println("Testing the algorithms with default 1000 iterations");
                    test_mss_algorithms(numList, numList.length, 1000);

                } while (runAgain());
            }
            else printUsage();
        }
        catch (IOException ex) { ex.getMessage(); }
        catch (NumberFormatException ex) { printUsage(); }
    }

    /**
     * Test the run times of each maximum subsequence sum algorithm
     * 
     * @param arr The array to be tested on
     * @param n The length of the array
     * @param iter The number of iterations to perform
     * @throws IOException
     */
    public static void test_mss_algorithms(int arr[], int n, int iter) throws IOException {
        PrintWriter outFile = new PrintWriter(new FileWriter("run_times.txt"));
        outFile.println("n,i,algo1,algo2,algo3,algo4");
        long totalRunTimes[] = {0, 0, 0, 0};

        // Test the run time for each algorithm and write it to the output file
        for (int i = 0; i < iter; i++) {
            outFile.printf("\n%d,%d,", n, i);
            for (int algo = 1; algo <= 4; algo++) {
                long runTime = getRuntime(algo, arr, n);
                outFile.printf("%d,", runTime);
            }
        }
        outFile.close();

        // Calculate and print the average run time for each algorithm
        System.out.printf("Average run times for n = %d\n", n);
        for (int algo = 0; algo < 4; algo++) {
            double averageRunTime = totalRunTimes[algo] / iter;
            System.out.printf("Algorithm %d: %f\n", algo+1, averageRunTime);
        }
        System.out.println("The full data set can be found in run_times.txt");
    }

    /**
     * Get the run time for a specific algorithm
     * 
     * @param algoNum The algorithm number
     * @param arr The array to run the algorithm on
     * @param n The length of the array
     * @return The run time
     */
    public static long getRuntime(int algoNum, int arr[], int n) {
        // Get the current system time in nano seconds
        long startTime = System.nanoTime();
        switch (algoNum) {
            case 1:
                algorithm1(arr);
                break;
            case 2:
                algorithm2(arr);
                break;
            case 3:
                algorithm3(arr, 0, n);
                break;
            case 4:
                algorithm4(arr);
                break;
        }
        // Calculate and return the run time for the algorithm
        return System.nanoTime() - startTime;
        
    }

    /**
     * An algorithm to calculate the maximum subsequence sum in O(n^3) time
     * 
     * @param arr The array
     * @return The maximum subsequence sum
     */
    public static int algorithm1(int arr[]) {
        int maxSum = 0;
        int n = arr.length;

        // Pass over each element in the array
        for(int i = 0; i < n; i++){
            // Pass over each element from i to the end
            for(int j = i; j < n; j++){
                // Calculate the sum of the elements from i to j
                int sum = 0;
                for(int k = i; k < j+1; k++){
                    sum += arr[k];
                }

                // If sum is greater than maxSum, update maxSum
                if (sum > maxSum)
                    maxSum = sum;
            }
        }
        return maxSum;
    }

    /**
     * An algorithm to calculate the maximum subsequence sum in O(n^2) time
     * 
     * @param arr The array
     * @return The maximum subsequence sum
     */
    public static int algorithm2(int arr[]){
        int maxSum = 0, n = arr.length;

        // Pass over each element in the array
        for(int i = 0; i < n; i++) {
            // Calculate the sum of the elements from i to the end
            int sum = 0;
            for(int j = i; j <n; j++){
               sum += arr[j];
               // If sum is greater than maxSum, update maxSum
               if(sum > maxSum){
                   maxSum = sum;
               } 
            }
        }
        return maxSum;
    }
    
    /**
     * A helper function for algorithm 3
     * 
     * @param arr The array
     * @return The maximum subsequence sum
     */
    static int algorithm3(int arr[]) {
        return algorithm3(arr, 0, arr.length - 1);
    }

    /**
     * An algorithm to calculate the maximum subsequence sum in O(nlog(n)) time
     * 
     * @param arr The array
     * @param start The starting position
     * @param stop The ending position
     * @return The maximum subsequence sum
     */
    static int algorithm3(int arr[], int start, int stop) { 
        // Base Case: Only one element 
        if (start == stop) 
            return arr[start]; 
    
        // Find middle point 
        int m = (start + stop)/2; 
    
        // Return the max of the left and right halves and the max bounded sum
        return Math.max(Math.max(algorithm3(arr, start, m), 
                        algorithm3(arr, m+1, stop)), 
                        maxBoundedSum(arr, start, m, stop)); 
    }

    /**
     * Calculate the maximum subsequence sum that includes position m
     * 
     * @param arr The array
     * @param start The starting position
     * @param m The middle
     * @param stop The stopping position
     * @return The max bounded sum
     */
    static int maxBoundedSum(int arr[], int start, int m, int stop) { 
        // Check the elements on left of mid.
        int sum = 0; 
        int left_sum = 0; 
        for (int i = m; i >= start; i--) 
        { 
            sum = sum + arr[i]; 
            if (sum > left_sum) 
            left_sum = sum; 
        } 
  
        // Check the elements on right of mid 
        sum = 0; 
        int right_sum = 0; 
        for (int i = m + 1; i <= stop; i++) 
        { 
            sum = sum + arr[i]; 
            if (sum > right_sum) 
            right_sum = sum; 
        } 
  
        // Return sum of elements on left and right of mid 
        return Math.max(left_sum + right_sum, Math.max(left_sum, right_sum)); 
    } 

    /**
     * An algorithm to calculate the maximum subsequence sum in O(n) time
     * 
     * @param arr The array
     * @return The maximum subsequence sum
     */
    public static int algorithm4(int arr[]) {
        int maxSum = 0, sum = 0, n = arr.length;

        // Pass over each element in the årray
        for (int j = 0; j < n; j++) {
            // Add the element to  sum
            sum += arr[j];

            // If sum is greater than maxSum, update maxSum
            if (sum > maxSum)
                maxSum = sum;
            // Else reset sum to 0
            else if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }

    /**
     * Read in a list of numbers from a file that is specified by the user
     * 
     * @return The array of numbers or null if there was an error
     */
    public static int[] readNums() {
        try {
            // Read in the file name
            Scanner scan = new Scanner(System.in);
            System.out.print("Please enter the input file name: ");
            String filename = scan.nextLine();

            // Open the input file and split it into tokens
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), ",");

            // Convert each token into an integer and add it to the array
            int len = tokenizer.countTokens();
            int numList[] = new int[len];
            for (int i = 0; i < len; i++) {
                numList[i] = Integer.valueOf(tokenizer.nextToken());
            }
            return numList;
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Asks the user if they want to run the program again
     * 
     * @return true if they do, false if they do not
     */
    public static boolean runAgain() {
        // Ask the user if they want to run again
        Scanner scan = new Scanner(System.in);
		System.out.print("Do you want to run the program again (y/n): ");
        String runProgram = scan.nextLine();

        // Validate the user input
        while ( !runProgram.equalsIgnoreCase("y") && !runProgram.equalsIgnoreCase("n") )
        {
			System.out.println("Invalid input.");
            System.out.print("Do you want to run the program again (y/n): ");
            runProgram =  scan.nextLine();
        }

        return runProgram.equalsIgnoreCase("y");
    }

    /**
     * Print the correct usage of the program
     */
    public static void printUsage() {
        System.out.println("Usage: java max_subsequence_sum [-t <n> <iterations>]");
    }
}
