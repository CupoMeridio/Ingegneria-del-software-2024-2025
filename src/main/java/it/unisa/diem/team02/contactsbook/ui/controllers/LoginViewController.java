/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author anuar
 */
public class LoginViewController implements Initializable {

    @FXML
    private AnchorPane login;
    @FXML
    private VBox vBoxLogin;
    @FXML
    private Label lblLogMail;
    @FXML
    private TextField txtLogMail;
    @FXML
    private Label lblLogPass;
    @FXML
    private PasswordField txtLogPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblSignMail;
    @FXML
    private TextField txtSignMail;
    @FXML
    private Label lblSignPass;
    @FXML
    private PasswordField txtSignPass;
    @FXML
    private Label lblSignConfirm;
    @FXML
    private PasswordField txtConfirmPass;
    @FXML
    private Button btnSign;
    @FXML
    private HBox hboxLogin;
    @FXML
    private VBox vboxSignin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionLogin(ActionEvent event) {
    }

    @FXML
    private void actionSignin(ActionEvent event) {
    }
    
}
