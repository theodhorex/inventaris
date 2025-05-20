package com.example.minggu10;

public class Mahasiswa {

    private String nim;
    private String nama;
    private float nilai;

    public Mahasiswa(String nim, String nama, float nilai, byte[] fotos) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public float getNilai() {
        return nilai;
    }

    public void setNilai(float nilai) {
        this.nilai = nilai;
    }

    public byte[] getFoto() {
        return new byte[0];
    }
}

