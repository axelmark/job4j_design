package ru.job4j.io;

import java.io.File;
import java.util.Scanner;

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
        /* здесь добавьте валидацию принятых параметров*/

        String[] arguments = new String[]{"-path=data/csvreader/file.csv", "-delimiter=;", "-out=stdout", "-filter=name,age"};
        ArgsName argsName = ArgsName.of(arguments);
        handle(argsName);
    }
}