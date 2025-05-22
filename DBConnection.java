package org.week12.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:your-database.db");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database");
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
