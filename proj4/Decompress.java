import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Decompress {
    private static final int END_OF_FILE = 0xFFFF;

    public static void main(String[] args) {
        if (args.length == 1) {
            while (true) {
                try {
                    String compressedFileName = args[0];
                    FileInputStream compressedFile = new FileInputStream(compressedFileName);

                    if (compressedFileName.indexOf(".zzz") != (compressedFileName.length() - 4)) {
                        System.out.println("Invalid file format. Must have the extension (.zzz)");
                        return;
                    }

                    String decompressedFileName = compressedFileName.replace(".zzz", "");
                    PrintWriter decompressedFile = new PrintWriter(decompressedFileName);

                    String logFileName = decompressedFileName + ".log";
                    PrintWriter logFile = new PrintWriter(logFileName);

                    // Create the hash table
                    HashTableChain<Integer, String> hashTable = new HashTableChain<>();

                    // Initialize the hash table for all possible input values
                    for (Character c = 0; c < 128; c++) {
                        hashTable.put(Integer.valueOf(c), c.toString());
                    }

                    // Read each character from the files
                    long startTime = System.currentTimeMillis();
                    String q = "";
                    char p = (char) compressedFile.read();
                    if (hashTable.get(p) != null) {
                        decompressedFile.print(hashTable.get(p));
                    } else {
                        System.out.println("Invalid file format");
                        continue;
                    }
                    int symbol = 128;
                    while ((p = (char) compressedFile.read()) != END_OF_FILE) {
                        if (hashTable.get(p) != null) {
                            String textP = hashTable.get(p);
                            decompressedFile.print(textP);
                            symbol++;
                            hashTable.put(symbol, hashTable.get(q) + textP.charAt(0));
                        } else {
                            String textQ = hashTable.get(q);
                            decompressedFile.print(textQ + textQ.charAt(0));
                            hashTable.put((int) p, textQ + textQ.charAt(0));
                        }
                    }
                    double runTime = (System.currentTimeMillis() - startTime) / 1000;

                    logFile.println("Decompression of " + compressedFileName);
                    logFile.printf("Decompression took %f seconds\n", runTime);
                    logFile.printf("The table was doubled %d times", hashTable.rehashCount);
                    logFile.close();

                    if (!runAgain()) {
                        break;
                    }

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
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
