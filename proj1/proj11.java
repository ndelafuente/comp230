
import java.util.Scanner;

public class proj11{
    public static int[] keyValues;
    public static int numberOfKeyValues;
    public static void main(String[] args) {
        
        Scanner scanInts = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);
        System.out.println("Enter the number of values you wish to use.");
        numberOfKeyValues = scanInts.nextInt();
        keyValues = new int[numberOfKeyValues];
        System.out.println("Enter individual values sepereated by spaces.");

        int i = 0;
        while(i < numberOfKeyValues) {
            keyValues [i] = scanInts.nextInt();
            i++;
        }

        System.out.println("Enter the message you wish to encrypt:");
        String phrase =  scanStr.nextLine();

        String encryptedPhrase = encrypt(phrase);
        System.out.println("Your encrypted message is: "+ encryptedPhrase);

        String decryptedPhrase = decrypt(encryptedPhrase);
        System.out.println("Your decrypted message is: "+ decryptedPhrase);
        scanInts.close();
        scanStr.close();
    }

    public static String encrypt(String phrase){
        String encryptedMessage = "";
        for(int i = 0; i < phrase.length(); i++){
            int c = (int) phrase.charAt(i) + keyValues[i % numberOfKeyValues];
            char character = (char) c;
            encryptedMessage += character;
        }
        return encryptedMessage;
        }

    public static String decrypt(String phrase){
        String decryptedMessage = "";
        for(int i = 0; i < phrase.length(); i++){
            int c = (int) phrase.charAt(i) - keyValues[i % numberOfKeyValues];
            char character = (char) c;
            decryptedMessage += character;
        }
        return decryptedMessage;
        }

}