package ru.job4j.question;

import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);

/*  id есть в current, но нет в previous - значит добавлен.
    id есть в previous, но нет current значит - удален.
    id есть в обеих коллекциях, но при этом имеет разное имя - изменен. */

        for (User pre : previous) {
            for (User cur : current) {
                if (!current.contains(pre)) {
                    rsl.setDeleted(+1);
                }
                if (!previous.contains(cur)) {
                    rsl.setAdded(+1);
                }
                if (pre.getId() == cur.getId()) {
                    if (!pre.getName().equals(cur.getName())) {
                        rsl.setChanged(+1);
                    }
                }
            }
        }
        return rsl;
    }

}