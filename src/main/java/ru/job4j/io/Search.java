package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        String[] arguments = new String[]{".", ".java"};
        validate(arguments);
        Path start = Path.of(arguments[0]);
        search(start, p -> p.toFile().getName().endsWith(arguments[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String... args) {
        if (!Files.exists(Path.of(args[0])) && Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Directory doesn't exist");
        }
        if (!args[1].matches(".[a-zA-Z-_0-9]*")) {
            throw new IllegalArgumentException("The file extension must match the template '.a-z A-Z 0-9' ");
        }
    }
}