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
        String running = "y"; // keep track of whether the user wants to run again
        Scanner s = new Scanner(System.in);
        while (running.equalsIgnoreCase("y")) {
        	String inFilename, outFilename;
        	try {
        		// Get the input and output file names
	            System.out.print("Enter the input file name: ");
    	        inFilename = s.nextLine();
        	    System.out.print("Enter the output file name: ");
           		outFilename = s.nextLine();
				System.out.println();

				// Get user input for a file, and remove the duplicate entries
            	removeDuplicates(inFilename, outFilename);

				System.out.println("Successfully removed duplicates from '" + inFilename + "'!");
				System.out.println("Updated data can be found in '" + outFilename + "'\n");

          		// Prompt the user if they would like to run again
				while ( !(running.equalsIgnoreCase("y") || running.equalsIgnoreCase("n")) ) {
            		System.out.println("Do you want to run the program again y or n?");
            		running =  s.nextLine();
				}
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
			catch (IOException ex) {
           		ex.printStackTrace();
            	System.exit(1);
       		}

        } // end while loop
      	System.out.println("Goodbye!");
        s.close();
    } // end of main

	public static void removeDuplicates(String inFilename, String outFilename) 
			throws FileNotFoundException,  EOFException, NumberFormatException, IOException {
		// Create the buffers from the file names
        inStream = new BufferedReader(new FileReader(inFilename));
        outStream = new BufferedWriter(new FileWriter(outFilename));
                    
        // Read each line until the end of the file
        String line = inStream.readLine();
        while (line != null) {
        	int num = Integer.valueOf(line); // throws exception if not an int
						
			// Write the number to the file
        	outStream.write(Integer.toString(num) + '\n');

			// Read the next line
        	String nextLine = inStream.readLine();

        	// If the next number is a duplicate
            if (Integer.valueOf(line) == Integer.valueOf(nextLine)) {
            	// Skip to the line after it
                line = inStream.readLine();
            }
			else
				line = nextLine;
		}
		// Close the stream to save the changes to the file
        outStream.close();
	}
}
