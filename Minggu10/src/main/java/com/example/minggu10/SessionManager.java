package com.example.minggu10;

import java.io.*;
public class SessionManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String SESSION_FILE = "session.ser";
    private static volatile SessionManager instance;
    private boolean isLoggedIn;

    private SessionManager() {
        isLoggedIn = false;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                    instance.createSessionFile();
                }
            }
        }
        return instance;
    }

    public void createSessionFile() {File file = new File(SESSION_FILE);
        if (!file.exists()) {
            saveSession();
        } else {
            loadSession();
        }
    }
    private void loadSession() {
        try (ObjectInputStream ois = new ObjectInputStream(new
                FileInputStream(SESSION_FILE))) {
            SessionManager sessionManager = (SessionManager)
                    ois.readObject();
            this.isLoggedIn = sessionManager.isLoggedIn;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading session: " +
                    e.getMessage());
        }
    }
    private void saveSession() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new
                FileOutputStream(SESSION_FILE))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void login() {
        isLoggedIn = true;
        saveSession();
    }

    public void logout() {
        isLoggedIn = false;
        saveSession();
    }
}