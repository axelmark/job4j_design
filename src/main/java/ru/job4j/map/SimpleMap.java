package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    public int i = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count < capacity) {
            table[expand(key)] = new MapEntry<>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        return table[expand(key)].key != null ? table[expand(key)].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (table[expand(key)] != null) {
            table[expand(key)] = null;
            count--;
            rsl = true;
        }
        return rsl;
    }

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private int expand(K key) {
        int hashKey = hash(key);
        return indexFor(hashKey);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (i < capacity && table[i] == null) {
                    i++;
                }
                return i < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[i++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}