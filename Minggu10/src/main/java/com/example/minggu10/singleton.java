package com.example.minggu10;

public class singleton {
    private static singleton instance;
    private int count = 0;

    private singleton() {
    }

    public static synchronized singleton getInstance() {
        if (instance == null) {
            instance = new singleton();
        }
        return instance;
    }

    public int getCount() {
        return count;
    }

    public void increase() {
        count++;
    }
}
