package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        System.out.println("Читаю файл...\n");
        String content = null;
        try {
            Path path = Paths.get(cachingDir + key);
            content = Files.readString(path);
            super.put(key, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}