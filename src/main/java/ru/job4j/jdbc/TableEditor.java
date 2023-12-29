package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s ();", tableName);
        executeSQL(tableName, sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s;", tableName);
        executeSQL(tableName, sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD %s %s(255);", tableName, columnName, type);
        executeSQL(tableName, sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        executeSQL(tableName, sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s RENAME %s TO %s;", tableName, columnName, newColumnName);
        executeSQL(tableName, sql);
    }

    public void isTableExists(String tableName) {
        String sql = String.format("SELECT EXISTS (SELECT * FROM %s)", tableName);
        executeSQL(tableName, sql);
    }

    private Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    private void executeSQL(String tableName, String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("sun850");
            tableEditor.dropTable("sun850");
            tableEditor.addColumn("sun850", "name", "varchar");
            tableEditor.dropColumn("sun850", "name");
            tableEditor.renameColumn("sun850", "name", "fio");
            tableEditor.isTableExists("sun850");
        }
    }
}