public class DirectoryEntry {
    String number;
    String name;

    /**
     * Constructor for a Directory Entry object
     * 
     * @param name The name
     * @param number The number
     */
    DirectoryEntry(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Returns a string representation of the DirectoryEntry object
     * 
     * @return The string
     */
    public String toString() {
        return name + ", " + number;
    }

    /**
     * Indicates whether two DirectoryEntry objects are equivalent. 
     * Equivalence is determined by having the names.
     * 
     * @param obj The other DirectoryEntry
     * 
     * @return Whether the two objects are equal
     */
    public boolean equals(Object obj) {
        String otherName = ((DirectoryEntry) obj).name;
        return otherName.equalsIgnoreCase(this.name);
    }

}