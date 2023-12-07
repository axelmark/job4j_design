package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean server = false;
        StringBuilder str = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(target)) {
            for (String s : read.lines().toList()) {
                String[] st = s.split(" ");
                if (Integer.parseInt(st[0]) == 400 || Integer.parseInt(st[0]) == 500) {
                    if (!server) {
                        str.append(st[1]);
                        str.append(";");
                        out.write(String.valueOf(str));
                        server = true;
                    }
                } else if (server) {
                    str.append(st[1]);
                    str.append(";");
                    out.write(String.valueOf(str));
                    out.write(System.lineSeparator());
                    server = false;
                }
                str = new StringBuilder();
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