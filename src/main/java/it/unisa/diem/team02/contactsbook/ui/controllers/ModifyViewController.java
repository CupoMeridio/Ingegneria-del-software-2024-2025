
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
 * @brief Inizializza le proprietà dei controlli dell'interfaccia utente.
 * 
 * Questo metodo viene invocato automaticamente quando la vista è stata completamente caricata e inizializzata. 
 * Si occupa di legare le proprietà dei controlli dell'interfaccia utente (come bottoni e campi di testo) con le 
 * condizioni di stato, disabilitando o abilitando determinati elementi in base al contenuto degli altri campi.
 * 
 * @param url URL utilizzato per il caricamento della risorsa FXML (non utilizzato in questo caso).
 * @param rb Risorse locali associate alla vista (non utilizzato in questo caso).
 * 
 * @pre
 * - La vista FXML è stata correttamente caricata e tutti i controlli sono disponibili.
 * 
 * @post
 * - I controlli dell'interfaccia sono legati correttamente con le proprietà dei campi di testo, 
 *   e i bottoni saranno abilitati o disabilitati in base al contenuto dei campi.
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModify.disableProperty().bind(Bindings.and(txtName.textProperty().isEmpty(), txtSur.textProperty().isEmpty()));
        txtNumber2.disableProperty().bind(Bindings.isEmpty(txtNumber1.textProperty()));
        txtNumber3.disableProperty().bind(Bindings.isEmpty(txtNumber2.textProperty()));
        txtEmail2.disableProperty().bind(Bindings.isEmpty(txtEmail1.textProperty()));
        txtEmail3.disableProperty().bind(Bindings.isEmpty(txtEmail2.textProperty()));
    }    


    @FXML
    private void actionCancel(ActionEvent event) {
        Stage stage=(Stage) btnCanc.getScene().getWindow();
        stage.close();
    }

/**
 * @brief Imposta la lista osservabile di contatti.
 * 
 * Questo metodo assegna una lista osservabile di contatti alla variabile di istanza `contacts`, che viene poi utilizzata 
 * per gestire dinamicamente la visualizzazione e la modifica dei contatti nell'interfaccia utente.
 * 
 * @param contacts La lista osservabile di contatti da impostare.
 * 
 * @pre
 * - Il parametro `contacts` deve essere non nullo e rappresentare una lista valida di oggetti `Contact`.
 * 
 * @post
 * - La variabile di istanza `contacts` viene aggiornata con il valore del parametro passato.
 * 
 * @invariant
 * - La variabile `contacts` deve sempre contenere una lista di oggetti `Contact` valida e aggiornata.
 * 
 * @see Contact
 */
    public void setObservableList(ObservableList<Contact> contacts){
        this.contacts=contacts;
    }
    
/**
 * @brief Imposta i valori di un contatto nelle apposite caselle di testo.
 * 
 * Questo metodo imposta i campi di un contatto (nome, cognome, numeri di telefono, email) nei rispettivi campi di input 
 * nell'interfaccia utente. I numeri di telefono e le email vengono separati e assegnati a più campi di testo, se presenti.
 * 
 * @param contact Il contatto da impostare, che fornisce le informazioni da visualizzare nei campi di input.
 * 
 * @pre
 * - Il parametro `contact` deve essere non nullo e contenere i dati da visualizzare.
 * - La variabile `oldContact` è utilizzata per salvare il contatto passato.
 * 
 * @post
 * - I valori del contatto passato vengono impostati nei rispettivi campi di testo (nome, cognome, numeri di telefono, email).
 * - Se un numero di telefono o una email è presente, verrà separato nei vari campi di input.
 * 
 * @invariant
 * - I campi di testo (txtName, txtSur, txtNumber1, txtNumber2, txtNumber3, txtEmail1, txtEmail2, txtEmail3) 
 *   devono essere correttamente aggiornati con i dati del contatto.
 * 
 * @see Contact#getName()
 * @see Contact#getSurname()
 * @see Contact#getNumber()
 * @see Contact#getEmail()
 */
public void setContact(Contact contact) {
    // Salva il contatto passato come parametro nella variabile di istanza
    oldContact = contact;
    
    // Imposta il nome nel campo di testo "txtName" se presente
    if (oldContact.getName() != "") txtName.setText(oldContact.getName());
    
    // Imposta il cognome nel campo di testo "txtSur" se presente
    if (oldContact.getSurname() != "") txtSur.setText(oldContact.getSurname());
    
    // Imposta i numeri di telefono separandoli se presenti
    String number = oldContact.getNumber();
    if (number != ""){
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
    if (email != ""){
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
 * Questo metodo crea un nuovo oggetto `Contact` con le informazioni aggiornate fornite dall'utente.
 * Se il contatto modificato non esiste già nella lista, il contatto precedente viene sostituito con quello nuovo.
 * Se il contatto modificato è un duplicato, viene mostrata una finestra di conferma per decidere se salvare la modifica o meno.
 * 
 * @param event L'evento che ha attivato l'azione di modifica.
 * 
 * @pre
 * - Il contatto da modificare (`oldContact`) deve esistere e essere presente nella lista `contacts`.
 * - I campi di input per il nome, cognome, numeri di telefono e email devono essere configurati correttamente.
 * 
 * @post
 * - Se il contatto non è un duplicato, il contatto precedente viene sostituito con quello modificato.
 * - Se il contatto è un duplicato, viene mostrata una finestra di dialogo per confermare o annullare la modifica.
 * 
 * @invariant
 * - La lista `contacts` rimane consistente, senza duplicati, dopo la modifica del contatto.
 * - I numeri di telefono e le email devono essere aggiunti solo se non vuoti.
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
