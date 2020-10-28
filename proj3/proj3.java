import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

class proj3 {
    public static void main(String[] args) {
        System.out.println("\n\nWelcome to the Cool Phonebook Manager.\n");
        Scanner scan = new Scanner(System.in);
        
        // Initialize an empty phone directory
        PhoneDirectory phoneBook = new PhoneDirectory("name", "number");
        String name = "", number = "", filename = "";
        DirectoryEntry dE = null;
        
        while (true) {
            int option = getOption(scan);
            switch (option) {
                case 0:
                    return;
                case 1:
                    // Read in the file name
                    System.out.print("Please enter the input file name: ");
                    filename = scan.nextLine();
                    phoneBook = readFile(filename);
                    break;
                case 2: // Add or change an entry in the directory
                    System.out.print("Enter the name: ");
                    name = scan.nextLine();
                    System.out.print("Enter the number: ");
                    number = scan.nextLine();

                    dE = phoneBook.addOrChangeEntry(name, number);
                    if (dE == null)
                        System.out.println("New entry added!");
                    else
                        System.out.printf("Existing entry <%s> was overwritten.\n", dE.toString());
                    break;
                case 3: // Remove an entry by name
                    System.out.print("Enter the name: ");
                    name = scan.nextLine();
                    phoneBook.removeEntry(name);
                    break;
                case 4: // Search for a entry by name
                    System.out.print("Enter the name: ");
                    name = scan.nextLine();
                    dE = phoneBook.searchEntry(name);
                    
                    if (dE != null)
                        System.out.printf("An entry was found <%s>\n", dE.toString());
                    else
                        System.out.printf("No entry with the name (%s) was found.\n", name);
                    break;
                case 5: // Display all directory entries
                    phoneBook.displayAllEntries();
                    break;
                case 6: // Save to file
                    try {
                        System.out.print("Enter the filename: ");
                        filename = scan.nextLine();
                        PrintWriter writer = new PrintWriter(new FileWriter(filename));
                        writer.print(phoneBook.toString());
                        writer.close();
                    }
                    catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
            }
        }
    }

    /**
     * Get the user's choice for the main menu
     * 
     * @param scan The scanner
     * @return The user's choice
     */
    public static int getOption(Scanner scan) {
        int OPT_MIN = 0, OPT_MAX = 6;
        System.out.println("\n                   Main Menu                  ");
        System.out.println(" -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
        System.out.println("| 0. Quit                                       |");
        System.out.println("| 1. Load a phone directory from a file         |");
        System.out.println("| 2. Add or change an entry                     |");
        System.out.println("| 3. Remove an entry                            |");
        System.out.println("| 4. Search for an entry                        |");
        System.out.println("| 5. Display all entries                        |");
        System.out.println("| 6. Save the current phone directory to a file |");
        System.out.println(" -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
        
        // Read in the option and ensure that it is valid
        return getInput(scan, "Enter the number of your choice: ", OPT_MIN, OPT_MAX);
    }

    /**
     * Get an integer input from the user, reprompt if outside of range
     * 
     * @param scan The scanner
     * @param msg The prompt
     * @param min The minimum input value
     * @param max The maximum input value
     * @return The input
     */
    public static int getInput(Scanner scan, String msg, int min, int max) {
        // Get the input and reprompt the user if it is out of the range
        int input = min - 1;
        while (input < min || input > max) {
            // Print the message
            System.out.print(msg);

            try {
                input = Integer.valueOf(scan.nextLine());
                if (input < min || input > max) { throw new NumberFormatException(); }
                System.out.println();
            }
            catch (NumberFormatException ex) {
                System.out.printf("Invalid input. Input must be an integer between %d and %d\n\n", min, max);
                input = min - 1;
            }
        }

        return input;
    }

    /**
     * Read in a list of numbers from a file that is specified by the user
     * 
     * @return The array of numbers or null if there was an error
     */
    public static PhoneDirectory readFile(String filename) {
        try {
            PhoneDirectory pD = new PhoneDirectory("", "");
            // Open the input file and split it into tokens
            Scanner reader = new Scanner(new File(filename));
            while (reader.hasNextLine()) {
                StringTokenizer tokenizer = new StringTokenizer(reader.nextLine(), ",");
                if (tokenizer.countTokens() == 2)
                    pD.addOrChangeEntry(tokenizer.nextToken(), tokenizer.nextToken());
                else
                    throw new InputMismatchException("Invalid phonebook formatting.");
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}

