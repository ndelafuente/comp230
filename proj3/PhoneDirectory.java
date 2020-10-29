import java.util.ArrayList;
import java.util.List;

public class PhoneDirectory extends DirectoryEntry {
    private List<DirectoryEntry> theDirectory = new ArrayList<DirectoryEntry>();

    /**
     * Constructor for a PhoneDirectory object
     * 
     * @param name The name of the first entry
     * @param number The number of the first entry
     */
    PhoneDirectory(String name, String number) {
        super(name,number);
        addOrChangeEntry(this.name, this.number);
    }

    /**
     * Add an entry to directory or change an existing entry.
     *  
     * @param name The name
     * @param number The number
     * @return return the old number or null if it is a new entry
     */
    public DirectoryEntry addOrChangeEntry(String name, String number) {
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));
        
        DirectoryEntry dE;
        if (index != -1) {
            dE = theDirectory.get(index);
            theDirectory.set(index, new DirectoryEntry(name, number));
            return dE;
        }

        else {
            theDirectory.add(new DirectoryEntry(name, number));
            return null;
        }
    }

    /**
     * Search the directory for an entry
     * 
     * @param name The name to be searched
     * @return The entry if found or null if not
     */
    public DirectoryEntry searchEntry(String name) {
        // Get the index of the entry
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));
        System.out.println(index);
        if (index != -1) {
            return theDirectory.get(index);
        }
        else {
            return null;
        }
    }

    /**
     * Remove an entry from the directory
     * 
     * @param name The name of the entry to be removed
     * @return The entry that was removed or null if a matching entry was not found
     */
    public DirectoryEntry removeEntry(String name) {
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));
        if(index != -1) {
            DirectoryEntry dE = theDirectory.get(index);
            theDirectory.remove(index);
            return dE;
        }
        else {
            return null;
        }

    }

    /**
     * Display all the entries in the directory
     */
    public void displayAllEntries() {
    // display all entries in a nice and readable format
        System.out.println("Size: " + theDirectory.size());
        System.out.println(this.toString());
    }

    /**
     * Returns a string representation of the PhoneBook object
     * 
     * @return The string
     */
    public String toString() {
        int len = theDirectory.size();
        String phoneBookStr = "";
        for (int i = 0; i < len; i++) {
            phoneBookStr += theDirectory.get(i).toString() + '\n';
        }
        return phoneBookStr;
    }
    
}