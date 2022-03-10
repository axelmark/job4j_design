package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        T rsl;
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                out.push(in.pop());
            }
            rsl = out.pop();
            size--;
            for (int i = 0; i < size; i++) {
                in.push(out.pop());
            }
        } else {
            throw new NoSuchElementException();
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
