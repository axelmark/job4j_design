package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        container[modCount++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T get(int index) {
        if (Objects.checkIndex(index, container.length) == index) {
            throw new IndexOutOfBoundsException();
        }

        return container[index];
    }

    @Override
    public int size() {
        return container.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    public static void main(String[] args) {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(2);
        list.add(1);
        list.add(2);

        System.out.println(list.get(1));
    }
}