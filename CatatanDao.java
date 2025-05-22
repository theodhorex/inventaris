package org.week12.util;

import org.week12.data.Catatan;
import org.week12.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatatanDao {
    private DatabaseUtil dbUtil;

    // Constructor untuk menginisialisasi DatabaseUtil
    public CatatanDao(DatabaseUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public List<Catatan> getAllDataCatatan() {
        List<Catatan> catatanList = new ArrayList<>();
        try (Connection conn = dbUtil.getConnection()) {
            String query = "SELECT * FROM catatan";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String judul = resultSet.getString("judul");
                    String konten = resultSet.getString("konten");
                    String kategori = resultSet.getString("kategori");
                    Catatan catatan = new Catatan(id, judul, konten, kategori);
                    catatanList.add(catatan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catatanList;
    }

    public boolean deleteCatatan(Catatan catatan) {
        try (Connection conn = dbUtil.getConnection()) {
            String query = "DELETE FROM catatan WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, catatan.getId());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCatatan(Catatan catatan) {
        try (Connection conn = dbUtil.getConnection()) {
            String query = "INSERT INTO catatan (judul, konten, kategori) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, catatan.getJudul());
                preparedStatement.setString(2, catatan.getKonten());
                preparedStatement.setString(3, catatan.getKategori());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCatatan(Catatan oldCatatan, Catatan newCatatan) {
        try (Connection conn = dbUtil.getConnection()) {
            String query = "UPDATE catatan SET judul = ?, konten = ?, kategori = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, newCatatan.getJudul());
                preparedStatement.setString(2, newCatatan.getKonten());
                preparedStatement.setString(3, newCatatan.getKategori());
                preparedStatement.setInt(4, oldCatatan.getId());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

