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
    //tes
    @FXML
    private TextField txtFldJudul;
    @FXML
    private TextArea txtAreaKonten;
    private ObservableList<Catatan> catatanObservableList;
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
    private TextField searchBox;
    private final String DB_URL = "jdbc:sqlite:catatanku.db";
    private Connection connection;
    Catatan selectedCatatan;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catatanObservableList = FXCollections.observableArrayList();
        table.setItems(catatanObservableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        judul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        getConnection();
        createTable();
        getAllData();

        FilteredList<Catatan> filteredList = new FilteredList<>(catatanObservableList, p -> true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(createPredicate(newValue));
        });

        table.setItems(filteredList);
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

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database connection error
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
                // Handle database connection closure error
            }
        }
    }

    /* Create database tables if they don't exist
     * */
    public void createTable() {
        String mhsTableSql = "CREATE TABLE IF NOT EXISTS catatan ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "judul TEXT NOT NULL,"
                + "konten TEXT NOT NULL,"
                + "kategori TEXT NOT NULL"
                + ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(mhsTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle table creation error
        }
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
                catatanObservableList.addAll(catatan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Catatan Dirubah!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Catatan gagal Dirubah!");
                alert.show();
            }
        } else {
            if (addCatatan(new Catatan(txtFldJudul.getText(), txtAreaKonten.getText(), cbKategori.getSelectionModel().getSelectedItem()))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Catatan Ditambahkan!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Catatan gagal Ditambahkan!");
                alert.show();
            }
        }
        bersihkan();
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
        String queryGetNextId = "SELECT seq FROM SQLITE_SEQUENCE WHERE name = 'catatan' LIMIT 1";
        String queryInsert = "INSERT INTO catatan (judul, konten, kategori) VALUES (?, ?, ?)";
        try {
            connection.setAutoCommit(false); // Start transaction
            try (PreparedStatement getNextIdStatement = connection.prepareStatement(queryGetNextId);
                 PreparedStatement insertStatement = connection.prepareStatement(queryInsert)) {
                // Execute query to get the next ID
                ResultSet resultSet = getNextIdStatement.executeQuery();
                int nextId = 1; // Default value if no rows are returned
                if (resultSet.next()) {
                    nextId = resultSet.getInt("seq") + 1;
                }
                // Set parameters for insert query
                insertStatement.setString(1, catatan.getJudul());
                insertStatement.setString(2, catatan.getKonten());
                insertStatement.setString(3, catatan.getKategori());
                // Execute insert query
                int rowsAffected = insertStatement.executeUpdate();

                if (rowsAffected > 0) {
                    catatan.setId(nextId);
                    catatanObservableList.add(catatan);
                    connection.commit(); // Commit transaction
                    return true;
                }
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction
                e.printStackTrace();
                // Handle database query error
            } finally {
                connection.setAutoCommit(true); // Reset auto-commit mode
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private boolean updateCatatan(Catatan oldCatatan, Catatan newCatatan) {
        String query = "UPDATE catatan SET judul = ?, konten = ?,  kategori = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newCatatan.getJudul());
            preparedStatement.setString(2, newCatatan.getKonten());
            preparedStatement.setString(3, newCatatan.getKategori());
            preparedStatement.setInt(4, oldCatatan.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                int iOldCatatan = catatanObservableList.indexOf(oldCatatan);
                catatanObservableList.set(iOldCatatan, newCatatan);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
        }
        return false;
    }

    @FXML
    protected void onMenuGrafikClicked(ActionEvent event) {
        try {
            Apps.openViewWithModal("piechart-view", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onBtnCloseClick() {
        Platform.exit();
    }
}