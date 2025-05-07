package org.example.latihan;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
public class Mahasiswa {
    private SimpleStringProperty nim;
    private SimpleStringProperty nama;
    private SimpleDoubleProperty nilai;
    private byte[] foto;
    public Mahasiswa(String nim, String nama, double nilai, byte[] foto) {
        this.nim = new SimpleStringProperty(nim);
        this.nama = new SimpleStringProperty(nama);
        this.nilai = new SimpleDoubleProperty(nilai);
        this.foto = foto;
    }
    public String getNim() {
        return nim.get();
    }
    public void setNim(String nim) {
        this.nim = new SimpleStringProperty(nim);
    }
    public String getNama() {
        return nama.get();
    }
    public void setNama(String nama) {
        this.nama = new SimpleStringProperty(nama);
    }
    public double getNilai() {
        return nilai.get();
    }
    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public void setNilai(double nilai) {
        this.nilai = new SimpleDoubleProperty(nilai);
    }
    public String toString() {
        return nim + " - " + nama + " (" + nilai + ")";
    }
}