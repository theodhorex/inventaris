package com.example.minggu10;

import java.sql.*;
import java.util.ArrayList;

public abstract class MahasiswaManager {
    abstract void MahasiswaManager();
    public void ManagerMahasiswa() {
        buatTabelJikaBelumAda();
    }
    // Membuat tabel Mahasiswa jika belum ada
    private void buatTabelJikaBelumAda() {
        String sql = "CREATE TABLE IF NOT EXISTS mahasiswa (" +
                "nim TEXT PRIMARY KEY, " +
                "nama TEXT NOT NULL, " +
                "nilai REAL NOT NULL, " +
                "foto BLOB) ";

        try {
            Connection conn = DBManager.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Gagal membuat tabel: " + e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
    }
    // CREATE: Menambahkan Mahasiswa
    public boolean tambahMahasiswa(Mahasiswa mahasiswa) {
        String sql = "INSERT INTO mahasiswa (nim, nama, nilai, foto) VALUES (?, ?, ?, ?)";
        DBManager DBConnectionManager = new DBManager();
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mahasiswa.getNim());
            pstmt.setString(2, mahasiswa.getNama());
            pstmt.setDouble(3, mahasiswa.getNilai());
            pstmt.setBytes(4, mahasiswa.getFoto());
            pstmt.executeUpdate();
            System.out.println("Mahasiswa berhasil ditambahkan.");
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan mahasiswa: " +
                    e.getMessage());
            return false;
        } finally {
            DBConnectionManager.closeConnection();
        }
    }
    public ArrayList<Mahasiswa> getAllMahasiswa() {
        ArrayList<Mahasiswa> mhslist = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa";
        try {
            Connection conn = DBManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                mhslist.add(new Mahasiswa(
                        rs.getString("nim"),
                        rs.getString("nama"),
                        (float) rs.getDouble("nilai"),
                        rs.getBytes("foto")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Gagal membaca data mahasiswa: " +
                    e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return mhslist;
    }
    // UPDATE: Memperbarui Data Mahasiswa
    public boolean updateMahasiswa(Mahasiswa mahasiswa) {
        String sql = "UPDATE mahasiswa SET nama = ?, nilai = ? , foto = ? WHERE nim = ?";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mahasiswa.getNama());
            pstmt.setDouble(2, mahasiswa.getNilai());
            pstmt.setBytes(3, mahasiswa.getFoto());
            pstmt.setString(4, mahasiswa.getNim());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data mahasiswa berhasil diperbarui.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Gagal memperbarui data mahasiswa: " +
                    e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return false;
    }
    // DELETE: Menghapus Mahasiswa
    public boolean hapusMahasiswa(String nim) {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nim);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mahasiswa dengan NIM " + nim + " telah dihapus.");
                return true;
            }
        } catch (SQLException e) {

            System.err.println("Gagal menghapus mahasiswa: " +
                    e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return false;
    }
}

