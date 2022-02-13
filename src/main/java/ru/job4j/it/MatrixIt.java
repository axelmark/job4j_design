package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (!(data[row].length > 0)) {
            if (data.length - row != 1) {
                row++;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][column];
        if (data[row].length > 1 && data[row].length - column != 1) {
            column++;
        } else {
            column = 0;
            row++;
        }
        return rsl;
    }
}