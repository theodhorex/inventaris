package org.week11;

public class Catatan {
    private int id;
    private String judul;
    private String konten;
    private String kategori;

    //konstanta
    public static String CATATAN_SELF_DEVELOPEMENT = "Self Development";
    public static String CATATAN_BELANJA = "Belanja";
    public static String CATATAN_KHUSUS = "Khusus";
    public static String CATATAN_PERCINTAAN = "Percintaan";

    public Catatan(String judul, String konten) {
        this.judul = judul;
        this.konten = konten;
    }

    public Catatan(int id, String judul, String konten) {
        this.id = id;
        this.judul = judul;
        this.konten = konten;
    }

    public Catatan(int id, String judul, String konten, String kategori) {
        this.id = id;
        this.judul = judul;
        this.konten = konten;
        this.kategori = kategori;
    }

    public Catatan(String judul, String konten, String kategori) {
        this.judul = judul;
        this.konten = konten;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
