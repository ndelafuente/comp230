import java.util.ArrayList;
import java.util.List;

public class PhoneDirectory extends DirectoryEntry{
    private List<DirectoryEntry> theDirectory = new ArrayList<>();

    public String addOrChangeEntry(String name, String number){
    // add an entry to directory or change an existing entry; return the old number or null if it is a // new entry
    }

    public DirectoryEntry searchEntry(String name){
    // search the entry referenced by name; return the entry searched or null if there is no entry // for name
    }

    public DirectoryEntry removeEntry(String name){
    // remove the entry referenced by name; return the entry removed or null if there is no entry // for name
    }

    public displayAllEntries(){
    // display all entries in a nice and readable format
    }
    
}