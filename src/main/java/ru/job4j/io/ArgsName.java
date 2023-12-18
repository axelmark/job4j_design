package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            String[] temp = s.split("=", 2);
            String key = temp[0].replaceAll("-", "").trim();
            String value = temp[1];
            this.values.putIfAbsent(key, value);
        }
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String s : args) {
            if (!s.contains("=")) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain an equal sign", s));
            }
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not start with a '-' character", s));
            }
            String[] temp = s.split("=", 2);
            if (temp[0].replaceAll("-", "").trim().isBlank()) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain a key", s));
            }
            if (temp[1].isBlank()) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain a value", s));
            }
        }
    }

    public static ArgsName of(String[] args) {
        validate(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}