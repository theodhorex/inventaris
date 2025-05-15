package org.week12;

import org.week12.data.Catatan;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:catatanku.db";
    private static volatile DatabaseManager instance = null;
    private static Connection connection;

    private DatabaseManager() {}

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                    instance.getConnection();
                }
            }
        }
        return instance;
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
            }
        }
    }
}
