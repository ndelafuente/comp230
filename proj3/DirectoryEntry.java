public class DirectoryEntry {
    String number;
    String name;
    DirectoryEntry(String name, String number){
        name = this.name;
        number = this.number;
    }
    public String toString(){
        return name.toString() + "," + number.toString();
    }
}