package com.rplbo.guided;

public abstract class Wallet {
    protected double totalUang;
    public abstract void insertMoney(Currency c);
    public abstract void expenseMoney(Currency c);
    public abstract void printMoney();

    public double getTotalUang() {
        return totalUang;
    }

    public void setTotalUang(double totalUang) {
        this.totalUang = totalUang;
    }
}
