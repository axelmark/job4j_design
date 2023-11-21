package ru.job4j.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import ru.job4j.list.List;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class SimpleArrayListTest {
    static List<Integer> list;

    @BeforeAll
    public static void init() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        assertThat(3).isEqualTo(list.size());
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        assertThat(Integer.valueOf(1)).isEqualTo(list.get(0));
    }

    @Test
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        });
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        assertThat(3).isEqualTo(list.size());
        assertThat(Integer.valueOf(2)).isEqualTo(list.remove(1));
        assertThat(2).isEqualTo(list.size());
    }

    @Test
    public void whenRemoveByIncorrectIndexThenGetException() {
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(5);
        });
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        assertThat(Integer.valueOf(1)).isEqualTo(list.get(0));
        assertThat(Integer.valueOf(3)).isEqualTo(list.get(1));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        assertThat(2).isEqualTo(list.size());
        assertThat(list.get(0)).isNull();
        assertThat(list.get(1)).isNull();
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        assertThat(Integer.valueOf(2)).isEqualTo(list.set(1, 22));
        assertThat(3).isEqualTo(list.size());
    }

    @Test
    public void whenSetByIncorrectIndexThenGetException() {
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(5, 22);
        });
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        assertThat(list.iterator().hasNext()).isFalse();
    }

    @Test
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            list = new SimpleArrayList<>(5);
            list.iterator().next();
        });
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        assertThat(Integer.valueOf(1)).isEqualTo(list.iterator().next());
        assertThat(Integer.valueOf(1)).isEqualTo(list.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(Integer.valueOf(1)).isEqualTo(iterator.next());
        assertThat(iterator.hasNext()).isTrue();
        assertThat(Integer.valueOf(2)).isEqualTo(iterator.next());
        assertThat(iterator.hasNext()).isTrue();
        assertThat(Integer.valueOf(3)).isEqualTo(iterator.next());
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> list.add(v));
    }

    @Test
    public void whenAddAfterGetIteratorThenMustBeException() {
        ConcurrentModificationException thrown = Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            Iterator<Integer> iterator = list.iterator();
            list.add(4);
            iterator.next();
        });
    }

    @Test
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        ConcurrentModificationException thrown = Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            Iterator<Integer> iterator = list.iterator();
            list.add(0);
            iterator.next();
        });
    }
}