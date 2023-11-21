package ru.job4j.it;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;

public class BackwardArrayItTest {

    @Test
    public void whenReadSequenceAndHasNextThenFalse() {
        BackwardArrayIt it = new BackwardArrayIt(new int[]{1, 2, 3, 4});
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void whenMultiCallHasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenNextFromEmpty() {
        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            BackwardArrayIt it = new BackwardArrayIt(
                    new int[]{}
            );
            it.next();
        });
    }
}