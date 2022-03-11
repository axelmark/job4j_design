package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize;
    private int outSize;

    public T poll() {
        T rsl = null;
        if (inSize != 0) {
            if (outSize == 0) {
                while (inSize != 0) {
                    out.push(in.pop());
                    inSize--;
                    outSize++;
                }
            }
            rsl = out.pop();
            outSize--;
        } else {
            throw new NoSuchElementException();
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
