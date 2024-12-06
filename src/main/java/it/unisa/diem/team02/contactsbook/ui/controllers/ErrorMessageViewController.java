/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * @lang it
 * Controller per la gestione dei messaggi di errori
 * 
 * 
 * @lang en
 * Controller for managing error messages.
 * 
 * @author team02
 */
public class ErrorMessageViewController implements Initializable {

    @FXML
    private AnchorPane messaggioErrore;
    @FXML
    private Label txtError;
    @FXML
    private Button btnOk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
