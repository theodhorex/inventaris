package org.example.latihan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.example.latihan.Mahasiswa;
import org.example.latihan.ManagerMahasiswa;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class LineChartMhsController implements Initializable {
    private ManagerMahasiswa manager;
    @FXML
    private LineChart lineChart;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.manager = new ManagerMahasiswa();
        preparedData();
    }
    private void preparedData() {
        XYChart.Series series = new XYChart.Series();
        series.setName("Persebaran Nilai Mahasiswa");
        ArrayList<Mahasiswa> mahasiswas = manager.getAllMahasiswa();
        for (Mahasiswa mahasiswa : mahasiswas) {
            series.getData().add(new XYChart.Data(mahasiswa.getNim(),
                    mahasiswa.getNilai()));
        }
        lineChart.getData().add(series);
    }
}
