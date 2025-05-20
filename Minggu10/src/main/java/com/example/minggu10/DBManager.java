package com.example.minggu10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
    private final String DB_URL = "jdbc:sqlite:nilaimahasiswa.db";
    private Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(DB_URL);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}

