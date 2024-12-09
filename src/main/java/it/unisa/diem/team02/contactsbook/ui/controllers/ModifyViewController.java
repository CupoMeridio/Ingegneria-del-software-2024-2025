
package it.unisa.diem.team02.contactsbook.ui.controllers;

import it.unisa.diem.team02.contactsbook.model.Contact;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * Controller per la gestione della schermata di modifica di un contatto già esistente.
 * 
 * @author team02
 */
public class ModifyViewController implements Initializable {

    @FXML
    private AnchorPane add;
    @FXML
    private HBox hboxInsertInfo;
    @FXML
    private HBox hboxName;
    @FXML
    private Label lblName;
    @FXML
    private VBox vboxName;
    @FXML
    private TextField txtName;
    @FXML
    private HBox hboxSur;
    @FXML
    private Label lblSur;
    @FXML
    private VBox vboxSur;
    @FXML
    private TextField txtSur;
    @FXML
    private HBox hboxEmail;
    @FXML
    private Label lblEmail;
    @FXML
    private VBox vboxEmail;
    @FXML
    private TextField txtEmail1;
    @FXML
    private TextField txtEmail2;
    @FXML
    private TextField txtEmail3;
    @FXML
    private HBox hboxNumber;
    @FXML
    private Label lblNumber;
    @FXML
    private VBox vboxNumber;
    @FXML
    private TextField txtNumber1;
    @FXML
    private TextField txtNumber2;
    @FXML
    private TextField txtNumber3;
    @FXML
    private HBox hboxButton;
    @FXML
    private Button btnCanc;
    @FXML
    private Button btnModify;
    
