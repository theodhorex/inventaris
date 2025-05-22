package org.week12;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.week12.data.Catatan;
import org.week12.util.CatatanDao;
import org.week12.util.DatabaseUtil;
import org.week12.util.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class DaftarCatatanController implements Initializable {
    @FXML
    private TextField txtFldJudul;
    @FXML
    private TextArea txtAreaKonten;
    private FilteredList<Catatan> catatanFilteredList;
    @FXML
    private TableView<Catatan> table;
    @FXML
    private TableColumn<Catatan, String> id;
    @FXML
    private TableColumn<Catatan, String> judul;
    @FXML
    private TableColumn<Catatan, String> kategori;
    @FXML
    private ChoiceBox<String> cbKategori;
    @FXML
    public TextField searchBox;
    Catatan selectedCatatan;
    private CatatanDao catatanDao;
    private DatabaseUtil dbUtil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbUtil = new DatabaseUtil();
        catatanDao = new CatatanDao(dbUtil);
        catatanFilteredList = new FilteredList<>(FXCollections.observableList(FXCollections.observableArrayList()));
        table.setItems(catatanFilteredList);
        searchBox.textProperty().addListener(
                (observableValue, oldValue, newValue) -> catatanFilteredList.setPredicate(createPredicate(newValue))
        );

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        judul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        getAllData();
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catatan>() {
            @Override
            public void changed(ObservableValue<? extends Catatan> observableValue, Catatan course, Catatan t1) {
                if (observableValue.getValue() != null) {
                    selectedCatatan = observableValue.getValue();
                    txtFldJudul.setText(observableValue.getValue().getJudul());
                    txtAreaKonten.setText(observableValue.getValue().getKonten());
                    cbKategori.setValue(observableValue.getValue().getKategori());
                }
            }
        });

        //setup combo box
        cbKategori.getItems().add(Catatan.CATATAN_SELF_DEVELOPEMENT);
        cbKategori.getItems().add(Catatan.CATATAN_BELANJA);
        cbKategori.getItems().add(Catatan.CATATAN_KHUSUS);
        cbKategori.getItems().add(Catatan.CATATAN_PERCINTAAN);
        bersihkan();
    }

    private ObservableList<Catatan> getObservableList() {
        return (ObservableList<Catatan>) catatanFilteredList.getSource();
    }

    private Predicate<Catatan> createPredicate(String searchText) {
        return catatan -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsCatatan(catatan, searchText);
        };
    }

    private boolean searchFindsCatatan(Catatan catatan, String searchText) {
        return (catatan.getJudul().toLowerCase().contains(searchText.toLowerCase())) ||
                (catatan.getKonten().toLowerCase().contains(searchText.toLowerCase())) ||
                (catatan.getKategori().toLowerCase().contains(searchText.toLowerCase()));
    }

    private void getAllData() {
        getObservableList().clear();
        getObservableList().addAll(CatatanDao.getAllDataCatatan());
    }

    private void bersihkan() {
        txtFldJudul.clear();
        txtAreaKonten.clear();
        txtFldJudul.requestFocus();
        table.getSelectionModel().clearSelection();
        selectedCatatan = null;
        cbKategori.getSelectionModel().clearSelection();
        cbKategori.getSelectionModel().select(0);
    }

    private boolean isCatatanUpdated() {
        if (selectedCatatan == null) {
            return false;
        }
        if (!selectedCatatan.getJudul().equalsIgnoreCase(txtFldJudul.getText()) ||
                !selectedCatatan.getKonten().equalsIgnoreCase(txtAreaKonten.getText()) ||
                !selectedCatatan.getKategori().equalsIgnoreCase(cbKategori.getSelectionModel().getSelectedItem())) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    protected void onBtnSimpanClick() {
        if (isCatatanUpdated()) {
            if (updateCatatan(selectedCatatan, new Catatan(selectedCatatan.getId(), txtFldJudul.getText(), txtAreaKonten.getText(), cbKategori.getSelectionModel().getSelectedItem()))) {
                new Alert(Alert.AlertType.INFORMATION, "Catatan Dirubah!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Catatan gagal Dirubah!").show();
            }
        } else {
            if (addCatatan(new Catatan(txtFldJudul.getText(), txtAreaKonten.getText(), cbKategori.getSelectionModel().getSelectedItem()))) {
                new Alert(Alert.AlertType.INFORMATION, "Catatan Ditambahkan!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Catatan gagal Ditambahkan!").show();
            }
        }
        bersihkan();
    }

    @FXML
    protected void onBtnGrafik() throws IOException {
        Apps.openViewWithModal("pie-chart-view", "Pie Chart", false);
    }

    @FXML
    public void handleClearSearchText(ActionEvent event) {
        searchBox.setText("");
        event.consume();
    }

    @FXML
    protected void onBtnHapus() {
        if (selectedCatatan != null && deleteCatatan(selectedCatatan)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Catatan Dihapus!");
            alert.show();
            bersihkan();
        }
    }

    public boolean deleteCatatan(Catatan catatan) {
        if (CatatanDao.deleteCatatan(catatan)) {
            getObservableList().remove(catatan);
            return true;
        }
        return false;
    }

    private boolean addCatatan(Catatan catatan) {
        if (CatatanDao.addCatatan(catatan)) {
            getObservableList().add(catatan);
            return true;
        }
        return false;
    }

    private boolean updateCatatan(Catatan oldCatatan, Catatan newCatatan) {
        if (CatatanDao.updateCatatan(oldCatatan, newCatatan)) {
            int iOldCatatan = getObservableList().indexOf(oldCatatan);
            getObservableList().set(iOldCatatan, newCatatan);
            return true;
        }
        return false;
    }

    @FXML
    protected void onActionMenuLogOut() throws IOException {
        SessionManager.logout();
        if (!SessionManager.isLoggedIn()) {
            Apps.setRoot("login-view", "Login", false);
        }
    }

    @FXML
    protected void onActionMenuAbout() throws IOException {
        Apps.openViewWithModal("about-view", "About Program", false);
    }
}
