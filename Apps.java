package org.week11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Apps extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Title");
        primaryStage.setScene(new Scene(loadFXML("login-view")));
        primaryStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Apps.class
                .getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void setRoot(String fxml, String title, boolean isResizeable)
            throws IOException {
        primaryStage.getScene().setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
        primaryStage.setResizable(isResizeable);
        if(title !=null){
            primaryStage.setTitle(title);
        }
        primaryStage.show();
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

    public static void main(String[] args) {
        launch();
    }
}