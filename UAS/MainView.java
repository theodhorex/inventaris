package org.ukdw.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Handle action related to input (in this case specifically only responds to
     * keyboard event CTRL-A).
     *
     * @param event Input event.
     */
    @FXML
    private void handleKeyInput(final InputEvent event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {

            }
        }
    }

    @FXML
    private void doExit(final ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void doOnlineManual(final ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://www.ukdw.ac.id").toURI());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void doAbout(final ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Kami dari 27 bulan Mei");
        info.setHeaderText(null);
        info.setContentText("WLEEEE");
        info.showAndWait();
    }

    @FXML
    private void handleGedungAction(final ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/ukdw/gedung-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Data Gedung");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUserAction(final ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/ukdw/user-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Data User");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBookingAction(final ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/ukdw/pemesanan-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pemesanan");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRuanganAction(final ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/ukdw/ruangan-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Data Ruangan");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void doLogout(final ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Apakah kamu yakin?");
        alert.setContentText("Apakah ingin logout? huhuhu");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.close();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("org/ukdw/login-view.fxml"));
                Stage loginStage = new Stage();
                loginStage.setTitle("Login");
                loginStage.setScene(new Scene(root));
                loginStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Info");
            info.setHeaderText(null);
            info.setContentText("Logout dibatalkan.");
            info.showAndWait();
        }
    }
}
