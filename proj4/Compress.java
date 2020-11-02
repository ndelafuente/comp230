import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Compress {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                // Read the contents of the file specified at the command line
                File file = new File(args[0]);
                long fileSize = file.length();
                BufferedReader buffer = new BufferedReader(new FileReader(file));

                // Create the hash table
                HashTableChain<Integer, String> hashTable = new HashTableChain<>();

                // Read each character from the file
                while(buffer.ready()) {
                    Integer character = buffer.read();
                    hashTable.put(character);
                }

                HashTableChain x;


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