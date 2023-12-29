package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private final Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException, IllegalAccessException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            String read;
            while ((read = reader.readLine()) != null) {
                String[] items = read.split(";");
                if (items.length != 2 || items[1].startsWith(";")) {
                    throw new IllegalAccessException("Argument not passed");
                }
                users.add(new User(items[0], items[1]));
            }
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("login"),
                config.getProperty("password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, email) values(?, ?);")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/spammer/dump.txt");
        dataBase.save(dataBase.load());
    }
}