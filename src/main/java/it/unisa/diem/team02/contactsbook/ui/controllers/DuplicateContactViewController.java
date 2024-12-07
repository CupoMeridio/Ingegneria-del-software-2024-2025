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
 * 
 * Controller per la gestione dei contatti duplicati.
 * 
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
     * 
     * Metodo di inizializzazione del controller.
     * Chiama i metodi che inizializzano i vari componenti.
     * 
     * @param url utilizzato per risolvere il percorso del file FXML.
     * @param rb contenente dati di localizzazione.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    /**
     * 
     * Implementa l'azione associcata al tasto Add: un campo del contatto viene usato come flag per indicare
     * che l'utente desidera aggiungere il contatto.
     * 
     * @param event
     * 
     */
    @FXML
    private void actionYes(ActionEvent event){
        contact.setName("");
        Stage stage = (Stage) btnYes.getScene().getWindow();
        stage.close();
    }
    
    /**
     * 
     * Implementa l'azione associcata al tasto Modify: il contatto/flag non viene modificato e si torna alla
     * schermata di modifica/aggiunta.
     * 
     * @param event
     * 
     */
    @FXML
    private void actionNo(ActionEvent event){
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.close();
    }
    
    /**
     * 
     * Setta il campo contatto con il contatto passato come parametro.
     * 
     * @param c1 è il contatto da usare come flag.
     * 
     */
    public void set(Contact c1){
        this.contact=c1;
    }
    
}
