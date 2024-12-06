package it.unisa.diem.team02.contactsbook.ui.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import it.unisa.diem.team02.contactsbook.model.Contact;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @lang it
 * Controller per la gestione dei contatti duplicati.
 * 
 * @lang en
 * Controller for handling duplicate contacts.
 * 
 * @author team02
 */
public class DuplicateContactViewController implements Initializable {

    @FXML
    private Label lblMessage;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    
    private Contact contact;

    /**
     * @lang it
     * Metodo di inizializzazione del controller.
     * Chiama i metodi che inizializzano i vari componenti.
     * 
     * @param url URL utilizzato per risolvere il percorso del file FXML.
     * @param rb Risorsa contenente dati di localizzazione.
     * 
     * @lang en
     * Initializes the controller.
     * Calls the methods that initialize the various components.
     * 
     * @param url URL used to resolve the FXML file path.
     * @param rb Resource containing localization data.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void actionYes(ActionEvent event){
        contact.setName("");
        Stage stage = (Stage) btnYes.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void actionNo(ActionEvent event){
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.close();
    }
    
    public void set(Contact c1){
        this.contact=c1;
    }
    
}
