package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor d = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), d);
        List<Path> duplicates = d.sortDuplicates(d.basic);
        System.out.println("Найдено дубликатов: " + duplicates.size());
        duplicates.forEach(x -> System.out.printf("Имя: %s - Размер: %s - Путь: %s \n",
                x.toFile().getName(),
                x.toFile().length(),
                x.toFile().getAbsolutePath()
        ));
    }
}