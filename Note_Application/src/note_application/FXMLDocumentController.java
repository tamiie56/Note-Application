/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package note_application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author TAMIM
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button Lg1;
    @FXML
    private Button Lg11;
    @FXML
    private TextField lusnm;
    @FXML
    private PasswordField lusid;
    @FXML
    private TextField sunmn;
    @FXML
    private TextField susid;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoginButtonEvent(ActionEvent event) {
        
    }

    @FXML
    private void SignupButtonEvent(ActionEvent event) {
    }

    @FXML
    private void susem(ActionEvent event) {
    }
    
}
