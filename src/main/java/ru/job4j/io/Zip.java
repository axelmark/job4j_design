package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path s : sources) {
                zip.putNextEntry(new ZipEntry(s.toFile().getAbsolutePath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(s.toFile().getAbsolutePath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    private void validate(ArgsName argsName) {
        if (!Files.exists(Path.of(argsName.get("d"))) && Files.isDirectory(Path.of(argsName.get("o")))) {
            throw new IllegalArgumentException("Directory doesn't exist");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("The file extension must match the template '.zip' ");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validate(argsName);
        List<Path> listCat = Search.search(Path.of(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(listCat, new File(argsName.get("o")));

         /*  zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );*/
    }
}