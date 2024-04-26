package project20280.hashtable;

import project20280.interfaces.Entry;

import java.util.ArrayList;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    public ChainHashMap() {
        super();
    }

    public ChainHashMap(int cap) {
        super(cap);
    }

    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = new UnsortedTableMap[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
    }
    @Override
    protected V bucketGet(int h, K k) {
        // TODO
        UnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) return null;
        return bucket.get(k);
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            bucket = new UnsortedTableMap<>();
            table[h] = bucket;
        }
        int oldSize = bucket.size();
        V answer = bucket.put(k, v);
        n += (bucket.size() - oldSize); // size may have increased
        return answer;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            return null;
        }
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n -= (oldSize - bucket.size()); // size may have decreased
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {

        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (UnsortedTableMap<K, V> tm : table) {
            if (tm != null) {
                for (Entry<K, V> e : tm.entrySet()) {
                    entries.add(e);
                }
            }
        }
        return entries;
    }

    public String toString() {
        return entrySet().toString();
    }

    public static void main(String[] args) {
        ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
        m.put(1, "One");
        m.put(10, "Ten");
        m.put(11, "Eleven");
        m.put(20, "Twenty");

        System.out.println("m: " + m);

        m.remove(11);
        System.out.println("m: " + m);
    }
}
