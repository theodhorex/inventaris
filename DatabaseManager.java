package com.ukdw.prplbo.jackpot;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.ukdw.prplbo.jackpot.User;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:gacor.db";
    private static Connection connection;

    private DatabaseManager() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database connection closure error
            }
        }
    }

    public static void createTable() {
        String userTable = "CREATE TABLE IF NOT EXISTS users ("
                + "username TEXT NOT NULL,"
                + "password TEXT NOT NULL,"
                + "status TEXT NOT NULL,"
                + "attempt INTEGER NOT NULL,"
                + "time TEXT NOT NULL"
                + ")";

        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(userTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void saveUser(User user) {
        String nama = User.getUsername();
        String password = User.getPassword();
        String status = User.getStatus();
        int attempt = User.getAttempt();
        String now = LocalDateTime.now().toString();
        String tambahUser = "insert into users values(?, ?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(tambahUser)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, status);
            preparedStatement.setInt(4, attempt);
            preparedStatement.setString(5, now);
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
