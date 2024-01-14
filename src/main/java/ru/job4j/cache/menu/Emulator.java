package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public static final int CACHE_DIR = 1;
    public static final int LOAD_FILE_CONTENT_TO_CACHE = 2;
    public static final int GET_FILE_CONTENT_FROM_CACHE = 3;
    public static final String BASIC_CACHE_PATH = "src/main/java/ru/job4j/cache/files/";
    public static final String MENU = """
                Введите 1, указать кэшируемую директорию.
                Введите 2, загрузить содержимое файла в кэш.
                Введите 3, получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        String key = "Names.txt";

        DirFileCache dirFileCache = new DirFileCache(BASIC_CACHE_PATH);
        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (CACHE_DIR == userChoice) {
                System.out.println("Установлена как основная директория" + BASIC_CACHE_PATH);
                System.out.print(System.lineSeparator());
            } else if (LOAD_FILE_CONTENT_TO_CACHE == userChoice) {
                System.out.println("Кеш будет обновлен по ключу: " + key);
                System.out.print(System.lineSeparator());
            } else if (GET_FILE_CONTENT_FROM_CACHE == userChoice) {
                System.out.print(dirFileCache.get(key));
                System.out.print(System.lineSeparator());
            } else {
                run = false;
            }
        }
    }
}
