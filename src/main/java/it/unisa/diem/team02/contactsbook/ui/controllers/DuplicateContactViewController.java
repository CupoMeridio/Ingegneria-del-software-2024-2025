
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
 * @brief Controller per la gestione dei contatti duplicati.
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
     * @note Metodo non utilizzato
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
/**
 * @brief Gestisce l'azione quando l'utente clicca "Sì".
 * 
 * Questo metodo viene invocato quando l'utente clicca sul pulsante "Sì" in una finestra di dialogo.
 * Imposta la variabile booleana `b` su `true` e chiude la finestra corrente.
 * 
 * @pre Il metodo viene invocato solo quando l'utente ha interagito con la finestra di dialogo.
 * @post La variabile `b` sarà impostata su `true` e la finestra corrente sarà chiusa.
 * @invariant La variabile `b` sarà sempre aggiornata correttamente e la finestra verrà chiusa.
 * 
 * @param event L'evento che attiva l'azione di clic sul pulsante "Sì".
 */
    @FXML
    private void actionYes(ActionEvent event){
        b=true;
        Stage stage = (Stage) btnYes.getScene().getWindow();
        stage.close();
    }
    
/**
 * @brief Gestisce l'azione quando l'utente clicca "No".
 * 
 * Questo metodo viene invocato quando l'utente clicca sul pulsante "No" in una finestra di dialogo.
 * Imposta la variabile booleana `b` su `false` e chiude la finestra corrente.
 * 
 * @pre Il metodo viene invocato solo quando l'utente ha interagito con la finestra di dialogo.
 * @post La variabile `b` sarà impostata su `false` e la finestra corrente sarà chiusa.
 * @invariant La variabile `b` sarà sempre aggiornata correttamente e la finestra verrà chiusa.
 * 
 * @param event L'evento che attiva l'azione di clic sul pulsante "No".
 */
    @FXML
    private void actionNo(ActionEvent event){
        b=false;
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.close();
    }
    
    
/**
 * @brief Restituisce il valore della variabile booleana `b`.
 * 
 * Questo metodo restituisce il valore corrente della variabile booleana `b`, che rappresenta
 * una scelta dell'utente in una finestra di dialogo. Viene utilizzato per recuperare la decisione
 * dell'utente riguardo a una determinata azione.
 * 
 * @pre La variabile `b` deve essere stata precedentemente impostata tramite uno dei metodi
 *      che gestiscono l'interazione dell'utente (ad esempio, `actionYes` o `actionNo`).
 * @post Il valore di `b` rimane invariato, restituendo semplicemente il valore corrente.
 * @invariant Il valore di `b` rimarrà coerente con la scelta dell'utente fino a un'eventuale modifica.
 * 
 * @return Il valore della variabile booleana `b`.
 */
    public Boolean getBoolean(){
        return b;
    }
    
}
