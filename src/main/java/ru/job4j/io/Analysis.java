package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             FileOutputStream out = new FileOutputStream(target)) {
            for (String string : read.lines().toList()) {
                String[] st = string.split(" ");
                if (Integer.parseInt(st[0]) == 400 || Integer.parseInt(st[0]) == 500) {
                    out.write(st[1].getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}