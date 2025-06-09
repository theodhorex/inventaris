package org.ukdw.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ukdw.data.Ruangan;
import org.ukdw.managers.RuanganManager;
import org.ukdw.utils.DBConnectionManager;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class RuanganView implements Initializable {

    @FXML
    private TextField namaIdTxtFld;

    @FXML
    private ChoiceBox<String> cbGedungId;

    @FXML
    private Label lblKelas;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnUbah;

    @FXML
    private TextField searchBox;

    @FXML
    private TableView<Ruangan> table;

    @FXML
    private TableColumn<Ruangan, Integer> colKelasId;

    @FXML
    private TableColumn<Ruangan, String> colNameKelas;

    @FXML
    private TableColumn<Ruangan, Integer> colLokasi;

    private final ObservableList<Ruangan> ruanganList = FXCollections.observableArrayList();

    private final Map<String, Integer> namaGedungToId = new HashMap<>();
    private final Map<Integer, String> idGedungToNama = new HashMap<>();

    private Ruangan selectedRuangan = null;

    private RuanganManager ruanganManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialisasi RuanganManager
        ruanganManager = new RuanganManager(DBConnectionManager.getConnection()); // Pastikan menggunakan koneksi DB yang benar

        // Inisialisasi data gedung
        namaGedungToId.put("Gedung A", 1);
        namaGedungToId.put("Gedung B", 2);
        namaGedungToId.put("Gedung C", 3);
        for (Map.Entry<String, Integer> entry : namaGedungToId.entrySet()) {
            idGedungToNama.put(entry.getValue(), entry.getKey());
        }

        cbGedungId.setItems(FXCollections.observableArrayList(namaGedungToId.keySet()));

        colKelasId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNameKelas.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLokasi.setCellValueFactory(new PropertyValueFactory<>("idGedung"));

        table.setItems(ruanganList);

        // Menambahkan listener pada searchBox untuk menangani pencarian
        searchBox.textProperty().addListener((obs, oldText, newText) -> {
            if (newText == null || newText.trim().isEmpty()) {
                loadRuangan();  // Memuat ulang data ketika search kosong
            } else {
                List<Ruangan> filtered = ruanganManager.searchRuanganByKeyword(newText.trim());  // Memanggil search di RuanganManager
                table.getItems().setAll(filtered);
            }
        });

        loadRuangan();  // Memuat data ruangan ketika aplikasi pertama kali dijalankan
    }

    private void loadRuangan() {
        List<Ruangan> ruangans = ruanganManager.getAllRuangan();  // Memanggil data ruangan dari RuanganManager
        System.out.println("Number of ruangan retrieved: " + ruangans.size());
        ruanganList.setAll(ruangans);  // Mengatur list ruangan ke table
    }

    public void tampilRuangan(ActionEvent actionEvent) {
        String selectedGedung = cbGedungId.getValue();

        if (selectedGedung == null || selectedGedung.isEmpty()) {
            table.setItems(ruanganList);
            return;
        }
        int idGedung = namaGedungToId.get(selectedGedung);
        ObservableList<Ruangan> filteredList = ruanganList.filtered(r -> r.getIdGedung() == idGedung);
        table.setItems(filteredList);
    }

    public void handleAddAction(ActionEvent actionEvent) {
        String namaRuangan = namaIdTxtFld.getText();
        String namaGedung = cbGedungId.getValue();

        if (namaRuangan.isEmpty() || namaGedung == null) {
            showAlert("Input salah", "Nama Ruangan dan Gedung harus diisi!");
            return;
        }
        int idGedung = namaGedungToId.get(namaGedung);

        // Menambahkan ruangan baru ke database dan ke tabel
        boolean success = ruanganManager.addRuangan(namaRuangan, idGedung);
        if (success) {
            loadRuangan();  // Refresh daftar ruangan setelah penambahan
            clearForm();
        } else {
            showAlert("Gagal", "Gagal menambahkan ruangan");
        }
    }

    public void handleDeleteAction(ActionEvent actionEvent) {
        if (selectedRuangan != null) {
            boolean success = ruanganManager.deleteRuangan(selectedRuangan.getId());
            if (success) {
                loadRuangan();  // Refresh daftar ruangan setelah penghapusan
                clearForm();
            } else {
                showAlert("Gagal", "Gagal menghapus ruangan");
            }
        } else {
            showAlert("Pilih Data", "Pilih ruangan yang ingin dihapus!");
        }
    }

    public void handleEditAction(ActionEvent actionEvent) {
        if (selectedRuangan != null) {
            String nama = namaIdTxtFld.getText();
            String namaGedung = cbGedungId.getValue();
            if (nama.isEmpty() || namaGedung == null) {
                showAlert("Input Salah", "Nama ruangan dan gedung harus diisi!");
                return;
            }
            boolean success = ruanganManager.editRuangan(nama, namaGedungToId.get(namaGedung), selectedRuangan.getId());
            if (success) {
                loadRuangan();  // Refresh daftar ruangan setelah perubahan
                clearForm();
            } else {
                showAlert("Gagal", "Gagal mengubah ruangan");
            }
        } else {
            showAlert("Pilih Data", "Pilih ruangan yang ingin diubah!");
        }
    }

    public void handleClearSearchText(ActionEvent actionEvent) {
        searchBox.clear();
        table.setItems(ruanganList);
    }

    private void clearForm() {
        namaIdTxtFld.clear();
        cbGedungId.setValue(null);
        selectedRuangan = null;
        table.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}