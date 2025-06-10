package com.ukdw.prplbo.jackpot;

public class User {
    private static String username;
    private static int attempt = 0;
    private static String status;
    private static String password;

    public User() {
    }

    public static String getUsername() {
        return username;
    }

    public static int getAttempt() {
        return attempt;
    }

    public static String getStatus() {
        return status;
    }

    public static String getPassword() {
        return password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public int increaseAttempts() {
        this.attempt += 1;
        return attempt;
    }
}