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
    
    /**
     * @lang it
     * Implementa l'azione associcata al tasto Add: un campo del contatto viene usato come flag per indicare
     * che l'utente desidera aggiungere il contatto.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the Add button: a contact field is used as a flag to indicate
     * that the user wants to add the contact.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionYes(ActionEvent event){
        contact.setName("");
        Stage stage = (Stage) btnYes.getScene().getWindow();
        stage.close();
    }
    
    /**
     * @lang it
     * Implementa l'azione associcata al tasto Modify: il contatto/flag non viene modificato e si torna alla
     * schermata di modifica/aggiunta.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the Modify button: the contact/field is not modified and the
     * systems returns to the modify/add screen.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionNo(ActionEvent event){
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.close();
    }
    
    /**
     * @lang it
     * Setta il campo contatto con il contatto passato come parametro.
     * 
     * @param Contact c è il contatto da usare come flag.
     * 
     * @lang en
     * Sets the contact field with the contact passed as a parameter.
     * 
     * @param Contact c is the contacted used as flag.
     */
    public void set(Contact c1){
        this.contact=c1;
    }
    
}
