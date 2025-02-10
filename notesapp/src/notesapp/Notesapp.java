package notesapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Notesapp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load the login.fxml file
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Notes App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}