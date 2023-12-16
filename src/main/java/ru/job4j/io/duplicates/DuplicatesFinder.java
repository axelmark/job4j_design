package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor d = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), d);

        var duplicates = d.getElements();
        for (Map.Entry<FileProperty, List<String>> s : duplicates.entrySet()) {
            if (s.getValue().size() > 1) {
                System.out.printf("Name: %s; Size: %s; Found duplicates: %s; Paths: %s\n",
                        s.getKey().getName(), s.getKey().getSize(), s.getValue().size(), s.getValue());
            }
        }
    }
}