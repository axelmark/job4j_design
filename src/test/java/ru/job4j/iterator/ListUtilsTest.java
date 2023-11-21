package ru.job4j.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).isEqualTo(Arrays.asList(1, 2, 3));
    }

    @Test
    public void whenAddBeforeWithInvalidIndex() {
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
            ListUtils.addBefore(input, 3, 2);
        });
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input).isEqualTo(Arrays.asList(0, 1, 2, 3));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.removeIf(input, value -> value % 2 == 0);
        assertThat(input).isEqualTo(Arrays.asList(1));
    }

    @Test
    public void whenreplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.replaceIf(input, value -> value > 1, 77);
        assertThat(input).isEqualTo(Arrays.asList(0, 1, 77));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 2));
        ListUtils.removeAll(input, elements);
        assertThat(input).isEqualTo(Arrays.asList(1));
    }
}