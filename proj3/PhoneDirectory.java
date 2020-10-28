import java.util.ArrayList;
import java.util.List;

public class PhoneDirectory extends DirectoryEntry {
    private List<DirectoryEntry> theDirectory = new ArrayList<DirectoryEntry>();

    PhoneDirectory(String name, String number) {
        super(name,number);
        addOrChangeEntry(this.name, this.number);
    }


    public DirectoryEntry addOrChangeEntry(String name, String number) {
    // add an entry to directory or change an existing entry; return the old number or null if it is a // new entry
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

    public DirectoryEntry searchEntry(String name){
    // search the entry referenced by name; return the entry searched or null if there is no entry // for name
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));
        if (index != -1){
            return theDirectory.get(index);
        }
        else{
            return null;
        }
    }

    public DirectoryEntry removeEntry(String name){
    // remove the entry referenced by name; return the entry removed or null if there is no entry // for name
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));
        if(index != -1){
            DirectoryEntry dE = theDirectory.get(index);
            theDirectory.remove(index);
            return dE;
        }
        else{
            return null;
        }

    }

    public void displayAllEntries() {
    // display all entries in a nice and readable format
        System.out.println("Size: " + theDirectory.size());
        System.out.println(this.toString());
    }

    public String toString() {
        int len = theDirectory.size();
        String phoneBookStr = "";
        for (int i = 0; i < len; i++) {
            phoneBookStr += theDirectory.get(i).toString() + '\n';
        }
        return phoneBookStr;
    }
    
}