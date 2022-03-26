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
    public int threshold = (int) (capacity * LOAD_FACTOR);
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count != threshold) {
            int hashKey = hash(key);
            int index = indexFor(hashKey);
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        int hashKey = hash(key);
        int index = indexFor(hashKey);
        return table[index].key.equals(key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int hashKey = hash(key);
        int index = indexFor(hashKey);
        if (table[index] != null && table[index].equals(key)) {
            table[index] = null;
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

    private void expand() {
        capacity *= 2;
        threshold = (int) (capacity * LOAD_FACTOR);
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (var item : table) {
            if (item != null) {
                int hashKey = hash(item.key);
                int index = indexFor(hashKey);
                newTable[index] = item;
            }
        }
        table = newTable;
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