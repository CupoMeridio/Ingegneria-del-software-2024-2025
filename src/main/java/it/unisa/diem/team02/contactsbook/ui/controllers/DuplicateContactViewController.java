package it.unisa.diem.team02.contactsbook.ui.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author anuar
 */
public class DuplicateContactViewController implements Initializable {

    @FXML
    private Label lblMessage;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionYes(ActionEvent event) {
    }

    @FXML
    private void actionNo(ActionEvent event) {
    }
    
}
