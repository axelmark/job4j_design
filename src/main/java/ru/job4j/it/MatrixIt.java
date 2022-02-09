package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private final int[] row = new int[10];
    private final int[] column = new int[10];
    int count = 0;
    int begin = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != 0) {
                    row[count] = i;
                    column[count] = j;
                    count++;
                }
            }
        }
        return count != 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row[begin]][column[begin]];
        begin++;
        return rsl;
    }
}