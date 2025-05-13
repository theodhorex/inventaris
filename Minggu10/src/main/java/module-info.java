module com.example.minggu10 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.minggu10 to javafx.fxml;
    exports com.example.minggu10;
}