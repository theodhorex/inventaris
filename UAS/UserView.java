package org.ukdw.view;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ukdw.data.User;
import org.ukdw.managers.UserManager;
import org.ukdw.utils.DBConnectionManager;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UserView implements Initializable {

    @FXML private TextField usernameTxtFld;
    @FXML private PasswordField passwordTxtFld;
    @FXML private TextField emailTxtFld;
    @FXML private Button btnTambah;
    @FXML private Button btnHapus;
    @FXML private Button btnUbah;
    @FXML private TextField searchBox;
    @FXML private TableView<User> table;
    @FXML private TableColumn<User, String> tblColUsername;
    @FXML private TableColumn<User, String> tblColEmail;

    private ObservableList<User> users = FXCollections.observableArrayList();
    private FilteredList<User> filteredUsers;

    private final UserManager manager = new UserManager(DBConnectionManager.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupSearch();
        loadUsers();

        btnUbah.disableProperty().bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));
        btnHapus.disableProperty().bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                usernameTxtFld.setText(newSel.getUsername());
                passwordTxtFld.setText(newSel.getPassword());
                emailTxtFld.setText(newSel.getEmail());
            }
        });
    }

    private void setupTable() {
        tblColUsername.setCellValueFactory(cellData -> Bindings.createStringBinding(() ->
                cellData.getValue().getUsername()));
        tblColEmail.setCellValueFactory(cellData -> Bindings.createStringBinding(() ->
                cellData.getValue().getEmail()));
        filteredUsers = new FilteredList<>(users, p -> true);
        table.setItems(filteredUsers);
    }

    private void setupSearch() {
        searchBox.textProperty().addListener((obs, oldValue, newValue) -> {
            String keyword = newValue.toLowerCase();
            filteredUsers.setPredicate(user ->
                    user.getUsername().toLowerCase().contains(keyword) ||
                            user.getEmail().toLowerCase().contains(keyword)
            );
        });
    }

    private void loadUsers() {
        users.clear();
        try (PreparedStatement stmt = DBConnectionManager.getConnection()
                .prepareStatement("SELECT email, username, password FROM users");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            showError("Gagal memuat data user.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        String username = usernameTxtFld.getText().trim();
        String password = passwordTxtFld.getText().trim();
        String email = emailTxtFld.getText().trim();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            showError("Semua field wajib diisi.");
            return;
        }

        if (manager.registerUser(email, username, password)) {
            loadUsers();
            clearForm();
        } else {
            showError("User gagal ditambahkan. Mungkin email/username sudah digunakan.");
        }
    }

    @FXML
    private void handleEditAction(ActionEvent event) {
        User selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Silakan pilih user terlebih dahulu.");
            return;
        }

        String username = usernameTxtFld.getText().trim();
        String password = passwordTxtFld.getText().trim();

        if (manager.updateProfile(selected.getEmail(), username, password)) {
            loadUsers();
            clearForm();
        } else {
            showError("Gagal mengupdate data user.");
        }
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        User selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Silakan pilih user yang akan dihapus.");
            return;
        }

        try (PreparedStatement stmt = DBConnectionManager.getConnection()
                .prepareStatement("DELETE FROM users WHERE email = ?")) {
            stmt.setString(1, selected.getEmail());
            int deleted = stmt.executeUpdate();
            if (deleted > 0) {
                loadUsers();
                clearForm();
            } else {
                showError("Tidak ada user yang dihapus.");
            }
        } catch (SQLException e) {
            showError("Terjadi kesalahan saat menghapus user.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClearSearchText(ActionEvent event) {
        searchBox.clear();
    }

    private void clearForm() {
        usernameTxtFld.clear();
        passwordTxtFld.clear();
        emailTxtFld.clear();
        table.getSelectionModel().clearSelection();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK).showAndWait();
    }
}