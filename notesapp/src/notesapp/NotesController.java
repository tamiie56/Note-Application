package notesapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NotesController {
    @FXML private ListView<String> notesList;
    @FXML private TextField titleField;
    @FXML private TextArea contentField;

    private ObservableList<String> notes = FXCollections.observableArrayList();
    private int currentUserId = 1; // Replace with logged-in user ID

    @FXML
    private void initialize() {
        loadNotes();
        notesList.setOnMouseClicked(event -> loadSelectedNote());
    }

    private void loadNotes() {
        notes.clear();
        String query = "SELECT * FROM notes WHERE user_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentUserId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                notes.add(rs.getString("title"));
            }
            notesList.setItems(notes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSelectedNote() {
        String selectedTitle = notesList.getSelectionModel().getSelectedItem();
        if (selectedTitle == null) return;

        String query = "SELECT * FROM notes WHERE user_id = ? AND title = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentUserId);
            stmt.setString(2, selectedTitle);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                titleField.setText(rs.getString("title"));
                contentField.setText(rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddNote() throws SQLException {
        String title = titleField.getText();
        String content = contentField.getText();

        if (title.isEmpty() || content.isEmpty()) {
            showAlert("Error", "Title and content cannot be empty.");
            return;
        }

        String query = "INSERT INTO notes (user_id, title, content) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentUserId);
            stmt.setString(2, title);
            stmt.setString(3, content);
            stmt.executeUpdate();
            loadNotes();
            clearFields();
        }
    }

    @FXML
    private void handleEditNote(ActionEvent event) {
        String selectedTitle = notesList.getSelectionModel().getSelectedItem();
        if (selectedTitle == null) {
            showAlert("Error", "No note selected for editing.");
            return;
        }

        String newTitle = titleField.getText();
        String newContent = contentField.getText();

        if (newTitle.isEmpty() || newContent.isEmpty()) {
            showAlert("Error", "Title and content cannot be empty.");
            return;
        }

        String query = "UPDATE notes SET title = ?, content = ? WHERE user_id = ? AND title = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newTitle);
            stmt.setString(2, newContent);
            stmt.setInt(3, currentUserId);
            stmt.setString(4, selectedTitle);
            stmt.executeUpdate();
            loadNotes();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteNote(ActionEvent event) {
        String selectedTitle = notesList.getSelectionModel().getSelectedItem();
        if (selectedTitle == null) {
            showAlert("Error", "No note selected for deletion.");
            return;
        }

        String query = "DELETE FROM notes WHERE user_id = ? AND title = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentUserId);
            stmt.setString(2, selectedTitle);
            stmt.executeUpdate();
            loadNotes();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) notesList.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void clearFields() {
        titleField.clear();
        contentField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
