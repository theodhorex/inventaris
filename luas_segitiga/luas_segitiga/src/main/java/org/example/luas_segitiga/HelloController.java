package org.example.luas_segitiga;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField inputAlas;
    @FXML
    private TextField inputTinggi;

    @FXML
    private Label hasilLabel;

    @FXML
    protected void hitungLuasSegitiga() {
        try {
            double alas = Double.parseDouble(inputAlas.getText());
            double tinggi = Double.parseDouble(inputTinggi.getText());
            double luas = 0.5 * alas * tinggi;

            hasilLabel.setText("Luas segitiga: " + luas);
        } catch (NumberFormatException e) {
            hasilLabel.setText("Masukkan angka yang valid!");
        }
    }
    @FXML
    protected void close() {
        System.exit(0);
    }
}