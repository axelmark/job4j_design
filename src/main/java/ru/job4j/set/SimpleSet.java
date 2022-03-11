package ru.job4j.set;

import java.util.Iterator;
import java.util.Objects;
import ru.job4j.collection.SimpleArrayList;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean rsl = !contains(value);
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (var t : set) {
            if (Objects.equals(t, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}