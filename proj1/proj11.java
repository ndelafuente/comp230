/**
 * proj11.java
 * 
 * This program takes in a string and a list of values. It then usees each of those 
 * values to encrypt and decode the string that was inputed.
 * 
 * Authors: Dillan Lopez and Nicolas de la Fuente
 * Updated: September 9, 2020
 */

import java.util.Scanner;

public class proj11{
    public static int[] keyValues; //array of the values the user enters
    public static int numberOfKeyValues; //number of values the user wishes to enter

    public static void main(String[] args) {
        Scanner scanInts = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);
        Scanner scanQuit = new Scanner(System.in);
        String runProgram = "y"; //variable to keep track of whether the user wants to run again
        
        while(runProgram.charAt(0) == 'y'){
            // variable to keep track of whether the user wants to run again
            System.out.println("Enter the number of values you wish to use.");
            numberOfKeyValues = scanInts.nextInt();

            //creates an array the size of the value the user enter before and gets the individual values the user
            //wants to use
            keyValues = new int[numberOfKeyValues];
            System.out.println("Enter individual values sepereated by spaces.");
            int i = 0;
            while(i < numberOfKeyValues) {
                keyValues [i] = scanInts.nextInt();
                i++;
            }

            //gets the string the user wants to decrypt
            System.out.println("Enter the message you wish to encrypt:");
            String phrase =  scanStr.nextLine();

            //returns the encrypted message
            String encryptedPhrase = encrypt(phrase);
            System.out.println("Your encrypted message is: "+ encryptedPhrase);

            //returns the decrypted message
            String decryptedPhrase = decrypt(encryptedPhrase);
            System.out.println("Your decrypted message is: "+ decryptedPhrase);

            //Prompts the user if they would like to run again
            System.out.println("Do you want to run the program again y or n?");
            runProgram =  scanQuit.nextLine();

        }
        scanQuit.close();
        scanInts.close();
        scanStr.close();
    }

    //method that takes in a string and retunrs it as an encrypted message
    public static String encrypt(String phrase){
        String encryptedMessage = "";
        for(int i = 0; i < phrase.length(); i++){
            int c = (int) phrase.charAt(i) + keyValues[i % numberOfKeyValues];
            char character = (char) c;
            encryptedMessage += character;
        }
        return encryptedMessage;
        }

    //method takes in a encypted message and returns it as a decrypted string
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