import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Compress {
    private static final int END_OF_FILE = 0xFFFF;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String fileName = "";
        do {
            // Open the file specified at the command line
            if (args.length == 1) {
                fileName = args[0];
                args = null;
            }
            // Or ask the user to specify a filename
            else {
                System.out.print("Please enter a file name: " );
                fileName = scan.nextLine();
            }
            String compressedFileName = fileName + ".zzz";

            // Compress the file
            try { compressFile(fileName, compressedFileName); }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }

        // Ask the user if they want to run again    
        } while (runAgain(scan));

        scan.close();
    }

    /**
     * Compress a text file
     * 
     * @param fileName The location of the file to compress
     * @param compressedFileName The location to store the compressed file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void compressFile(String fileName, String compressedFileName) throws IOException {
        long startTime = System.currentTimeMillis(); // start the run time clock
        
        // Open the input and output files
        FileInputStream file = new FileInputStream(fileName);
        FileOutputStream compressedOutputStream = new FileOutputStream(compressedFileName);
        
        // Create the hash table
        HashTableChain<String, Character> hashTable = new HashTableChain<>();

        // Initialize the hash table for all possible input values
        char symbol = 0;
        while (symbol < 128) {
            hashTable.put(symbol + "", symbol);
            symbol++;
        }
        
        // Read each character from the files
        String p = "";
        char c = 0;
        while ((c = (char) file.read()) != END_OF_FILE) {
            // Find the longest prefix that is in the hash table
            if (hashTable.get(p + c) != null) {
                p += c;
            // If just p is in the hash table
            } else {
                compressedOutputStream.write(hashTable.get(p));
                hashTable.put(p + c, symbol);

                p = "" + c;
                symbol++;
            }
        }
        // Compress the remaining †ext
        compressedOutputStream.write(hashTable.get(p));

        // Close the files
        file.close();
        compressedOutputStream.close();

        // Calculate the run time in seconds
        double runTime = (System.currentTimeMillis() - startTime) / 1000;

        // Get the size of the file before and after compression
        double fileSize = Files.size(Paths.get(fileName)) / 1000;
        double compressedSize = Files.size(Paths.get(compressedFileName)) / 1000;

        // Write the log information to the log file
        String logFileName = compressedFileName + ".log";
        PrintWriter logFile = new PrintWriter(logFileName);
        logFile.println("Compression of " + fileName);
        logFile.printf("Compressed from %f kilobytes to %f kilobytes\n", fileSize, compressedSize);
        logFile.printf("Compression took %f seconds\n", runTime);
        logFile.printf("The dictionary contains %d total entries\n", hashTable.size());
        logFile.printf("The table was rehashed %d times", hashTable.rehashCount);
        logFile.close();
    }
    
    /**
     * Asks the user if they want to run the program again
     * 
     * @param scan The Scanner for user input
     * @return true if they do, false if they do not
     */
    public static boolean runAgain(Scanner scan) {
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