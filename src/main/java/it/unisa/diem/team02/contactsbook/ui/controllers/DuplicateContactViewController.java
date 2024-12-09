
package it.unisa.diem.team02.contactsbook.ui.controllers;

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
 * Controller per la gestione dei contatti duplicati.
 * @author team02
 */
public class DuplicateContactViewController implements Initializable {

    @FXML
    private Label lblMessage;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    
    private Boolean b;

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
 * @brief Gestisce l'azione di conferma per il reset del nome del contatto.
 * 
 * Questo metodo viene chiamato quando l'utente clicca sul pulsante "Yes". Il metodo resetta il nome del contatto
 * a una stringa vuota e chiude la finestra corrente.
 * 
 * @param event L'evento generato dal clic sul pulsante "Yes".
 * 
 * @see Stage#close()
 * @see Button
 */
    @FXML
    private void actionYes(ActionEvent event){
        b=true;
        Stage stage = (Stage) btnYes.getScene().getWindow();
        stage.close();
    }
    
/**
 * @brief Gestisce l'azione di annullamento, chiudendo la finestra senza apportare modifiche.
 * 
 * Questo metodo viene chiamato quando l'utente clicca sul pulsante "No". Il metodo chiude semplicemente la finestra
 * corrente senza eseguire alcuna modifica ai dati.
 * 
 * @param event L'evento generato dal clic sul pulsante "No".
 * 
 * @see Stage#close()
 * @see Button
 */
    @FXML
    private void actionNo(ActionEvent event){
        b=false;
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.close();
    }
    
    public Boolean getBoolean(){
        return b;
    }
    
}
