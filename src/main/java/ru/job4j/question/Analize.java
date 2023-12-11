package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = new HashMap<>();
        Info rsl = new Info(0, 0, 0);

        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!map.containsKey(user.getId()) && !map.containsValue(user.getName())) {
                rsl.setAdded(+1);
            }
            if (map.containsKey(user.getId()) && !map.containsValue(user.getName())) {
                rsl.setChanged(+1);
            }
            map.remove(user.getId());
        }
        rsl.setDeleted(map.size());
        return rsl;
    }
}