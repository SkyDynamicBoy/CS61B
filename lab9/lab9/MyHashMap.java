package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("call get() with null key");
        }
        int keyhash = hash(key);
        ArrayMap<K, V> targetBucket = buckets[keyhash];
        if (targetBucket.containsKey(key)) {
            return targetBucket.get(key);
        }
        return null;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("call put() with null key");
        }
        if (value == null) {
            remove(key);
            return;
        }
        int keyhash = hash(key);
        ArrayMap<K, V> targetBucket = buckets[keyhash];
        if (!targetBucket.containsKey(key)) {
            size++;
        }
        targetBucket.put(key, value);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        for (ArrayMap<K, V> b : buckets) {
            for (K key : b) {
                keyset.add(key);
            }
        }
        return keyset;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("call remove() with null key");
        }
        int keyhash = hash(key);
        ArrayMap<K, V> targetBucket = buckets[keyhash];
        size--;
        return targetBucket.remove(key);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("call remove() with null key");
        }
        int keyhash = hash(key);
        ArrayMap<K, V> targetBucket = buckets[keyhash];
        V removeValue = targetBucket.get(key);
        if (removeValue == value) {
            size--;
            return targetBucket.remove(key);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private int leftKeys;
        private Iterator<K> currentIterator;
        private int currentIndex;

        public KeyIterator() {
            this.leftKeys = size;
            this.currentIndex = 0;
            this.currentIterator = buckets[currentIndex].iterator();
        }

        public boolean hasNext() {
            return leftKeys != 0;
        }

        public K next() {
            if (this.leftKeys == 0) {
                throw new IllegalCallerException("no next elements");
            }
            if (currentIterator.hasNext()) {
                this.leftKeys--;
                return currentIterator.next();
            } else {
                currentIndex++;
                currentIterator = buckets[currentIndex].iterator();
                return this.next();
            }
        }

    }

}
