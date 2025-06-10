package com.ukdw.prplbo.jackpot;

import java.util.Random;

public class                                                                                                           Game {
    char a;
    char b;
    char c;

    public void spin() {
        Random rnd = new Random();
        this.a = (char) ('a' + rnd.nextInt(26));
        this.b = (char) ('b' + rnd.nextInt(26));
        this.c = (char) ('c' + rnd.nextInt(26));
    }

    public char getA() {
        return a;
    }

    public char getB() {
        return b;
    }

    public char getC() {
        return c;
    }

    public String evaluateResult() {
        //untuk menampilkan hasil
        if (a == b && b == c) {
            return "Jackpot!!!!!!!!";
        } else if (a == b || b == c) {
            return "Anda mendapat 2 huruf keberuntungan!";
        } else {
            return "Anda belum beruntung";
        }
    }
}

