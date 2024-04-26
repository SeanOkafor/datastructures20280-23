package project20280.priorityqueue;


import project20280.interfaces.Entry;
import project20280.interfaces.PriorityQueue;

import java.util.Comparator;

/**
 * An abstract base class to ease the implementation of the PriorityQueue interface.
 * <p>
 * The base class provides four means of support:
 * 1) It defines a PQEntry class as a concrete implementation of the
 * entry interface
 * 2) It provides an instance variable for a general Comparator and a
 * protected method, compare(a, b), that makes use of the comparator.
 * 3) It provides a boolean checkKey method that verifies that a given key
 * is appropriate for use with the comparator
 * 4) It provides an isEmpty implementation based upon the abstract size() method.
 */
public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
    //---------------- nested PQEntry class ----------------

    protected static abstract class PQEntry<K, V> implements Entry<K, V> {
        private K k;  // key
        private V v;  // value

        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }

        // methods of the Entry interface
        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            k = key;
        }

        protected void setValue(V value) {
            v = value;
        }

        public String toString() {
            //return "<" + k + ", " + v + ">";
            return String.valueOf(k);
        }
    } //----------- end of nested PQEntry class -----------

    private final Comparator<K> comp;


    protected AbstractPriorityQueue(Comparator<K> c) {
        comp = c;
    }


    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }


    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0);  // see if key can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
