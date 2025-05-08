package org.week11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PieChartController implements Initializable {
    @FXML
    private PieChart pieChart;

    private final String database = "jdbc:sqlite:catatanku.db";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(database)) {
            String sql = "SELECT kategori, COUNT(*) as jumlah FROM catatan GROUP BY kategori";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String kategori = rs.getString("kategori");
                int jumlah = rs.getInt("jumlah");
                data.add(new PieChart.Data(kategori, jumlah));
            }

            pieChart.setData(data);
            pieChart.setLegendVisible(true);
            pieChart.setLabelsVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}