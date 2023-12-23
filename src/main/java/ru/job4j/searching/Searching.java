package ru.job4j.searching;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Searching {
    public void start(ArgsName argsName) throws IOException {

        Predicate<Path> predicate = switch (argsName.get("t")) {
            case ("name") -> p -> p.toFile().getName().startsWith(argsName.get("n"));
            case ("mask") -> p -> p.toFile().getName().matches("\\.\\*" + argsName.get("n"));
            case ("regex") -> p -> p.toFile().getName().matches(".*" + argsName.get("n") + ".*");
            default -> p -> p.toFile().getName().matches(".*");
        };

        List<Path> res = Search.search(Path.of(argsName.get("d")), predicate);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(String.valueOf(argsName.get("o"))))) {
            for (Path s : res) {
                out.write(s.toString().getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Файл записан " + argsName.get("o"));
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите директорию, в которой начинать поиск");
        String d = scanner.next();
        System.out.println("Введите имя файла, маска, либо регулярное выражение.");
        String n = scanner.next();
        System.out.println("Введите тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.");
        String t = scanner.next();
        System.out.println("Введите результат записать в файл");
        String o = scanner.next();
        String set = String.format("-d=%s -n=%s -t=%s -o=%s", d, n, t, o);
        ArgsName argsName = ArgsName.of(set.split(" "));
/*
        ArgsName argsName = ArgsName.of(new String[]{"-d=.", "-n=ResultFile", "-t=name", "-o=data/searching/log.txt"});
*/
        Searching search = new Searching();
        search.start(argsName);
    }
}