import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Decompress {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                String compressedFileName = args[0];
                FileInputStream compressedFile = new FileInputStream(compressedFileName);

                if (compressedFileName.indexOf(".zzz") != (compressedFileName.length() - 4)) {
                    System.out.println("Invalid file format. Must have the extension (.zzz)");
                    return;
                }

                String decompressedFileName = compressedFileName.replace(".zzz", "");
                FileOutputStream decompressedFile = new FileOutputStream(decompressedFileName);

                String logFileName = decompressedFileName + ".log";
                PrintWriter logFile = new PrintWriter(logFileName);

                // Create the hash table
                HashTableChain<String, Integer> hashTable = new HashTableChain<>();

                // Initialize the hash table for all possible input values
                for (Character c = 0; c < 128; c++) {
                    hashTable.put(c.toString(), Integer.valueOf(c));
                }


            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