    private ObservableList<Contact> contacts;
    private Contact oldContact;

/**
 * Inizializza il controller, definendo i comportamenti dei bottoni e dei campi di input.
 * Questo metodo viene chiamato automaticamente durante la fase di inizializzazione della scena.
 * 
 * Le seguenti proprietà sono legate tramite binding:
 * 
 * - Il bottone "btnModify" è disabilitato se i campi "txtName" e "txtSur" sono vuoti.
 * - Il campo "txtNumber2" è disabilitato se il campo "txtNumber1" è vuoto.
 * - Il campo "txtNumber3" è disabilitato se il campo "txtNumber2" è vuoto.
 * - Il campo "txtEmail2" è disabilitato se il campo "txtEmail1" è vuoto.
 * - Il campo "txtEmail3" è disabilitato se il campo "txtEmail2" è vuoto.
 * 
 * @param url L'URL utilizzato per caricare il file FXML (se presente).
 * @param rb Il ResourceBundle utilizzato per internazionalizzare il contenuto.
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModify.disableProperty().bind(Bindings.and(txtName.textProperty().isEmpty(), txtSur.textProperty().isEmpty()));
        txtNumber2.disableProperty().bind(Bindings.isEmpty(txtNumber1.textProperty()));
        txtNumber3.disableProperty().bind(Bindings.isEmpty(txtNumber2.textProperty()));
        txtEmail2.disableProperty().bind(Bindings.isEmpty(txtEmail1.textProperty()));
        txtEmail3.disableProperty().bind(Bindings.isEmpty(txtEmail2.textProperty()));
    }    

/**
 * Gestisce l'azione di annullamento, chiudendo la finestra corrente.
 * Questo metodo viene chiamato quando l'utente clicca sul bottone "btnCanc" (annulla).
 * 
 * Il metodo ottiene il riferimento alla finestra corrente (stage) e la chiude.
 * 
 * @param event L'evento generato dal click sul bottone "btnCanc".
 */
    @FXML
    private void actionCancel(ActionEvent event) {
        Stage stage=(Stage) btnCanc.getScene().getWindow();
        stage.close();
    }

/**
 * Imposta la lista osservabile di contatti.
 * Questo metodo viene utilizzato per aggiornare l'elenco dei contatti nella vista.
 * 
 * @param contacts La lista osservabile di contatti da impostare.
 */
    public void setObservableList(ObservableList<Contact> contacts){
        this.contacts=contacts;
    }
    
/**
 * @brief Imposta i dettagli di un contatto esistente nei campi di input.
 * 
 * Questo metodo aggiorna i vari campi di testo (nome, cognome, numeri di telefono, email) 
 * con i valori del contatto passato come parametro. Gestisce il caso in cui il contatto 
 * abbia più di un numero di telefono o più di un'email, separandoli tramite il carattere di newline ("\n").
 * 
 * @param contact Il contatto da impostare nei campi di input.
 * 
 */
public void setContact(Contact contact) {
    // Salva il contatto passato come parametro nella variabile di istanza
    oldContact = contact;
    
    // Imposta il nome nel campo di testo "txtName" se presente
    if (oldContact.getName() != null) txtName.setText(oldContact.getName());
    
    // Imposta il cognome nel campo di testo "txtSur" se presente
    if (oldContact.getSurname() != null) txtSur.setText(oldContact.getSurname());
    
    // Imposta i numeri di telefono separandoli se presenti
    String number = oldContact.getNumber();
    if (number != null){
        String[] numbers = number.split("\n");
        txtNumber1.setText(numbers[0]);
        
        // Imposta il secondo numero di telefono se presente
        if (numbers.length > 1){
            txtNumber2.setText(numbers[1]);
            
            // Imposta il terzo numero di telefono se presente
            if (numbers.length > 2){
                txtNumber3.setText(numbers[2]);
            }
        }
    }
    
    // Imposta le email separandole se presenti
    String email = oldContact.getEmail();
    if (email != null){
        String[] emails = email.split("\n");
        txtEmail1.setText(emails[0]);
        
        // Imposta la seconda email se presente
        if (emails.length > 1){
            txtEmail2.setText(emails[1]);
            
            // Imposta la terza email se presente
            if (emails.length > 2){
                txtEmail3.setText(emails[2]);
            }
        }
    }
}

/**
 * @brief Gestisce l'azione di modifica di un contatto esistente.
 * 
 * Questo metodo consente di modificare un contatto esistente con nuovi dettagli (nome, cognome, numeri di telefono e email).
 * Verifica se il contatto modificato è già presente nella lista dei contatti. In caso affermativo, viene mostrata una finestra 
 * di dialogo per gestire il duplicato. Se il contatto non è un duplicato, la modifica viene salvata nella lista.
 * 
 * @param event L'evento associato al clic del pulsante di modifica.
 * 
 * @throws IOException Se si verifica un errore nel caricare la finestra di dialogo.
 * 
 * @note I numeri di telefono e le email vengono aggiunti solo se i rispettivi campi di input non sono vuoti.

 */
    @FXML
private void actionModify(ActionEvent event) throws IOException {
    Contact newContact = new Contact(txtName.getText(), txtSur.getText(), oldContact.getID());
    ArrayList<String> numbers = new ArrayList<>(3);
    ArrayList<String> emails = new ArrayList<>(3);
    
    // Aggiungi numeri di telefono se non vuoti
    if (!txtNumber1.getText().isEmpty())
        numbers.add(txtNumber1.getText());
    if (!txtNumber2.getText().isEmpty())
        numbers.add(txtNumber2.getText());
    if (!txtNumber3.getText().isEmpty())
        numbers.add(txtNumber3.getText());

    // Aggiungi email se non vuote
    if (!txtEmail1.getText().isEmpty())
        emails.add(txtEmail1.getText());
    if (!txtEmail2.getText().isEmpty())
        emails.add(txtEmail2.getText());
    if (!txtEmail3.getText().isEmpty())
        emails.add(txtEmail3.getText());

    // Imposta numeri di telefono ed email nel nuovo contatto
    newContact.setNumber(numbers);
    newContact.setEmail(emails);

    // Verifica se il contatto esiste già nella lista
    if (!contacts.contains(newContact)) {
        // Se il contatto non è un duplicato, sostituisci il vecchio contatto con il nuovo
        contacts.remove(oldContact);
        contacts.add(newContact);
        Stage stage = (Stage) btnModify.getScene().getWindow();
        stage.close();
    } else { 
        // Se il contatto è un duplicato, mostra la finestra di dialogo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DuplicateContactView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        DuplicateContactViewController duplicateC = loader.getController();

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initOwner(btnModify.getScene().getWindow());
        newStage.showAndWait();

        // Se l'utente conferma la modifica, salva il nuovo contatto
        if (duplicateC.getBoolean()) {
            contacts.remove(oldContact);
            contacts.add(newContact);
            Stage stage = (Stage) btnModify.getScene().getWindow();
            stage.close();
        } else {
            // Riapri la finestra di modifica se l'utente non conferma
            Stage stage = (Stage) btnModify.getScene().getWindow();
            stage.show();
        }  
    }
}
    
}
