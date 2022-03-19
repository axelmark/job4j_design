package ru.job4j.map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import org.junit.Test;
import ru.job4j.User;

public class SimpleMapTest {

    SimpleMap<User, String> simpleMap = new SimpleMap();
    User vasya = new User("Vasya", 2, new GregorianCalendar(1999, 1, 1));

    @Test
    public void get() {
        simpleMap.put(vasya, "Value of Vasya");
        var rsl = simpleMap.get(vasya);
        assertThat(rsl, is("Value of Vasya"));
    }

    @Test
    public void remove() {
        simpleMap.put(vasya, "Value of Vasya");
        var rsl = simpleMap.remove(vasya);
        assertTrue(rsl);
    }
}