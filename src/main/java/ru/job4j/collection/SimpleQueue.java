package ru.job4j.collection;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private int sizeOut;

    public T poll() {
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
            sizeOut++;
        }
        sizeOut--;
        size = 0;
        return out.pop();
    }

    public void push(T value) {
        if (sizeOut > 0) {
            for (int i = 0; i < sizeOut; i++) {
                in.push(out.pop());
                size++;
            }
        }
        in.push(value);
        size++;
    }
}
