package ru.job4j;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Vasya", 2, new GregorianCalendar(1999, 1, 1));
        User user2 = new User("Vasya", 2, new GregorianCalendar(1999, 1, 1));

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
