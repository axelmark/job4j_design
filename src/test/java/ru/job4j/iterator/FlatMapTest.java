package ru.job4j.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMapTest {

    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next()).isEqualTo(1);
        assertThat(flat.next()).isEqualTo(2);
        assertThat(flat.next()).isEqualTo(3);
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next()).isEqualTo(1);
        assertThat(flat.next()).isEqualTo(2);
        assertThat(flat.next()).isEqualTo(3);
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.hasNext()).isTrue();
        assertThat(flat.hasNext()).isTrue();
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next()).isEqualTo(1);
        assertThat(flat.hasNext()).isFalse();
    }

    @Test
    public void whenEmpty() {
        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            Iterator<Iterator<Object>> data = List.of(
                    List.of().iterator()
            ).iterator();
            FlatMap<Object> flat = new FlatMap<>(data);
            flat.next();
        });
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        assertThat(flat.hasNext()).isTrue();
        assertThat(1).isEqualTo(flat.next());
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        assertThat(flat.hasNext()).isFalse();
    }
}