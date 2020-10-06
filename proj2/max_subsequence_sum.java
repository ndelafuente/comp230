import java.util.System;
import java.io.BufferedReader;
class max_subsequence_sum {
    public static void main(String[] args) {
        try {
            // Open the input file and split it into tokens
            BufferedReader reader = new BufferedReader(new FileReader("nums.in"));
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), ',');

            // Convert each token into an integer and add it to the array
            int len = tokenizer.countTokens();
            int numList[] = new int[len];
            for (int i = 0; i < len; i++) {
                numList[i] = Integer.valueOf(tokenizer.nextToken());
                System.out.println(numList[i]);
            }


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

    public static int[] algorithm1(int arr[]) {

    }
}