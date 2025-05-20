package com.example.minggu10.repositori;

import com.example.minggu10.DBManager;
import com.example.minggu10.Mahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaRepository implements Dao<Mahasiswa, String> {

    private Connection connection;
    public MahasiswaRepository(Connection connection) {
        this.connection = connection;
        buatTableJikaBelumAda();
    }

    private void buatTableJikaBelumAda() {
        String sql = """
                CREATE TABLE IF NOT EXISTS mahasiswa (
                    nim TEXT PRIMARY KEY,
                    nama TEXT NOT NULL,
                    nilai REAL,
                    foto BLOB
                )
                """;
        try (Statement statement = DBManager.getConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel: " + e.getMessage());
        }
    }

    @Override
    public Mahasiswa findById(String id) {
        String sql = "SELECT * FROM mahasiswa WHERE nim = ?";
        try (PreparedStatement ps = DBManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Mahasiswa(
                        rs.getString("nim"),
                        rs.getString("nama"),
                        rs.getFloat("nilai"),
                        rs.getBytes("foto")
                );
            }
        } catch (SQLException e) {
            System.out.println("findById error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Mahasiswa> findAll() {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa";
        try (Statement statement = DBManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                list.add(new Mahasiswa(
                        resultSet.getString("nim"),
                        resultSet.getString("nama"),
                        resultSet.getFloat("nilai"),
                        resultSet.getBytes("foto")
                ));
            }
        } catch (SQLException e) {
            System.out.println("findAll error: " + e.getMessage());
        }

        return list;
    }

    @Override
    public boolean save(Mahasiswa mahasiswa) {
        String sql = "INSERT INTO mahasiswa (nim, nama, nilai, foto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DBManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, mahasiswa.getNim());
            ps.setString(2, mahasiswa.getNama());
            ps.setFloat(3, mahasiswa.getNilai());
            ps.setBytes(4, mahasiswa.getFoto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("save error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        String sql = "UPDATE mahasiswa SET nama = ?, nilai = ?, foto = ? WHERE nim = ?";
        try (PreparedStatement ps = DBManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, mahasiswa.getNama());
            ps.setFloat(2, mahasiswa.getNilai());
            ps.setBytes(3, mahasiswa.getFoto());
            ps.setString(4, mahasiswa.getNim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("update error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Mahasiswa mahasiswa) {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try (PreparedStatement ps = DBManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, mahasiswa.getNim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("delete error: " + e.getMessage());
        }
        return false;
    }
}
