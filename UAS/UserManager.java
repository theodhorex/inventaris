package org.ukdw.managers;

import org.ukdw.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    private Connection connection;

    public UserManager(Connection connection) {
        this.connection = connection;
    }

    // Add methods for user authentication, registration, and profile management
    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // User exists if there is at least one row in the result set
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public boolean registerUser(String email,String username,String password) {
        User user = new User(email, username, password);
        String query = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Registration successful if rows were inserted
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public boolean updateProfile(String email,String username,String password) {
        String query = "UPDATE users SET username = ?, password = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Profile updated if rows were affected
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }
}
