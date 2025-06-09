package org.ukdw.managers;

import org.ukdw.data.Gedung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GedungManager {
    private Connection connection;

    public GedungManager(Connection connection) {
        this.connection = connection;
    }

    // Add methods for course management (create, edit, delete)
    public boolean addGedung(String nama, String alamat)  {
        String query = "INSERT INTO gedung (nama, alamat) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, alamat);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course added successfully if rows were inserted
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public boolean editGedung(int gedungId, String newNama, String newAlamat) {
        String query = "UPDATE gedung SET nama = ?, alamat = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newNama);
            preparedStatement.setString(2, newAlamat);
            preparedStatement.setInt(3, gedungId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course edited successfully if rows were affected
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public boolean deleteGedung(int gedungId) {
        String query = "DELETE FROM gedung WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gedungId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course deleted successfully if rows were affected
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public List<Gedung> getAllGedung() {
        List<Gedung> gedungs = new ArrayList<>();
        String query = "SELECT * FROM gedung";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String alamat = resultSet.getString("alamat");
                Gedung gedung = new Gedung(id, nama, alamat);
                gedungs.add(gedung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
        }
        return gedungs;
    }
}
