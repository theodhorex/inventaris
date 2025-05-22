package org.week12.dao;

import org.week12.data.Catatan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatatanDaoImpl implements CatatanDao {
    private final Connection connection;

    public CatatanDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Catatan> getAll() {
        return null;
    }

    @Override
    public boolean add(Catatan catatan) {
        return false;
    }

    @Override
    public boolean update(Catatan oldCatatan, Catatan newCatatan) {
        return false;
    }

    @Override
    public boolean delete(Catatan catatan) {
        return false;
    }

    // Implementasi CRUD
    // ... (lihat dokumen samping untuk isi lengkap)
}
