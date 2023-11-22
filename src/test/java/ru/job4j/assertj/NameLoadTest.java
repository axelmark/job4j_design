package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }
    @Test
    void checkValidateIfNotContainTheSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(()-> nameLoad.parse( "string"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain the symbol '='", "string");
    }

    @Test
    void checkValidateIfNameStartsWith() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(()-> nameLoad.parse( "=string"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key", "=string");
    }

    @Test
    void checkValidateIfNameIndexOf() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(()-> nameLoad.parse( "string="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value", "string=");
    }
}