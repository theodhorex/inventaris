package org.example.latihan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegistrasiMahasiswa extends Application {
    private static Stage primaryStage;
    public RegistrasiMahasiswa() {
    }
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Data Nilai Mahasiswa");
        primaryStage.setScene(new Scene(loadFXML("mahasiswa-view")));
        primaryStage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistrasiMahasiswa.class
                .getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void setRoot(String fxml, boolean isResizeable)
            throws IOException {
        primaryStage.getScene().setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
        primaryStage.setResizable(isResizeable);
    }
    public static void openViewWithModal(String fxml, boolean isResizeable)
            throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(loadFXML(fxml)));
        stage.sizeToScene();
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    public static void main(String[] args) throws IOException {
        launch();
    }
}
