package org.example.latihan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.example.latihan.Mahasiswa;
import org.example.latihan.ManagerMahasiswa;
import org.example.latihan.RegistrasiMahasiswa;
import javax.imageio.ImageIO;
public class MahasiswaViewController implements Initializable {
    @FXML
    public TableView<Mahasiswa> tblView;
    @FXML
    private TableColumn<Mahasiswa, String> colNIM;
    @FXML
    private TableColumn<Mahasiswa, String> colNama;
    @FXML
    private TableColumn<Mahasiswa, Double> colNilai;
    @FXML
    private TextField txtNim;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtNilai;
    @FXML
    public TextField txtNamaCari;
    @FXML
    private Label lblInfo;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private ImageView imgFoto;
    private byte[] selectedMahasiswaFotoBlob;
    private ManagerMahasiswa manager;
    private ObservableList<Mahasiswa> masterData;
    private FilteredList<Mahasiswa> mahasiswaFilteredList;
    private Mahasiswa selectedMahasiswa;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.manager = new ManagerMahasiswa();
        this.masterData = FXCollections.observableArrayList(); // ← list utama
        this.mahasiswaFilteredList = new FilteredList<>(masterData, p ->
                true); // ← filtered list dari master

        tblView.getSelectionModel().selectedItemProperty().addListener((obs,
                                                                        oldVal, newVal) -> {
            if (newVal != null) {
                selectedMahasiswa = newVal;
                txtNim.setText(newVal.getNim());
                txtNama.setText(newVal.getNama());
                txtNilai.setText(String.valueOf(newVal.getNilai()));
                txtNim.setDisable(true); // NIM tidak boleh diubah saat edit
                        selectedMahasiswaFotoBlob = newVal.getFoto();

                imgFoto.setImage(convertBytesToImage(selectedMahasiswaFotoBlob));
            }
        });
        SortedList<Mahasiswa> sortedData = new
                SortedList<>(mahasiswaFilteredList);

        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);
        txtNamaCari.textProperty().addListener((
                (obs, oldValue, newValue) ->
                {

                    mahasiswaFilteredList.setPredicate(createPredicate(newValue));
                }
        ));
        Image image = new Image("https://via.placeholder.com/100"); // pakai image online
//                Image(Objects.requireNonNull(RegistrasiMahasiswa.class.getResourceAsStream("default_img.png")));
        imgFoto.setImage(image);
        displayList();
    }
    private Predicate<? super Mahasiswa> createPredicate(String
                                                                 searchText) {
        return order -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsMahasiswa(order, searchText);
        };
    }
    private boolean searchFindsMahasiswa(Mahasiswa mahasiswa, String
            searchText) {
        return
                (mahasiswa.getNim().toLowerCase().contains(searchText.toLowerCase())) ||

                        (mahasiswa.getNama().toLowerCase().contains(searchText.toLowerCase()));
    }
    private void displayList() {
        tblView.setEditable(false);
        colNIM.setCellValueFactory(new PropertyValueFactory<>("nim"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colNilai.setCellValueFactory(new PropertyValueFactory<>("nilai"));
        masterData.clear();
        masterData.addAll(manager.getAllMahasiswa());
        updateInfo();
    }
    private void updateInfo() {
        double rata = 0;
        for (Mahasiswa m : masterData) {
            rata += m.getNilai();
        }
        rata = !masterData.isEmpty() ? rata / masterData.size() : 0;
        lblInfo.setText("Jumlah data: " + masterData.size() + ", Rata nilai: " + rata);
    }
    private void bersihkan() {
        txtNim.clear();
        txtNama.clear();
        txtNilai.clear();
        txtNim.setDisable(false);
        // Display the image in imageView
        Image image = new
                Image(Objects.requireNonNull(RegistrasiMahasiswa.class.getResourceAsStream
                ("default_img.png")));
        imgFoto.setImage(image);
        tblView.getSelectionModel().clearSelection();
        selectedMahasiswa = null;
        updateInfo();
    }
    @FXML
    public void onBtnAddClick(ActionEvent actionEvent) {
        Mahasiswa newMahasiswa = new Mahasiswa(txtNim.getText(),
                txtNama.getText(),
                Double.parseDouble(txtNilai.getText()),
                selectedMahasiswaFotoBlob);
        if (manager.tambahMahasiswa(newMahasiswa)) {
            masterData.add(newMahasiswa);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Mahasiswa Ditambahkan!");
            alert.show();
            bersihkan();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Mahasiswa gagal Ditambahkan!");
            alert.show();
            bersihkan();
        }
    }
    @FXML
    public void onBtnSimpanClick(ActionEvent actionEvent) {
        if (selectedMahasiswa == null) {
            new Alert(Alert.AlertType.WARNING, "Pilih data dari tabel untuk diperbarui.").show();
            return;
        }
        Mahasiswa updatedMahasiswa = new Mahasiswa(txtNim.getText(),
                txtNama.getText(),
                Double.parseDouble(txtNilai.getText()),
                selectedMahasiswaFotoBlob);
        if (manager.updateMahasiswa(updatedMahasiswa)) {
            selectedMahasiswa.setNama(updatedMahasiswa.getNama());
            selectedMahasiswa.setNilai(updatedMahasiswa.getNilai());
            selectedMahasiswa.setFoto(updatedMahasiswa.getFoto());
            tblView.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Mahasiswa Diperbarui!");
            alert.show();
            bersihkan();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Mahasiswa gagal Diperbarui!");
            alert.show();
            bersihkan();
        }
    }
    @FXML
    public void onBtnHapusClick(ActionEvent actionEvent) {
        if (selectedMahasiswa == null) {
            new Alert(Alert.AlertType.WARNING, "Pilih data dari tabel untuk dihapus.").show();
            return;
        }
        if (manager.hapusMahasiswa(selectedMahasiswa.getNim())) {
            masterData.remove(selectedMahasiswa);
            new Alert(Alert.AlertType.INFORMATION, "Data Mahasiswa Dihapus!").show();
            bersihkan();
        } else {
            new Alert(Alert.AlertType.ERROR, "Data Mahasiswa gagal Dihapus!").show();
        }
    }
    @FXML
    protected void onBtnPilihFotoClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Foto Mahasiswa");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg",
                        "*.jpeg", "*.png")
        );
        File file =
                fileChooser.showOpenDialog(imgFoto.getScene().getWindow());
        if (file != null) {
            selectedMahasiswaFotoBlob = resizeImageToBytes(file, 100,
                    150);
            imgFoto.setImage(new Image(file.toURI().toString()));
        }
    }
    private byte[] resizeImageToBytes(File file, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(file);
            BufferedImage resizedImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage.getScaledInstance(width, height,
                    java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            g2d.dispose();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Image convertBytesToImage(byte[] bytes) {
        if (bytes == null) return null;
        return new Image(new ByteArrayInputStream(bytes));
    }
    @FXML
    public void onLineChartCLicked() throws IOException {
        RegistrasiMahasiswa.openViewWithModal("line-chart-view", false);
    }
    @FXML
    public void onPieChartCLicked(ActionEvent actionEvent) throws
            IOException {
        RegistrasiMahasiswa.openViewWithModal("pie-chart-view", false);
    }
}
