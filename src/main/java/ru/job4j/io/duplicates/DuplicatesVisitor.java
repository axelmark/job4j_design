package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    public final List<FileProperty> basic = new ArrayList<>();
    public final Set<FileProperty> elements = new HashSet<>();
    public final List<FileProperty> duplicates = new ArrayList<>();

    List<FileProperty> sortDuplicates(List<FileProperty> list) {
        for (FileProperty f : list) {
            if (!elements.add(new FileProperty(f.getSize(), f.getName(), f.getPath()))) {
                duplicates.add(f);
            }
        }
        for (FileProperty f : elements) {
            if (duplicates.contains(f)) {
                duplicates.add(f);
            }
        }
        duplicates.sort(Comparator.comparing(FileProperty::getName));
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            basic.add(new FileProperty(file.toFile().length(), file.toFile().getName(), file.toFile().getAbsolutePath()));
        }
        return super.visitFile(file, attrs);
    }
}