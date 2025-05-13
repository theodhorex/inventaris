package com.example.minggu10;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class MahasiswaViewController implements Initializable {
    @FXML
    public TextField txtNim;
    @FXML
    public TextField txtNama;
    @FXML
    public TextField txtNilai;
    @FXML
    public TableView<Mahasiswa> table;
    @FXML
    public TableColumn<Mahasiswa, String> nim;
    @FXML
    public TableColumn<Mahasiswa, String> nama;
    @FXML
    public TableColumn<Mahasiswa, Float> nilai;
    @FXML
    public TextField searchBox;
    @FXML
    public ImageView imageView;

    private ObservableList<Mahasiswa> mahasiswaObservableList; //master data

    private FilteredList<Mahasiswa> mahasiswaFilteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mahasiswaObservableList = FXCollections.observableArrayList();
        mahasiswaFilteredList = new FilteredList<>(FXCollections.observableList(mahasiswaObservableList));

        table.setItems(mahasiswaObservableList);
        nim.setCellValueFactory(new PropertyValueFactory<>("Nim"));
        nama.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        nilai.setCellValueFactory(new PropertyValueFactory<>("Nilai"));

        table.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Mahasiswa>() {
                    @Override
                    public void changed(ObservableValue<? extends Mahasiswa> observableValue, Mahasiswa mahasiswa, Mahasiswa t1) {
                        if (observableValue.getValue() != null) {
                            txtNim.setText(observableValue.getValue().getNim());
                            txtNama.setText(observableValue.getValue().getNama());
                            txtNilai.setText("" + observableValue.getValue().getNilai());
                        }
                    }
                });

        searchBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//                table.setItems(filterList(mahasiswaObservableList, newValue));
                mahasiswaFilteredList.setPredicate(createPredicate(newValue));
                table.setItems(mahasiswaFilteredList);
            }
        });
    }

    private Predicate<? super Mahasiswa> createPredicate(String searchText) {
        return new Predicate<Mahasiswa>() {
            @Override
            public boolean test(Mahasiswa mahasiswa) {
                if(searchText == null || searchText.isEmpty()) return true;
                return searchMahasiswa(mahasiswa, searchText);
            }
        };
    }

    private ObservableList<Mahasiswa> filterList(ObservableList<Mahasiswa> mahasiswaObservableList,
                                                 String searchText) {
        List<Mahasiswa> filteredList = new ArrayList<>();
        for (Mahasiswa mahasiswa : mahasiswaObservableList) {
            if (searchMahasiswa(mahasiswa, searchText)) filteredList.add(mahasiswa);
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean searchMahasiswa(Mahasiswa mahasiswa, String searchText) {
        return (mahasiswa.getNim().toLowerCase().contains(searchText.toLowerCase()) ||
                mahasiswa.getNama().toLowerCase().contains(searchText.toLowerCase()));
    }

    public void onBtnAddClick(ActionEvent actionEvent) {
        if (isMahasiswaChanged()) {
            if (updateMahasiswa(table.getSelectionModel().getSelectedItem(),
                    new Mahasiswa(
                            txtNim.getText(),
                            txtNama.getText(),
                            Float.parseFloat(txtNilai.getText())))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Data Updated");
                alert.show();
            }
        } else {
            addMahasiswa(new Mahasiswa(
                    txtNim.getText(),
                    txtNama.getText(),
                    Float.parseFloat(txtNilai.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Data added");
            alert.show();
        }
        bersihkan();
    }

    private boolean updateMahasiswa(Mahasiswa oldMhs, Mahasiswa newMhs) {
        int indexOldMhs = mahasiswaObservableList.indexOf(oldMhs);
        mahasiswaObservableList.set(indexOldMhs, newMhs);
        return true;
    }

    private boolean isMahasiswaChanged() {
        Mahasiswa selectedMhs = table.getSelectionModel().getSelectedItem();
        if (selectedMhs == null)
            return false;

        return !selectedMhs.getNim().equalsIgnoreCase(txtNim.getText()) ||
                !selectedMhs.getNama().equalsIgnoreCase(txtNama.getText()) ||
                selectedMhs.getNilai() != Float.parseFloat(txtNilai.getText());
    }

    private void bersihkan() {
        txtNilai.clear();
        txtNama.clear();
        txtNim.clear();
        table.getSelectionModel().select(null);
        table.setItems(mahasiswaObservableList);
    }

    private void addMahasiswa(Mahasiswa mahasiswa) {
        //validasi input!
        mahasiswaObservableList.add(mahasiswa);
    }

    public void onBtnHapusClick(ActionEvent actionEvent) {
        if (deleteMahasiswa(table.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Mahasiswa dihapus");
            alert.show();
            bersihkan();
        }
    }

    private boolean deleteMahasiswa(Mahasiswa selectedItem) {
        return mahasiswaObservableList.remove(selectedItem);
    }

    public void onBtnSaveFileClick(ActionEvent actionEvent) {

    }

    public void onBtnCloseClick(ActionEvent actionEvent) {

    }

    public void onOpenBtnClick(ActionEvent actionEvent) {

    }

    public void onMenuAboutClicked(ActionEvent actionEvent) {
        HelloApplication.openViewWithModal("hello-view", false);
    }

    public void onClickBersihkan(ActionEvent actionEvent) {

    }

    public void onBtnUbahFoto(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Foto");
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            try(FileInputStream fileInputStream = new FileInputStream(file)){
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fileInputStream.read(buffer)) != -1){
                    bos.write(buffer, 0, len);
                }
                byte[] imageByte = bos.toByteArray();
                imageView.setImage(new Image(new ByteArrayInputStream(imageByte)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
