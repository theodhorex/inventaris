package org.week11;

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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class DaftarCatatanController implements Initializable {
    public Button btnGrafik;
    @FXML
    private TextField txtFldJudul;
    @FXML
    private TextArea txtAreaKonten;
    @FXML
    private TextField searchBox;
    @FXML
    private Button btnClearSearch;

    private ObservableList<Catatan> catatanObservableList;
    private FilteredList<Catatan> filteredList;

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

    private final String DB_URL = "jdbc:sqlite:catatanku.db";
    private Connection connection;
    Catatan selectedCatatan;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catatanObservableList = FXCollections.observableArrayList();
        filteredList = new FilteredList<>(catatanObservableList, p -> true);
        table.setItems(filteredList);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        judul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));

        getConnection();
        createTable();
        getAllData();

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catatan>() {
            @Override
            public void changed(ObservableValue<? extends Catatan> observableValue, Catatan oldVal, Catatan newVal) {
                if (newVal != null) {
                    selectedCatatan = newVal;
                    txtFldJudul.setText(newVal.getJudul());
                    txtAreaKonten.setText(newVal.getKonten());
                    cbKategori.setValue(newVal.getKategori());
                }
            }
        });

        cbKategori.getItems().addAll(
                Catatan.CATATAN_SELF_DEVELOPEMENT,
                Catatan.CATATAN_BELANJA,
                Catatan.CATATAN_KHUSUS,
                Catatan.CATATAN_PERCINTAAN
        );

        // SEARCH IMPLEMENTATION
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(createPredicate(newValue));
        });

        bersihkan();
    }

    @FXML
    protected void onBtnClearSearch() {
        searchBox.clear();
        filteredList.setPredicate(p -> true);
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS catatan (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "judul TEXT NOT NULL," +
                "konten TEXT NOT NULL," +
                "kategori TEXT NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Predicate<Catatan> createPredicate(String searchText) {
        return catatan -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsCatatan(catatan, searchText);
        };
    }

    private boolean searchFindsCatatan(Catatan catatan, String searchText) {
        return catatan.getJudul().toLowerCase().contains(searchText.toLowerCase()) ||
                catatan.getKonten().toLowerCase().contains(searchText.toLowerCase()) ||
                catatan.getKategori().toLowerCase().contains(searchText.toLowerCase());
    }

    private void getAllData() {
        String query = "SELECT * FROM catatan";
        catatanObservableList.clear();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                String konten = resultSet.getString("konten");
                String kategori = resultSet.getString("kategori");
                Catatan catatan = new Catatan(id, judul, konten, kategori);
                catatanObservableList.add(catatan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        if (selectedCatatan == null) return false;
        return !selectedCatatan.getJudul().equalsIgnoreCase(txtFldJudul.getText()) ||
                !selectedCatatan.getKonten().equalsIgnoreCase(txtAreaKonten.getText()) ||
                !selectedCatatan.getKategori().equalsIgnoreCase(cbKategori.getSelectionModel().getSelectedItem());
    }

    @FXML
    protected void onBtnSimpanClick() {
        if (isCatatanUpdated()) {
            if (updateCatatan(selectedCatatan, new Catatan(
                    selectedCatatan.getId(),
                    txtFldJudul.getText(),
                    txtAreaKonten.getText(),
                    cbKategori.getSelectionModel().getSelectedItem()))) {
                new Alert(Alert.AlertType.INFORMATION, "Catatan Dirubah!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Catatan gagal Dirubah!").show();
            }
        } else {
            if (addCatatan(new Catatan(
                    txtFldJudul.getText(),
                    txtAreaKonten.getText(),
                    cbKategori.getSelectionModel().getSelectedItem()))) {
                new Alert(Alert.AlertType.INFORMATION, "Catatan Ditambahkan!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Catatan gagal Ditambahkan!").show();
            }
        }
        bersihkan();
    }

    @FXML
    protected void onBtnHapus() {
        if (selectedCatatan != null && deleteCatatan(selectedCatatan)) {
            new Alert(Alert.AlertType.INFORMATION, "Catatan Dihapus!").show();
            bersihkan();
        }
    }

    public boolean deleteCatatan(Catatan catatan) {
        String query = "DELETE FROM catatan WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, catatan.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                catatanObservableList.remove(catatan);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean addCatatan(Catatan catatan) {
        String queryInsert = "INSERT INTO catatan (judul, konten, kategori) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, catatan.getJudul());
            stmt.setString(2, catatan.getKonten());
            stmt.setString(3, catatan.getKategori());
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    catatan.setId(rs.getInt(1));
                }
                catatanObservableList.add(catatan);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateCatatan(Catatan oldCatatan, Catatan newCatatan) {
        String query = "UPDATE catatan SET judul = ?, konten = ?, kategori = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newCatatan.getJudul());
            stmt.setString(2, newCatatan.getKonten());
            stmt.setString(3, newCatatan.getKategori());
            stmt.setInt(4, oldCatatan.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                int index = catatanObservableList.indexOf(oldCatatan);
                catatanObservableList.set(index, newCatatan);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    protected void onMenuGrafikClicked(ActionEvent event) {
        try {
            Apps.openViewWithModal("grafik-view", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onBtnCloseClick() {
        Platform.exit();
    }
}
