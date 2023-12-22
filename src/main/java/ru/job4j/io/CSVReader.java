package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName args) {
        try (Scanner scanner = new Scanner(new File(args.get("path")))) {
            StringBuilder res = new StringBuilder();
            List<Integer> indexes = new ArrayList<>();
            String[] filter = args.get("filter").split(",");
            String[] headers = scanner.nextLine().split(args.get("delimiter"));

            for (int f = 0; f < filter.length; f++) {
                for (int h = 0; h < headers.length; h++) {
                    if (headers[h].equals(filter[f])) {
                        indexes.add(h);
                        res.append(f < filter.length - 1
                                ? filter[f] + args.get("delimiter") : filter[f] + "\n");
                    }
                }
            }

            while (scanner.hasNext()) {
                String[] columns = scanner.nextLine().split(args.get("delimiter"));
                for (int i = 0; i < indexes.size(); i++) {
                    res.append(i < filter.length - 1
                            ? columns[indexes.get(i)] + args.get("delimiter") : columns[indexes.get(i)] + "\n");
                }
            }
            if (!args.get("out").equals("stdout")) {
                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(args.get("out")))) {
                    out.write(res.toString().getBytes());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}