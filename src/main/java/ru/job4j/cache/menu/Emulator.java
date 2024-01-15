package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public static final int CACHE_DIR = 1;
    public static final int LOAD_FILE_CONTENT_TO_CACHE = 2;
    public static final int GET_FILE_CONTENT_FROM_CACHE = 3;
    private String dir = "src/main/java/ru/job4j/cache/files/";
    private String key = "Names.txt";
    public static final String MENU = """
                Введите 1, указать кэшируемую директорию.
                Введите 2, загрузить содержимое файла в кэш.
                Введите 3, получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (CACHE_DIR == userChoice) {
                System.out.println("укажите кэшируемую директорию");
                emulator.dir = scanner.nextLine();
                System.out.print(System.lineSeparator());
            } else if (LOAD_FILE_CONTENT_TO_CACHE == userChoice) {
                System.out.println("укажите файл для кеширования");
                emulator.key = scanner.nextLine();
                System.out.println("Кеш будет обновлен по ключу: " + emulator.key);
                System.out.print(System.lineSeparator());
            } else if (GET_FILE_CONTENT_FROM_CACHE == userChoice) {
                DirFileCache dirFileCache = new DirFileCache(emulator.dir);
                System.out.print(dirFileCache.get(emulator.key));
                System.out.print(System.lineSeparator());
            } else {
                run = false;
            }
        }
    }
}
