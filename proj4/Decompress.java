import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;

public class Decompress {
    private static final int END_OF_FILE = 0xFFFF;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String compressedFileName = "";
        do {
            // Open the file specified at the command line
            if (args.length == 1) {
                compressedFileName = args[0];
                args = null;
            }
            // Or ask the user to specify a filename
            else {
                System.out.print("Please enter a file name: " );
                compressedFileName = scan.nextLine();
            }
            // Open the compressed input file
            if (compressedFileName.indexOf(".zzz") != (compressedFileName.length() - 4)) {
                System.out.println("Invalid file format. Must have the extension (.zzz)");
                return;
            }

            // Open the decompressed output file
            String decompressedFileName = compressedFileName.replace(".zzz", ""); 

            try { decompressFile(compressedFileName, decompressedFileName); }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }

        // Ask the user if they want to run again    
        } while (runAgain(scan)); 
    }
    
    /**
     * Decompress a compressed file back into text
     * 
     * @param compressedFileName The location of the compresssed file
     * @param decompressedFileName The location to store the decompressed file
     * @throws IOException
     */
    public static void decompressFile(String compressedFileName, String decompressedFileName) 
            throws IOException {

        long startTime = System.currentTimeMillis(); // start the run time clock

        // Open the input and output files
        FileInputStream compressedFile = new FileInputStream(compressedFileName);
        PrintWriter decompressedFile = new PrintWriter(decompressedFileName);

        // Create the hash table
        HashTableChain<Character, String> hashTable = new HashTableChain<>();

        // Initialize the hash table for all possible input values
        char symbol = 0;
        while (symbol < 128) {
            hashTable.put(symbol, symbol + "");
            symbol++;
        }

        // Decompress the first symbol from the file
        char q = 0, p = (char) compressedFile.read();
        if (hashTable.get(p) != null) {
            decompressedFile.print(hashTable.get(p));
            q = p;
        } else {
            compressedFile.close();
            decompressedFile.close();
            throw new IOException("Invalid file format");
        }
        
        // Decompress the remaining symbols from the file
        while ((p = (char) compressedFile.read()) != END_OF_FILE) {
            if (hashTable.get(p) != null) {
                String textP = hashTable.get(p);
                decompressedFile.print(textP);
                hashTable.put(symbol, hashTable.get(q) + textP.charAt(0));
                symbol++;
            } else {
                String textQ = hashTable.get(q);
                decompressedFile.print(textQ + textQ.charAt(0));
                hashTable.put(p, textQ + textQ.charAt(0));
            }
            q = p;
        }
        double runTime = (System.currentTimeMillis() - startTime) / 1000;

        // Close the files
        compressedFile.close();
        decompressedFile.close();

        // Write the log information to the log file
        String logFileName = decompressedFileName + ".log";
        PrintWriter logFile = new PrintWriter(logFileName);
        logFile.println("Decompression of " + compressedFileName);
        logFile.printf("Decompression took %f seconds\n", runTime);
        logFile.printf("The table was doubled %d times", hashTable.rehashCount);
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
