package ru.job4j.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName args) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(args.get("path"))).useDelimiter(args.get("delimiter"));
            String[] filter = args.get("filter").split(",");
            String findWord = "";

            for (String s : filter) {
                findWord += scanner.findInLine(s) + ";";
            }
            System.out.println(findWord);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}