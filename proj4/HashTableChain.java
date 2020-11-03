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
    private int numKeys;
    private int index;
    private static final int CAPACITY = 63;
    private static final double LOAD_THRESHOLD = 5; // average size of linked list (should be less than 5?)
    
    public HashTableChain() {
        table = new LinkedList[CAPACITY];
        numKeys = 0;
        index = 65;
    }

    public HashTableChain(int cap) {
        table = new LinkedList[cap];
        numKeys = 0;
        index = 65;
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
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0) 
            index += table.length;

        if (table[index] == null)
            table[index] = new LinkedList<Entry<K, V>>();
        
        for (Entry<K,V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key)) {
                V oldValue = nextItem.getValue();
                nextItem.setValue(value);
                return oldValue;
            }
        }

        table[index].add(new Entry<K, V>(key, value));
        numKeys++;
        
        // TODO if the load factor exceeds the LOAD_THRESHOLD,  Rehash.

        return null;
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
                numKeys--;
                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                return nextItem.getValue();
            }
        }

        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return numKeys;
    }
}
