package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private int sizeOut;

    public T poll() {
        T rsl;
        if (size != 0 && sizeOut == 0) {
            for (int i = 0; i < size; i++) {
                out.push(in.pop());
                sizeOut++;
            }
            rsl = out.pop();
            sizeOut--;
            size = 0;
            for (int i = 0; i < sizeOut; i++) {
                in.push(out.pop());
                size++;
                sizeOut = 0;
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
