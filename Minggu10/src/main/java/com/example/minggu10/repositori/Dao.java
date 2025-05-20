package com.example.minggu10.repositori;

import java.util.List;

public interface Dao<ObjectType, idType> {
    ObjectType findById(idType id);

    List<ObjectType> findAll();

    boolean save(ObjectType mahasiswa);
    boolean update(ObjectType mahasiswaOld);
    boolean delete(ObjectType mahasiswa);
}
