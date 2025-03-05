package com.rplbo.guided;

public class DollarWallet extends Wallet {

    public DollarWallet(double totalUang) {
        this.totalUang = totalUang;
    }

    public DollarWallet(){};

    @Override
    public void insertMoney(Currency c) {
        double hasil = c.toUSD();
        setTotalUang(getTotalUang() + hasil);
    }

    @Override
    public void expenseMoney(Currency c) {
        double total = c.toUSD();
        if (getTotalUang() >= total) {
            setTotalUang(getTotalUang() - total);
        } else {
            System.out.println("saldo tidak mencukupi");
        }
    }

    @Override
    public void printMoney() {
        System.out.println("$ " + getTotalUang());
    }
}
