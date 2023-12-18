package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        Map<List<Path>, File> map = new HashMap<>();
        map.putIfAbsent(sources, target);
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

    public static void main(String[] args) {
        String[] arguments = new String[]{"-d=/Volumes/Project/JAVA/job4j_design", "-e=.class", "-o=project.zip"};
        ArgsName argsName = ArgsName.of(arguments);
        Search.validate(argsName.get("d"), argsName.get("e"));

        Zip zip = new Zip();

        var temp = new ArrayList<Path>();
        temp.add(new File("./pom.xml").toPath());
        zip.packFiles(temp, new File("./pom.zip"));

        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}