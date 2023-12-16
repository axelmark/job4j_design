package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> elements = new HashMap<>();

    public Map<FileProperty, List<String>> getElements() {
        return elements;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty entries = new FileProperty(file.toFile().length(), file.toFile().getName());
        String path = file.toFile().getAbsolutePath();
        List<String> tempList = new ArrayList<>();
        if (elements.containsKey(entries)) {
            tempList = elements.get(entries);
        }
        tempList.add(path);
        elements.putIfAbsent(entries, tempList);
        return super.visitFile(file, attrs);
    }
}