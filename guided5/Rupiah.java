package com.rplbo.guided;

public class Rupiah extends Currency{
    private double value;

    public Rupiah(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double toUSD() {
        return value/16500;
    }

    @Override
    public double toRupiah() {
        return value;
    }

    @Override
    public double toWon() {
       return value/11.3;
    }

    @Override
    public double toYen() {
        return value * 0.009125;
    }
}
