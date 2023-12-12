package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    public final List<Path> basic = new ArrayList<>();
    public final Set<FileProperty> elements = new HashSet<>();

    List<Path> sortDuplicates(List<Path> list) {
        return list.stream()
                .filter(f -> !elements.add(new FileProperty(f.toFile().length(), f.toFile().getName())))
                .collect(Collectors.toList());
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            basic.add(file);
        }
        return super.visitFile(file, attrs);
    }
}