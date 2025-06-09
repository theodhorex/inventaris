package org.week12.util;

import org.week12.data.Catatan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;
import org.week12.util.DatabaseDriver;
import org.week12.data.Catatan;

public class repository implements Dao<Catatan, Integer>{

    private Connection connection;


    public repository(DatabaseDriver driver) {
        this.connection = driver.getConnection();
        List<Catatan> catatanList = new ArrayList<>();
    }

    @Override
    public List<Catatan> getAllDataCatatan() {
        String query = "SELECT * FROM catatan";
        List<Catatan> catatanList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                String konten = resultSet.getString("konten");
                String kategori = resultSet.getString("kategori");
                Catatan catatan = new Catatan(id, judul, konten, kategori);
                catatanList.add(catatan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catatanList;
    }

    @Override
    public boolean addCatatan(Catatan catatan) {
        String queryGetNextId = "SELECT seq FROM SQLITE_SEQUENCE WHERE name = 'catatan' LIMIT 1";
        String queryInsert = "INSERT INTO catatan (judul, konten, kategori) VALUES (?, ?, ?)";
        try {
            connection.setAutoCommit(false); // Start transaction
            try (PreparedStatement getNextIdStatement = connection.prepareStatement(queryGetNextId);
                 PreparedStatement insertStatement = connection.prepareStatement(queryInsert)) {
                ResultSet resultSet = getNextIdStatement.executeQuery();
                int nextId = 1;
                if (resultSet.next()) {
                    nextId = resultSet.getInt("seq") + 1;
                }
                insertStatement.setString(1, catatan.getJudul());
                insertStatement.setString(2, catatan.getKonten());
                insertStatement.setString(3, catatan.getKategori());
                int rowsAffected = insertStatement.executeUpdate();

                if (rowsAffected > 0) {
                    catatan.setId(nextId);
                    connection.commit();
                    return true;
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCatatan(Catatan oldCatatan, Catatan newCatatan) {
        String query = "UPDATE catatan SET judul = ?, konten = ?,  kategori = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newCatatan.getJudul());
            preparedStatement.setString(2, newCatatan.getKonten());
            preparedStatement.setString(3, newCatatan.getKategori());
            preparedStatement.setInt(4, oldCatatan.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCatatan(Catatan catatan) {
        String query = "DELETE FROM catatan WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, catatan.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
