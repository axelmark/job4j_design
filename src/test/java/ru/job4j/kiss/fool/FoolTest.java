package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FoolTest {
    @Test
    public void isFizzBuzz() {
        Fool.Answer answer = new Fool.Answer(15);
        var res = answer.label();
        assertThat(res).isEqualTo("FizzBuzz");
    }
}