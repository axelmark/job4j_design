package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean server = false;
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(target)) {
            for (String s : read.lines().toList()) {
                String[] st = s.split(" ");
                if (Integer.parseInt(st[0]) == 400 || Integer.parseInt(st[0]) == 500) {
                    if (!server) {
                        out.write(st[1] + ";");
                        server = true;
                    }
                } else if (server) {
                    out.write(st[1] + ";");
                    out.write(System.lineSeparator());
                    server = false;
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