package com.rplbo.guided;

public class Yen extends Currency{
    private double value;

    public Yen(double value){
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
        return value/150;
    }

    @Override
    public double toRupiah() {
        return value / 0.009125;
    }

    @Override
    public double toWon() {
        return value/12;
    }

    @Override
    public double toYen() {
        return value;
    }
}
