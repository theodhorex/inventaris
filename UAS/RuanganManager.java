package org.ukdw.managers;

import org.ukdw.data.Ruangan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RuanganManager {
    private final Connection connection;

    public RuanganManager(Connection connection) {
        this.connection = connection;
    }

    // Add methods for course management (create, edit, delete)
    public boolean addRuangan(String nama, int idGedung) {
        String query = "INSERT INTO ruangan (nama, gedung_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setInt(2, idGedung);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course added successfully if rows were inserted
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public boolean editRuangan(String newNama, int idGedung, int idRuangan) {
        String query = "UPDATE ruangan SET nama = ?, gedung_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newNama);
            preparedStatement.setInt(2, idGedung);
            preparedStatement.setInt(3, idRuangan);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course edited successfully if rows were affected
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public boolean deleteRuangan(int idRuangan) {
        String query = "DELETE FROM ruangan WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idRuangan);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Course deleted successfully if rows were affected
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
            return false;
        }
    }

    public List<Ruangan> getAllRuangan() {
        List<Ruangan> ruangans = new ArrayList<>();
        String query = "SELECT * FROM ruangan";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                int idGedung = resultSet.getInt("gedung_id");
                Ruangan ruangan = new Ruangan(id, nama, idGedung);
                ruangans.add(ruangan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
        }
        return ruangans;
    }

    public List<Ruangan> lihatSemuaRuangan() {
        return null;
    }

    public List<Ruangan> searchRuanganByKeyword(String keyword) {
        List<Ruangan> ruangans = new ArrayList<>();
        String query = "SELECT * FROM ruangan WHERE nama LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                int gedungId = rs.getInt("gedung_id");
                ruangans.add(new Ruangan(id, nama, gedungId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruangans;
    }

    public String getRuanganNameById(int id) {
        String query = "SELECT nama FROM ruangan WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nama");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Tidak Dikenal";
    }
}
