package org.ukdw.data;

import java.io.Serializable;

public class Gedung implements Serializable {

    private int id;
    private String nama;
    private String alamat;

    /**
     * Self initialization.
     *
     * @param nama   Building Name.
     * @param alamat Building's Address.
     */
    public Gedung(int id, String nama, String alamat) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * Returns the buildingName.
     *
     * @return buildingName.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Returns the address of the building.
     *
     * @return address.
     */
    public String getAlamat() {
        return alamat;
    }

    public int getId() {
        return id;
    }
}
