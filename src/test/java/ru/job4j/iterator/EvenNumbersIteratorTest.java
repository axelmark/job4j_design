package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIteratorTest {

    private static Iterator<Integer> it;

    @BeforeAll
    public static void setUp() {
        it = new EvenNumbersIterator(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    public void shouldReturnEvenNumbersSequentially() {
//        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(2);
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(4);
            assertThat(it.hasNext()).isTrue();
            assertThat(it.next()).isEqualTo(6);
            assertThat(it.hasNext()).isFalse();
//            it.next();
//        });
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.next()).isEqualTo(6);
    }

    @Test
    public void shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1});
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[]{2, 4, 6});
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(6);
        assertThat(it.hasNext()).isFalse();
    }
}