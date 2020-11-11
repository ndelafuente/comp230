import java.util.*;

public class HashTableChain<K, V> implements KWHashMap<K, V> {
    public static class Entry<K, V> {
        private final K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public V getValue() {
            return value;
        }
        public K getKey() {
            return key;
        }
        public V setValue(V val) {
            V oldValue = value;
            value = val;
            return oldValue;
        }
    }
    
    private LinkedList<Entry<K, V>>[] table;
    private double loadFactor = 0;
    private int capacity = 257, numKeys = 0, numEntries = 0;
    public int rehashCount = 0;
    private static final double LOAD_THRESHOLD = .2; // average size of linked list (should be less than 5?)
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public HashTableChain() {
        table = new LinkedList[capacity];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public HashTableChain(int cap) {
        table = new LinkedList[cap];
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null;
        for (Entry<K,V> nextItem : table[index])
            if(nextItem.getKey().equals(key))
                return nextItem.getValue();
        return null;
    }

    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0) 
            index += table.length;

        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
            numKeys++;
        }
        
        V oldValue = null;
        for (Entry<K,V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key)) {
                oldValue = nextItem.getValue();
                nextItem.setValue(value);
                break;
            }
        }
        if (oldValue == null) {
            table[index].add(new Entry<K, V>(key, value));
            numEntries++;
        }
        
        // Update the load factor
        loadFactor = numKeys / numEntries;
        if (loadFactor > LOAD_THRESHOLD) {
            rehash();
        }

        return oldValue;
    }

    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null;
        
        for (Entry<K,V> nextItem : table[index]) {
            if(nextItem.getKey().equals(key)) {
                table[index].remove(nextItem);
                numEntries--;
                if (table[index].isEmpty()) {
                    table[index] = null;
                    numKeys--;
                }
                return nextItem.getValue();
            }
        }

        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void rehash() {
        capacity = capacity * 2 + 1;
        while (!isPrime(capacity)) {
            capacity++;
        }
        
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];

        numKeys = 0;
        for (int i = 0; i < capacity; i++) {
            if (this.get(i) != null) {
                for (Entry<K,V> nextItem : table[i]) {
                    K key = nextItem.getKey();
                    V value = nextItem.getValue();

                    int index = key.hashCode() % newTable.length;
                    if (index < 0) 
                        index += newTable.length;

                    if (newTable[index] == null) {
                        newTable[index] = new LinkedList<Entry<K, V>>();
                        numKeys++;
                    }

                    newTable[index].add(new Entry<K, V>(key, value));
                }
            }
        }

        table = newTable;
        rehashCount++;
    }
    
    private static boolean isPrime(int n) { 
        // Corner cases 
        if (n <= 1) return false; 
        if (n <= 3) return true; 
      
        // Check whether n is divisible by 2 or 3
        if (n % 2 == 0 || n % 3 == 0) return false; 
      
        // Check every 6th number up to sqrt(n)
        for (int i = 5; i * i <= n; i += 6) 
            if (n % i == 0 || n % (i + 2) == 0) 
            return false; 
      
        return true; 
    } 

    @Override
    public int size() {
        return numEntries;
    }

    public String toString() {
        String retStr = "";
        for (int i = 0; i < capacity; i++) {
            retStr += i + ": ";
            for (Entry<K,V> nextItem : table[i]) {
                retStr += "(" + nextItem.getKey() + ", " + nextItem.getValue() + ") ";
            }
            retStr += '\n';
        }
        return retStr;
    }
}
