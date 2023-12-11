package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> set = new HashSet<>();
    private final List<String> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile() && !set.add(new FileProperty(file.toFile().length(), file.toFile().getName()))) {
            duplicates.add(file.toFile().getAbsolutePath());
        }
        duplicates.forEach(System.out::println);
        return super.visitFile(file, attrs);
    }
}