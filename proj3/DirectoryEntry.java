public class DirectoryEntry {
    String number;
    String name;
    DirectoryEntry(String name, String number){
        this.name = name;
        this.number = number;
    }
    public String toString(){
        return name + "," + number;
    }
}