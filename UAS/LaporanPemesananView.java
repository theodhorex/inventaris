package org.ukdw.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ukdw.data.Gedung;
import org.ukdw.managers.GedungManager;
import org.ukdw.managers.PemesananManager;
import org.ukdw.managers.RuanganManager;
import org.ukdw.utils.DBConnectionManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LaporanPemesananView implements Initializable {

    @FXML private ComboBox<Gedung> gedungComboBox;
    @FXML private DatePicker tanggalAwalPicker;
    @FXML private DatePicker tanggalAkhirPicker;
    @FXML private TableView<RoomUsageData> laporanTable;
    @FXML private TableColumn<RoomUsageData, String> ruanganColumn;
    @FXML private TableColumn<RoomUsageData, Integer> jumlahColumn;
    @FXML private Label totalLabel;

    private Connection connection;
    private GedungManager gedungManager;
    private RuanganManager ruanganManager;
    private PemesananManager pemesananManager;

    private final ObservableList<RoomUsageData> roomUsageDataList = FXCollections.observableArrayList();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static class RoomUsageData {
        private final SimpleStringProperty ruanganName;
        private final SimpleIntegerProperty jumlahPemesanan;

        public RoomUsageData(String ruanganName, int jumlahPemesanan) {
            this.ruanganName = new SimpleStringProperty(ruanganName);
            this.jumlahPemesanan = new SimpleIntegerProperty(jumlahPemesanan);
        }

        public SimpleStringProperty ruanganNameProperty() {
            return ruanganName;
        }

        public SimpleIntegerProperty jumlahPemesananProperty() {
            return jumlahPemesanan;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnectionManager.getConnection();
        gedungManager = new GedungManager(connection);
        ruanganManager = new RuanganManager(connection);
        pemesananManager = new PemesananManager(connection);
        LocalDate now = LocalDate.now();
        tanggalAwalPicker.setValue(now.withDayOfMonth(1));
        tanggalAkhirPicker.setValue(now.withDayOfMonth(now.lengthOfMonth()));
        loadGedungComboBox();
        configureTableColumns();
    }

    private void loadGedungComboBox() {
        try {
            List<Gedung> gedungList = getAllGedung();
            Gedung allGedung = new Gedung(-1, "Semua Gedung", "");
            gedungList.add(0, allGedung);
            ObservableList<Gedung> gedungObservableList = FXCollections.observableArrayList(gedungList);
            gedungComboBox.setItems(gedungObservableList);
            gedungComboBox.getSelectionModel().selectFirst();
            gedungComboBox.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Gedung gedung, boolean empty) {
                    super.updateItem(gedung, empty);
                    setText((empty || gedung == null) ? null : gedung.getNama());
                }
            });
            gedungComboBox.setButtonCell(gedungComboBox.getCellFactory().call(null));
        } catch (Exception e) {
            showAlert("Error loading buildings: " + e.getMessage());
        }
    }

    private List<Gedung> getAllGedung() throws SQLException {
        List<Gedung> gedungList = new ArrayList<>();
        String query = "SELECT * FROM gedung";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                gedungList.add(new Gedung(id, nama, alamat));
            }
        }
        return gedungList;
    }

    private void configureTableColumns() {
        ruanganColumn.setCellValueFactory(cellData -> cellData.getValue().ruanganNameProperty());
        jumlahColumn.setCellValueFactory(cellData -> cellData.getValue().jumlahPemesananProperty().asObject());
        laporanTable.setItems(roomUsageDataList);
    }

    @FXML
    public void handleTampilkanLaporan(ActionEvent actionEvent) {
        LocalDate startDate = tanggalAwalPicker.getValue();
        LocalDate endDate = tanggalAkhirPicker.getValue();
        if (startDate == null || endDate == null) {
            showAlert("Silakan pilih rentang tanggal terlebih dahulu.");
            return;
        } if (startDate.isAfter(endDate)) {
            showAlert("Tanggal awal tidak boleh lebih besar dari tanggal akhir.");
            return;
        } try {
            roomUsageDataList.clear();
            Gedung selectedGedung = gedungComboBox.getValue();
            String start = startDate.format(formatter);
            String end = endDate.format(formatter);
            generateReport(selectedGedung, start, end);
        } catch (Exception e) {
            showAlert("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateReport(Gedung selectedGedung, String startDate, String endDate) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.id, r.nama, COUNT(p.id) AS booking_count ")
                .append("FROM ruangan r ")
                .append("LEFT JOIN pemesanan p ON r.id = p.ruangan_id ")
                .append("AND ((p.checkindate BETWEEN ? AND ?) OR (p.checkoutdate BETWEEN ? AND ?)) ");
        if (selectedGedung != null && selectedGedung.getId() != -1) {
            sql.append("WHERE r.gedung_id = ? ");
        }
        sql.append("GROUP BY r.id, r.nama ")
                .append("ORDER BY booking_count DESC, r.nama ASC");
        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setString(3, startDate);
            ps.setString(4, endDate);
            if (selectedGedung != null && selectedGedung.getId() != -1) {
                ps.setInt(5, selectedGedung.getId());
            }
            ResultSet rs = ps.executeQuery();
            int totalBookings = 0;
            while (rs.next()) {
                String roomName = rs.getString("nama");
                int bookingCount = rs.getInt("booking_count");
                roomUsageDataList.add(new RoomUsageData(roomName, bookingCount));
                totalBookings += bookingCount;
            }
            totalLabel.setText(String.valueOf(totalBookings));
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
