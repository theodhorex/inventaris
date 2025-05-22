package org.week12.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DATABASE_URL = "jdbc:sqlite:catatanku.db";
    private static final String DATABASE_DRIVER = "org.sqlite.JDBC";
    private static volatile DatabaseUtil instance;
    private DBConnection dbconnection;

    public DatabaseUtil() {
        this.dbconnection = DBConnection.getInstance();
    }

    public static DatabaseUtil getInstance() {
        if (instance == null) {
            synchronized (DatabaseUtil.class) {
                if (instance == null) {
                    instance = new DatabaseUtil();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            if (instance == null) {
                throw new IllegalStateException("DatabaseUtil not initialized properly.");
            }

            return DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            throw new SQLException("Unable to connect to the database.", e);
        }
    }
}
