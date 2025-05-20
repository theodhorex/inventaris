package com.example.minggu10;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class MahasiswaViewController implements Initializable {
    @FXML public TextField txtNim;
    @FXML public TextField txtNama;
    @FXML public TextField txtNilai;
    @FXML public TableView<Mahasiswa> table;
    @FXML public TableColumn<Mahasiswa, String> nim;
    @FXML public TableColumn<Mahasiswa, String> nama;
    @FXML public TableColumn<Mahasiswa, Float> nilai;
    @FXML public TextField searchBox;
    @FXML public ImageView imageView;

    private ObservableList<Mahasiswa> mahasiswaObservableList;
    private FilteredList<Mahasiswa> mahasiswaFilteredList;

    private byte[] currentImageBytes; // Menyimpan foto yang sedang dipilih

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mahasiswaObservableList = FXCollections.observableArrayList();
        mahasiswaFilteredList = new FilteredList<>(mahasiswaObservableList);

        table.setItems(mahasiswaObservableList);
        nim.setCellValueFactory(new PropertyValueFactory<>("Nim"));
        nama.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        nilai.setCellValueFactory(new PropertyValueFactory<>("Nilai"));

        table.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Mahasiswa> obs, Mahasiswa oldVal, Mahasiswa newVal) -> {
                    if (newVal != null) {
                        txtNim.setText(newVal.getNim());
                        txtNama.setText(newVal.getNama());
                        txtNilai.setText(String.valueOf(newVal.getNilai()));
                        currentImageBytes = newVal.getFoto();
                        if (currentImageBytes != null) {
                            imageView.setImage(new Image(new ByteArrayInputStream(currentImageBytes)));
                        } else {
                            imageView.setImage(null);
                        }
                    }
                });

        searchBox.textProperty().addListener((obs, oldVal, newVal) -> {
            mahasiswaFilteredList.setPredicate(createPredicate(newVal));
            table.setItems(mahasiswaFilteredList);
        });
    }

    private Predicate<Mahasiswa> createPredicate(String searchText) {
        return mahasiswa -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return mahasiswa.getNim().toLowerCase().contains(searchText.toLowerCase()) ||
                    mahasiswa.getNama().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    public void onBtnAddClick(ActionEvent actionEvent) {
        Mahasiswa selected = table.getSelectionModel().getSelectedItem();
        Mahasiswa newData = new Mahasiswa(
                txtNim.getText(),
                txtNama.getText(),
                Float.parseFloat(txtNilai.getText()),
                currentImageBytes
        );

        if (selected != null && isMahasiswaChanged()) {
            if (updateMahasiswa(selected, newData)) {
                new Alert(Alert.AlertType.INFORMATION, "Data Updated").show();
            }
        } else {
            addMahasiswa(newData);
            new Alert(Alert.AlertType.INFORMATION, "Data added").show();
        }
        bersihkan();
    }

    private boolean updateMahasiswa(Mahasiswa oldMhs, Mahasiswa newMhs) {
        int indexOld = mahasiswaObservableList.indexOf(oldMhs);
        mahasiswaObservableList.set(indexOld, newMhs);
        return true;
    }

    private boolean isMahasiswaChanged() {
        Mahasiswa selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return false;
        return !selected.getNim().equalsIgnoreCase(txtNim.getText()) ||
                !selected.getNama().equalsIgnoreCase(txtNama.getText()) ||
                selected.getNilai() != Float.parseFloat(txtNilai.getText());
    }

    private void bersihkan() {
        txtNim.clear();
        txtNama.clear();
        txtNilai.clear();
        imageView.setImage(null);
        currentImageBytes = null;
        table.getSelectionModel().clearSelection();
        table.setItems(mahasiswaObservableList);
    }

    private void addMahasiswa(Mahasiswa mahasiswa) {
        mahasiswaObservableList.add(mahasiswa);
    }

    public void onBtnHapusClick(ActionEvent actionEvent) {
        Mahasiswa selected = table.getSelectionModel().getSelectedItem();
        if (selected != null && deleteMahasiswa(selected)) {
            new Alert(Alert.AlertType.INFORMATION, "Data Mahasiswa dihapus").show();
            bersihkan();
        }
    }

    private boolean deleteMahasiswa(Mahasiswa mhs) {
        return mahasiswaObservableList.remove(mhs);
    }

    public void onBtnSaveFileClick(ActionEvent actionEvent) {
        // Tambahkan jika ingin menyimpan data ke file
    }

    public void onBtnCloseClick(ActionEvent actionEvent) {
        // Tambahkan jika ingin menutup aplikasi
    }

    public void onOpenBtnClick(ActionEvent actionEvent) {
        // Tambahkan jika ingin membuka data dari file
    }

    public void onMenuAboutClicked(ActionEvent actionEvent) {
        HelloApplication.openViewWithModal("hello-view", false);
    }

    public void onClickBersihkan(ActionEvent actionEvent) {
        bersihkan();
    }

    public void onBtnUbahFoto(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Foto");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                currentImageBytes = bos.toByteArray();
                imageView.setImage(new Image(new ByteArrayInputStream(currentImageBytes)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
