package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenIllegalArgumentException() {
        String path = "data/pair_with_comment.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenIllegalArgumentExceptionEmptyString() {
        String path = "data/pair_with_empty_strings.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }
}