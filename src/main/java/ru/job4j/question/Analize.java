package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);

        for (User user : previous) {
            for (User cur : current) {
                if (user.equals(cur)) {
                    rsl.setChanged(0);
                } else {
                    rsl.setChanged(1);
                }
            }
        }
        return rsl;
    }
}