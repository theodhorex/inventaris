package org.ukdw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.ukdw.managers.UserManager;
import org.ukdw.utils.DBConnectionManager;

import java.io.IOException;
import java.sql.Connection;

public class LoginView {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    private UserManager userManager;

    public LoginView() {
        Connection connection = DBConnectionManager.getConnection();

        if (connection == null) {
            System.err.println("Database connection is NULL. Please check DB setup.");
        }

        DBConnectionManager.createTables(); // Buat tabel jika belum ada
        userManager = new UserManager(connection); // Inisialisasi UserManager dengan koneksi
    }

    @FXML
    public void handleLoginAction(ActionEvent actionEvent) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Username dan password tidak boleh kosong.");
            return;
        }

        boolean loginSuccess = userManager.authenticateUser(username, password);

        if (loginSuccess) {
            showAlert(Alert.AlertType.INFORMATION, "Login Sukses", "Selamat datang, " + username + "!");
           openMainMenu();
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Gagal", "Username atau password salah.");
        }
    }


    @FXML
    public void onKeyPressEvent(javafx.scene.input.KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case ENTER -> handleLoginAction(new ActionEvent());
        }
    }

    private void openMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/ukdw/main-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Utama");
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal membuka menu utama.");
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}