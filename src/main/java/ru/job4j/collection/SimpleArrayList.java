package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import ru.job4j.list.List;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            changeLength();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T edited = container[index];
        container[index] = newValue;
        return edited;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removed = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        modCount++;
        this.size--;
        return removed;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    private void changeLength() {
        container = Arrays.copyOf(container, size * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            public int i = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return i < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[i++];
            }
        };
    }
}