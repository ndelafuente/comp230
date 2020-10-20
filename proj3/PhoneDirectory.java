import java.util.ArrayList;
import java.util.List;

public class PhoneDirectory extends DirectoryEntry{
    PhoneDirectory(String name, String number) {
        super(name, number);
        // TODO Auto-generated constructor stub
    }

    private List<DirectoryEntry> theDirectory = new ArrayList<>();

    public DirectoryEntry addOrChangeEntry(String name, String number) {
    // add an entry to directory or change an existing entry; return the old number or null if it is a // new entry
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));
        DirectoryEntry dE;
        if (index != -1) {
            dE = theDirectory.get(index);
            theDirectory.set(index, new DirectoryEntry(name, number));
            return dE;
        }

        else{
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

    public displayAllEntries(){
    // display all entries in a nice and readable format
        int len = theDirectory.size();
        for(int i = 0; i < len; i ++){
            System.out.println(theDirectory.get(i));
        }
    }
    
}