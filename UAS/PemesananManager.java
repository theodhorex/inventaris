package org.ukdw.managers;

import org.ukdw.data.Gedung;
import org.ukdw.data.Pemesanan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PemesananManager {
    private Connection connection;

    public PemesananManager(Connection connection) {
        this.connection = connection;
    }

    // Add methods for course management (create, edit, delete)
    public boolean addPemesanan(String userEmail, int idRuangan,
                                String checkInDate, String checkOutDate,
                                String checkInTime, String checkOutTime) {
        String query = "INSERT INTO pemesanan (user_email, ruangan_id, " +
                "checkindate, checkoutdate, " +
                "checkintime, checkouttime) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, idRuangan);
            preparedStatement.setString(3, checkInDate);
            preparedStatement.setString(4, checkOutDate);
            preparedStatement.setString(5, checkInTime);
            preparedStatement.setString(6, checkOutTime);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course added successfully if rows were inserted
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public boolean editPemesanan(int idPemesanan,
                                 String userEmail, int idRuangan,
                                 String checkInDate, String checkOutDate,
                                 String checkInTime, String checkOutTime) {
        String query = "UPDATE pemesanan SET user_email = ?, ruangan_id = ?, " +
                "checkindate = ?, checkoutdate = ?, " +
                "checkintime = ?, checkouttime = ? " +
                "WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, idRuangan);
            preparedStatement.setString(3, checkInDate);
            preparedStatement.setString(4, checkOutDate);
            preparedStatement.setString(5, checkInTime);
            preparedStatement.setString(6, checkOutTime);
            preparedStatement.setInt(7, idPemesanan);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course edited successfully if rows were affected
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public boolean deletePemesanan(int idPemesanan) {
        String query = "DELETE FROM pemesanan WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idPemesanan);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course deleted successfully if rows were affected
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public List<Pemesanan> getAllPemesanan() {
        List<Pemesanan> pemesanans = new ArrayList<>();
        String query = "SELECT * FROM pemesanan";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userEmail = resultSet.getString("user_email");
                int idRuangan = resultSet.getInt("ruangan_id");
                String checkindate = resultSet.getString("checkindate");
                String checkoutdate = resultSet.getString("checkoutdate");
                String checkintime = resultSet.getString("checkintime");
                String checkouttime = resultSet.getString("checkouttime");
                Pemesanan pemesanan = new Pemesanan(id, userEmail, idRuangan, checkindate, checkoutdate,
                        checkintime, checkouttime);
                pemesanans.add(pemesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
        }
        return pemesanans;
    }
}
