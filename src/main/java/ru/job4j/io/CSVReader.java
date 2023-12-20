package ru.job4j.io;

import java.io.File;
import java.util.Scanner;

import static java.lang.String.format;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Scanner scanner = new Scanner(new File(argsName.get("path")))
                .useDelimiter(argsName.get("delimiter"));
        String[] filter = argsName.get("filter").split(",");
        for (String s : filter) {
            String findWord = scanner.findInLine(s);
            System.out.println(findWord);
        }
       /* while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }*/
    }

    public static void main(String[] args) throws Exception {
        String[] arguments = new String[]{"-path=data/csvreader/file.csv", "-delimiter=;", "-out=stdout", "-filter=name,age"};

        if (arguments.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String s : arguments) {
            if (!s.contains("=")) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain an equal sign", s));
            }
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not start with a '-' character", s));
            }
            String[] temp = s.split("=", 2);
            if (temp[0].replaceAll("-", "").trim().isBlank()) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain a key", s));
            }
            if (temp[1].isBlank()) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain a value", s));
            }
        }

        ArgsName argsName = ArgsName.of(arguments);
        handle(argsName);
    }
}