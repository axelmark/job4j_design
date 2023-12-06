package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {

            for (String string : read.lines().toList()) {
                if (string.isEmpty() || string.startsWith("#")) {
                    continue;
                }
                if (string.contains("=")) {
                    String[] st = string.split("=", 2);
                    if (st[0].isEmpty() || st[1].isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    values.put(st[0], st[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/pair_with_comments.properties"));
    }
}