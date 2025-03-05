package com.rplbo.guided;

public class Main {
    public static void main(String[] args) {
        Rupiah r = new Rupiah(33000);
        Yen y = new Yen(1000);
        Won w = new Won(5000);
        DollarWallet dw = new DollarWallet();
        dw.insertMoney(r);
        dw.insertMoney(y);
        dw.printMoney();
    }
}
