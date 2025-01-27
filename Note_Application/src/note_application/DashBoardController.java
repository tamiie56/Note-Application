/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package note_application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author TAMIM
 */
public class DashBoardController implements Initializable {

    @FXML
    private TextField note;
    @FXML
    private Button addbtn;
    @FXML
    private Button editbtn;
    @FXML
    private Button dltbtn;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<?, ?> mynotes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addbuttonevent(ActionEvent event) {
    }

    @FXML
    private void editbuttonevent(ActionEvent event) {
    }

    @FXML
    private void deletebuttonevent(ActionEvent event) {
    }
    
}
