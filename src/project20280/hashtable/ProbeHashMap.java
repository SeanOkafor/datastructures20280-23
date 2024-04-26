package project20280.hashtable;

import project20280.interfaces.Entry;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null) {
        @Override
        public int compareTo(Entry<K, V> o) {
            return 0;
        }
    };

    public ProbeHashMap() {
        super();
    }
    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    protected void createTable() {
        table = new MapEntry[capacity];
    }

    int findSlot(int h, K k) {
        int avail = -1;
        int j = h;
        do {
            if (isAvailable(j)) {
                if (avail == -1) {
                    avail = j;
                }
                if (table[j] == null) {
                    break;
                }
            }
            j = (j + 1) % capacity;
        } while (j != h);
        if (avail >= 0) {
            return avail;
        } else {
            return j;
        }
    }

    @Override
    protected V bucketGet(int h, K k) {
        // TODO
        int j = findSlot(h, k);
        if (j < 0) return null; // no match found
        return table[j].getValue( );
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);
        if (j >= 0) { // this key has an existing entry
            return table[j].setValue(v);
        } else {
            j = (j + capacity) % capacity; // make sure j is within array bounds
            table[j] = new MapEntry<K, V>(k, v) {
                @Override
                public int compareTo(Entry<K, V> o) {
                    return 0;
                }
            };
            n++;
            return null;
        }
    }

    @Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null; // nothing to remove
        V answer = table[j].getValue( );
        table[j] = DEFUNCT; // mark this slot as deactivated
        n = n - 1 ;
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return null;
    }
}
