package org.example;

public abstract class Colokan {
    private String merk;
    private double promisedBandwith;
    private int harga;

    public Colokan(int harga, double promisedBandwith, String merk) {
        this.merk = merk;
        this.promisedBandwith = promisedBandwith;
        this.harga = harga;
    }

    public abstract double getRealBandwith();

    public String getMerk() {
        return merk;
    }

    public double getPromisedBandwith() {
        return promisedBandwith;
    }

    public int getHarga() {
        return harga;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setPromisedBandwith(double promisedBandwith) {
        this.promisedBandwith = promisedBandwith;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
