import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

class max_subsequence_sum {
    public static void main(String[] args) {
        do {
            int[] numList = null;
            while (numList == null) {
                numList = readNums();
            }
            System.out.println(algorithmRec(numList,0, numList.length));
            //test_mss_algorithms(numList);
        } while (runAgain());

    }

    public static void test_mss_algorithms(int numList[]) {
        int iter = 1;
        long startTime = 0;
        double rtAlgo1 = 0, rtAlgo2 = 0, rtAlgoR = 0, rtAlgo4 = 0;

        for (int i = 0; i < iter; i++) {
            startTime = System.nanoTime();
            algorithm1(numList);
            rtAlgo1 += System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            algorithm2(numList);
            rtAlgo2 += System.nanoTime() - startTime;
            
            // startTime = System.nanoTime();
            // algorithmRec(numList, 0, numList.length);
            // rtAlgoR += System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            algorithm4(numList);
            rtAlgo4 += System.nanoTime() - startTime;
            
        }

        System.out.println("Algorithm 1: " + rtAlgo1);
        System.out.println("Algorithm 2: " + rtAlgo2);
        System.out.println("Algorithm R: " + rtAlgoR);
        System.out.println("Algorithm 4: " + rtAlgo4);

    }

    //O(n^3)
    public static int algorithm1(int arr[]) {
        int maxSum = 0;
        int n = arr.length;
        for(int i =0; i < n; i++){
            for(int j = i; j < n; j++){
                int sum = 0;
                for(int k = i; k < j+1; k++){
                    sum += arr[k];
                }
                if (sum > maxSum)
                    maxSum = sum;
            }
        }
        return maxSum;
    }

    //O(n^2)
    public static int algorithm2(int arr[]){
        int maxSum = 0;
        int n = arr.length;
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = i; j <n; j++){
               sum += arr[j];
               if(sum > maxSum){
                   maxSum = sum;
               } 
            }
        }
        return maxSum;
    }

    // O(n log(n))
    public static int algorithmRec(int arr[], int start, int end) {
        /*
            ¤ Partition the array into 2 equal halves – left and right
            ¤ Compute maxLeftSum and maxRightSum, recursively
            ¤ Compute maxLeftBoundSum and maxRightBoundSum
            ¤ Return max (maxLeftSum, maxRightSum, maxLeftBoundSum+maxRightBoundSum)
        */
        int n = end - start;
        int mid = (int) (n / 2);

        if (n <= 0) {
            return 0;
        }
        else {
            int maxLeftSum = algorithmRec(arr, start, mid);
            int maxRightSum = algorithmRec(arr, mid + 1, end);
            int maxLeftBoundSum = 0, maxRightBoundSum = 0;
            // int leftBoundSum = 0, rightBoundSum = 0;

            // [a, b, c, d, e, f]
            for (int j = 0; j < mid; j++) {
                maxLeftBoundSum += arr[mid - j];
                maxRightBoundSum += arr[mid + j];
                // if (leftBoundSum > maxLeftBoundSum)
                //     maxLeftBoundSum = leftBoundSum;
    
                // if (rightBoundSum > maxLeftBoundSum)
                //     maxLeftBoundSum = leftBoundSum;
            }

            return Math.max(Math.max(maxLeftSum, maxRightSum), 
                    (maxLeftBoundSum + maxRightBoundSum));
        }
    }

    // O(n)
    public static int algorithm4(int arr[]) {
        int maxSum = 0, sum = 0;
        int n = arr.length;
        for (int j = 0; j < n; j++) {
            sum += arr[j];
            if (sum > maxSum)
                maxSum = sum;
            else if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }

    public static int[] readNums() {
        try {
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
        Scanner scan = new Scanner(System.in);
		System.out.print("Do you want to run the program again (y/n): ");
        String runProgram = scan.nextLine();
        while ( !runProgram.equalsIgnoreCase("y") && !runProgram.equalsIgnoreCase("n") )
        {
			System.out.println("Invalid input.");
            System.out.print("Do you want to run the program again (y/n): ");
            runProgram =  scan.nextLine();
        }

        return runProgram.equalsIgnoreCase("y");
    }
}
