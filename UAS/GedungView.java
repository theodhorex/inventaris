package org.ukdw.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ukdw.data.Gedung;
import org.ukdw.managers.GedungManager;
import org.ukdw.utils.DBConnectionManager;

import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;

public class GedungView implements Initializable {
    @FXML private TextField idTxtFld;
    @FXML private TextField titleTxtFld;
    @FXML private TextArea descriptionTxtArea;
    @FXML private TextField searchBox;
    @FXML private TableView<Gedung> table;
    @FXML private TableColumn<Gedung, Integer> tblColId;
    @FXML private TableColumn<Gedung, String> tblColTitle;
    @FXML private TableColumn<Gedung, String> tblColDescription;

    private GedungManager gedungManager;
    private ObservableList<Gedung> daftarGedung = FXCollections.observableArrayList();
    private Gedung selectedGedung;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn = DBConnectionManager.getConnection();
        gedungManager = new GedungManager(conn);

        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColTitle.setCellValueFactory(new PropertyValueFactory<>("nama"));
        tblColDescription.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        table.setItems(daftarGedung);
        loadGedungData();

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedGedung = newVal;
            if (newVal != null) {
                idTxtFld.setText(String.valueOf(newVal.getId()));
                titleTxtFld.setText(newVal.getNama());
                descriptionTxtArea.setText(newVal.getAlamat());
            }
        });
    }

    private void loadGedungData() {
        daftarGedung.setAll(gedungManager.getAllGedung());
    }

    private void clearInput() {
        idTxtFld.clear();
        titleTxtFld.clear();
        descriptionTxtArea.clear();
        table.getSelectionModel().clearSelection();
        selectedGedung = null;
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    public void handleAddAction(ActionEvent event) {
        String nama = titleTxtFld.getText().trim();
        String alamat = descriptionTxtArea.getText().trim();

        if (nama.isEmpty() || alamat.isEmpty()) {
            showAlert("Nama dan alamat gedung harus diisi.");
            return;
        }

        if (gedungManager.addGedung(nama, alamat)) {
            showAlert("Gedung berhasil ditambahkan.");
            loadGedungData();
            clearInput();
        } else {
            showAlert("Gagal menambahkan gedung.");
        }
    }

    @FXML
    public void handleEditAction(ActionEvent event) {
        if (selectedGedung == null) {
            showAlert("Pilih gedung yang akan diubah.");
            return;
        }

        String newNama = titleTxtFld.getText().trim();
        String newAlamat = descriptionTxtArea.getText().trim();

        if (newNama.isEmpty() || newAlamat.isEmpty()) {
            showAlert("Nama dan alamat tidak boleh kosong.");
            return;
        }

        if (gedungManager.editGedung(selectedGedung.getId(), newNama, newAlamat)) {
            showAlert("Gedung berhasil diperbarui.");
            loadGedungData();
            clearInput();
        } else {
            showAlert("Gagal memperbarui gedung.");
        }
    }

    @FXML
    public void handleDeleteAction(ActionEvent event) {
        if (selectedGedung == null) {
            showAlert("Pilih gedung yang ingin dihapus.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Yakin ingin menghapus gedung ini?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (gedungManager.deleteGedung(selectedGedung.getId())) {
                showAlert("Gedung berhasil dihapus.");
                loadGedungData();
                clearInput();
            } else {
                showAlert("Gagal menghapus gedung.");
            }
        }
    }

    @FXML
    public void handleClearSearchText(ActionEvent event) {
        searchBox.clear();
        loadGedungData();
    }
}
