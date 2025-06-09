package org.ukdw.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.ukdw.data.Pemesanan;
import org.ukdw.data.Ruangan;
import org.ukdw.managers.PemesananManager;
import org.ukdw.managers.RuanganManager;
import org.ukdw.utils.DBConnectionManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class PemesananView implements Initializable {

    @FXML
    private Button btnUbah;
    @FXML
    private ChoiceBox<Ruangan> ruanganChoiceBox;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker tanggalCheckInPicker;
    @FXML
    private DatePicker tanggalCheckOutPicker;
    @FXML
    private Spinner<LocalTime> jamCheckInSpinner;
    @FXML
    private Spinner<LocalTime> jamCheckOutSpinner;
    @FXML
    private TextField searchBox;

    @FXML
    private TableView<Pemesanan> table;
    @FXML
    private TableColumn<Pemesanan, String> colId;
    @FXML
    private TableColumn<Pemesanan, String> colIdStudent;
    @FXML
    private TableColumn<Pemesanan, String> colIdKelas;
    @FXML
    private TableColumn<Pemesanan, String> colIdKelas1;

    private final ObservableList<Pemesanan> data = FXCollections.observableArrayList();

    Connection connection = DBConnectionManager.getConnection();
    PemesananManager pemesananManager = new PemesananManager(connection);
    RuanganManager ruanganManager = new RuanganManager(connection);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setup tabel
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        colIdStudent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserEmail()));
        colIdKelas.setCellValueFactory(cellData -> {
            int idRuangan = cellData.getValue().getIdRuangan();
            String namaRuangan = ruanganManager.getRuanganNameById(idRuangan);
            return new SimpleStringProperty(namaRuangan);
        });
        colIdKelas1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheckInDate()));

        data.addAll(pemesananManager.getAllPemesanan());
        table.setItems(data);

        // Isi choicebox ruangan dari DB
        RuanganManager ruanganManager = new RuanganManager(connection);
        ObservableList<Ruangan> daftarRuangan = FXCollections.observableArrayList(ruanganManager.getAllRuangan());
        ruanganChoiceBox.setItems(daftarRuangan);

        ruanganChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Ruangan ruangan) {
                return ruangan != null ? ruangan.getName() : "";
            }

            @Override
            public Ruangan fromString(String s) {
                return null;
            }
        });

        SpinnerValueFactory<LocalTime> checkInFactory = new SpinnerValueFactory<>() {
            {
                setConverter(new StringConverter<>() {
                    @Override
                    public String toString(LocalTime time) {
                        return time != null ? time.toString() : "";
                    }

                    @Override
                    public LocalTime fromString(String string) {
                        return LocalTime.parse(string);
                    }
                });
                setValue(LocalTime.of(8, 0));
            }

            @Override
            public void decrement(int steps) {
                setValue(getValue().minusMinutes(steps * 30));
            }

            @Override
            public void increment(int steps) {
                setValue(getValue().plusMinutes(steps * 30));
            }
        };

        SpinnerValueFactory<LocalTime> checkOutFactory = new SpinnerValueFactory<>() {
            {
                setConverter(new StringConverter<>() {
                    @Override
                    public String toString(LocalTime time) {
                        return time != null ? time.toString() : "";
                    }

                    @Override
                    public LocalTime fromString(String string) {
                        return LocalTime.parse(string);
                    }
                });
                setValue(LocalTime.of(10, 0));
            }

            @Override
            public void decrement(int steps) {
                setValue(getValue().minusMinutes(steps * 30));
            }

            @Override
            public void increment(int steps) {
                setValue(getValue().plusMinutes(steps * 30));
            }
        };

        jamCheckInSpinner.setValueFactory(checkInFactory);
        jamCheckOutSpinner.setValueFactory(checkOutFactory);

    }

    public void handleClearSearchText(ActionEvent actionEvent) {
        searchBox.clear();
        table.setItems(data);
    }

    public void handleAddAction(ActionEvent actionEvent) {
        String email = emailField.getText();
        Ruangan selectedRuangan = ruanganChoiceBox.getValue();
        String checkIn = String.valueOf(tanggalCheckInPicker.getValue());
        String jamIn = jamCheckInSpinner.getValue().toString();
        String jamOut = jamCheckOutSpinner.getValue().toString();
        String checkOut = String.valueOf(tanggalCheckOutPicker.getValue());

        if (email.isEmpty() || selectedRuangan == null || checkIn == null || jamIn.isEmpty() || checkOut == null || jamOut.isEmpty()) {
            showAlert("Input Tidak Lengkap", "Silakan isi semua data pemesanan.");
            return;
        }

        int ruangan = selectedRuangan.getId();

        boolean success = pemesananManager.addPemesanan(email, ruangan, checkIn, checkOut, jamIn, jamOut);
        if (success) {
            data.clear();
            data.addAll(pemesananManager.getAllPemesanan());
        }
    }

    public void handleBatalAction(ActionEvent actionEvent) {
        emailField.clear();
        ruanganChoiceBox.getSelectionModel().clearSelection();
        tanggalCheckInPicker.setValue(null);
        jamCheckInSpinner.getValueFactory().setValue(LocalTime.of(8, 0));
        tanggalCheckOutPicker.setValue(null);
        jamCheckOutSpinner.getValueFactory().setValue(LocalTime.of(10, 0));
    }

    public void handleEditAction(ActionEvent actionEvent) {
        Pemesanan selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Tidak ada data dipilih", "Pilih data pemesanan yang ingin diubah.");
            return;
        }

        String email = emailField.getText();
        Ruangan selectedRuangan = ruanganChoiceBox.getValue();
        String checkIn = String.valueOf(tanggalCheckInPicker.getValue());
        String jamIn = jamCheckInSpinner.getValue().toString();
        String checkOut = String.valueOf(tanggalCheckOutPicker.getValue());
        String jamOut = jamCheckOutSpinner.getValue().toString();

        if (email.isEmpty() || selectedRuangan == null || checkIn == null || jamIn.isEmpty() || checkOut == null || jamOut.isEmpty()) {
            showAlert("Input Tidak Lengkap", "Silakan isi semua data pemesanan.");
            return;
        }

        int ruangan = selectedRuangan.getId();

        boolean success = pemesananManager.editPemesanan(selected.getId(), email, ruangan, checkIn, checkOut, jamIn, jamOut);
        if (success) {
            data.clear();
            data.addAll(pemesananManager.getAllPemesanan());
        }
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}
