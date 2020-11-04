import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Compress {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                // Open the file specified at the command line
                File file = new File(args[0]);
                long fileSize = file.length();
                BufferedReader buffer = new BufferedReader(new FileReader(file));

                // Create the compressed file to be written to
                File compressedFile = new File(args[0] + ".zzz");
                PrintWriter compressedFileWriter = new PrintWriter(compressedFile);

                // Create the log file to be written to
                File logFile = new File((args[0] + ".zzz.log"));
                PrintWriter logWriter = new PrintWriter(logFile);

                // Create the hash table
                HashTableChain<Integer, String> hashTable = new HashTableChain<>();

                // // Read each character from the files
                // while(buffer.ready()) {
                //     String dataCluster = "" + (char) buffer.read();
                //     // While key in hashTable
                //     while (hashTable.get(dataCluster) != null && buffer.ready()) {
                //         // Add next character to key
                //         dataCluster += (char) buffer.read();
                //     }

                //     // Remove the last character 
                //     String lastChar = dataCluster.substring(dataCluster.length()-1);
                //     dataCluster = dataCluster.substring(0, dataCluster.length());
                    
                //     hashTable.put(lastChar);
                //     String symbol = hashTable.get(dataCluster);
                //     compressedFileWriter.write(symbol);
                // }

                compressedFileWriter.close();
                long compressedSize = compressedFile.length();

                logWriter.println("Compression of " + file.getName());
                logWriter.printf("Compressed from %d to %d", fileSize, compressedSize);
                logWriter.close();

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Usage: java Compress <filename>");
        }
    }
}