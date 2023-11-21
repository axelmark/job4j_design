package ru.job4j.map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.GregorianCalendar;

import ru.job4j.User;

public class SimpleMapTest {

    SimpleMap<User, String> simpleMap = new SimpleMap();
    User vasya = new User("Vasya", 2, new GregorianCalendar(1999, 1, 1));

    @Test
    public void get() {
        simpleMap.put(vasya, "Value of Vasya");
        var rsl = simpleMap.get(vasya);
        assertThat(rsl).isEqualTo("Value of Vasya");
    }

    @Test
    public void remove() {
        simpleMap.put(vasya, "Value of Vasya");
        var rsl = simpleMap.remove(vasya);
        assertThat(rsl).isTrue();
    }
}