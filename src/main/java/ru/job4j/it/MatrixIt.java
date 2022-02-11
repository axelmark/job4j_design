package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    public String row = "";
    private String column = "";
    private int point = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data.length != 0 && row.length() == 0) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i].length != 0) {
                        row += i;
                        column += j;
                    }
                }
            }
        }
        return point < row.length();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row.charAt(point) - '0'][column.charAt(point) - '0'];
        point++;
        return rsl;
    }
}