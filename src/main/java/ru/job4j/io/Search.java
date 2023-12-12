package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        String startDir = ".";
        String fileExt = ".java";
        validate(startDir, fileExt);
        Path start = Path.of(startDir);
        search(start, p -> p.toFile().getName().endsWith(fileExt)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String startDir, String fileExt) {
        if (startDir.isEmpty()) {
            throw new IllegalArgumentException("Root folder cannot be null.");
        }
        if (fileExt.isEmpty()) {
            throw new IllegalArgumentException("file extension cannot be null");
        }
    }
}