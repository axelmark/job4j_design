package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashMap<FileProperty, List<String>> elements = new HashMap<>();

    public HashMap<FileProperty, List<String>> getElements() {
        return elements;
    }

    private void updateValue(FileProperty f, String s) {
        List<String> tempList = new ArrayList<>();
        if (elements.containsKey(f)) {
            tempList = elements.get(f);
        }
        tempList.add(s);
        elements.put(f, tempList);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            FileProperty entries = new FileProperty(file.toFile().length(), file.toFile().getName());
            String path = file.toFile().getAbsolutePath();
            updateValue(entries, path);
        }
        return super.visitFile(file, attrs);
    }
}