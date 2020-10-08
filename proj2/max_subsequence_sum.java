import java.util.StringTokenizer;
import java.io.*;

class max_subsequence_sum {
    public static void main(String[] args) {
        try {
            // Open the input file and split it into tokens
            BufferedReader reader = new BufferedReader(new FileReader("nums.in"));
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), ",");

            // Convert each token into an integer and add it to the array
            int len = tokenizer.countTokens();
            int numList[] = new int[len];
            for (int i = 0; i < len; i++) {
                numList[i] = Integer.valueOf(tokenizer.nextToken());
            }
            System.out.println(algorithm1(numList));

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
    
}