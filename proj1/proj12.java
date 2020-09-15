/**
 * proj12.java
 * 
 * This program takes in a file of sorted integers and creates a new file where
 * any duplicate entries are removed.
 * 
 * Authors: Dillan Lopez and Nicolas de la Fuente
 * Updated: September 10, 2020
 */

import java.io.*;
import java.util.Scanner;

public class proj12 {
    public static BufferedReader inStream; // the input stream
    public static BufferedWriter outStream; // the output stream
    public static void main(String[] args) {
        char running = 'y'; // keep track of whether the user wants to run again
        Scanner s = new Scanner(System.in);
        try {
            while (running == 'y') {
                String inFilename, outFilename;
                try {
                    // Get the input and output file names
                    System.out.print("Enter the input file name: ");
                    inFilename = s.nextLine();
                    System.out.print("Enter the output file name: ");
                    outFilename = s.nextLine();
					System.out.println();

                    // Create the buffers from the file names
                    inStream = new BufferedReader(new FileReader(inFilename));
                    outStream = new BufferedWriter(new FileWriter(outFilename));
                    
					//TODO: Clean up this code (nextLine is unnecessary)
                    // Read each line until the end of the file
                    String line = inStream.readLine();
                    while (line != null) {
                        int num = Integer.valueOf(line); // throws exception if not an int
						// Write the number to the file
                        outStream.write(Integer.toString(num) + '\n');

                        String nextLine = inStream.readLine();
                        if(nextLine == null) break; // stop reading if there is no next line
                        
                        int nextNum = Integer.valueOf(nextLine);
                        // If the next number is a duplicate
                        if (num == nextNum) {
                            // Skip to the line after it
                            nextLine = inStream.readLine();
                        }
                        line = nextLine;
                    }
					System.out.println("Successfully removed duplicates from '" + inFilename + "'!");
					System.out.println("Updated data can be found in '" + outFilename + "'\n");
                    outStream.close();

                }
                catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (EOFException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (NumberFormatException ex) {
                    System.out.println("Error reading file: " + ex.getMessage());
                }
                finally {
                    // Close the streams to save the changes
                    inStream.close();
                    outStream.close();

                    // Prompt the user if they would like to run again
                    System.out.print("Would you like to run again? (y/n): ");
                    running = s.nextLine().charAt(0);

                }
            } // end of while loop
        } // end of try block
        catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
		System.out.println("Goodbye!");
        s.close();
    } // end of main
}
