package com.ukdw.prplbo.jackpot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JackpotApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseManager.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(JackpotApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("gacor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}