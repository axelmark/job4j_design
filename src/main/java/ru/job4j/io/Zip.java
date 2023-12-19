package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        for (Path s : sources) {
            packSingleFile(s.toFile(), target);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(String... args) {
        if (!Files.exists(Path.of(args[0])) && Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Directory doesn't exist");
        }
        if (!args[1].matches("^[a-z]*.zip")) {
            throw new IllegalArgumentException("The file extension must match the template '.zip' ");
        }
    }

    public static void main(String[] args) {
        String[] arguments = new String[]{"-d=pom.xml", "-e=.class", "-o=pom.zip"};
        ArgsName argsName = ArgsName.of(arguments);

        Zip zip = new Zip();
        zip.validate(argsName.get("d"), argsName.get("o"));

        var temp = new ArrayList<Path>();
        temp.add(Path.of(argsName.get("d")));
        zip.packFiles(temp, new File(argsName.get("o")));

      /*  zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );*/
    }
}