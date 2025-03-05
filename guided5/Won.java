package com.rplbo.guided;

public class Won extends Currency{
    private double value;

    public Won(double value){
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
        return value/1455;
    }

    @Override
    public double toRupiah() {
        return value/15;
    }

    @Override
    public double toWon() {
        return value;
    }

    @Override
    public double toYen() {
        return value/0.10;
    }
}
