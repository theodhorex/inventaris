package com.example.minggu10;

public class Demosingleton {
    public static void main(String[] args) {
        singleton singletonInstance = singleton.getInstance();
    }
}

class kelas {
    public kelas() {
        singleton.getInstance().increase();
    }
}

class kelas2 {
    public kelas2() {
        singleton.getInstance().increase();
    }
}
