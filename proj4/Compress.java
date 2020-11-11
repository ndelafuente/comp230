import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Compress {
    private static int END_OF_FILE = 0xFFFF;
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                // Open the file specified at the command line
                String fileName = args[0];
                FileInputStream file = new FileInputStream(fileName);

                // Create the compressed file to be written to
                String compressedFileName = fileName + ".zzz";
                FileOutputStream compressedOutputStream = new FileOutputStream(compressedFileName);

                // Create the log file to be written to
                String logFileName = compressedFileName + ".log";
                PrintWriter logFile = new PrintWriter(logFileName);

                // Create the hash table
                HashTableChain<String, Integer> hashTable = new HashTableChain<>();

                // Initialize the hash table for all possible input values
                for (Character c = 0; c < 128; c++) {
                    hashTable.put(c.toString(), Integer.valueOf(c));
                }
                
                //System.out.println(hashTable.toString());

                // Read each character from the files
                long startTime = System.currentTimeMillis();
                String p = "";
                char c = 0;
                int symbol = 128;
                while ((c = (char) file.read()) != END_OF_FILE) {
                    //System.out.printf("p + c: %s %c, %d\t c: %d\n", p , c, hashTable.get(p + c), hashTable.get(c));
                    
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
                double runTime = (System.currentTimeMillis() - startTime) / 1000;

                // Compress the remaining 
                compressedOutputStream.write(hashTable.get(p));

                compressedOutputStream.close();

                // Get the size of the file before and after compression
                long fileSize = Files.size(Paths.get(fileName));
                long compressedSize = Files.size(Paths.get(compressedFileName));

                logFile.println("Compression of " + fileName);
                logFile.printf("Compressed from %dkb to %dkb\n", fileSize, compressedSize);
                logFile.printf("Compression took %f seconds\n", runTime);
                logFile.printf("The dictionary contains %d total entries\n", hashTable.size());
                logFile.printf("The table was rehashed %d times", hashTable.rehashCount);
                logFile.close();

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Usage: java Compress <filename>");
        }
    }
}