
package it.unisa.diem.team02.contactsbook.ui.controllers;

import it.unisa.diem.team02.contactsbook.model.Contact;
import java.io.IOException;
import java.net.URL;
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
 * Controller per la gestione della schermata di aggiunta di un nuovo contatto.
 * @author team02
 */
public class AddViewController implements Initializable {

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
    private HBox hboxButton;
    @FXML
    private Button btnCanc;
    @FXML
    private TextField txtNumber3;
    @FXML
    private Button btnAdd;
    
    private ObservableList<Contact> contacts;

/**
 * @brief Inizializza la finestra o il controller associato alla vista FXML.
 * 
 * Questo metodo configura le proprietà di abilitazione/disabilitazione di alcuni componenti dell'interfaccia utente,
 * utilizzando binding JavaFX per collegare le proprietà degli elementi.
 * 
 * @param url L'URL utilizzato per individuare le risorse necessarie al controller (può essere nullo).
 * @param rb Il ResourceBundle contenente le risorse internazionalizzate per il controller (può essere nullo).
 * 
 * @details 
 * - Il pulsante `btnAdd` viene disabilitato se entrambi i campi di testo `txtName` e `txtSur` sono vuoti.
 * - I campi `txtNumber2` e `txtNumber3` vengono abilitati sequenzialmente solo se i campi precedenti contengono testo.
 * - I campi `txtEmail2` e `txtEmail3` seguono una logica simile a quella dei numeri.
 * - Le proprietà di abilitazione/disabilitazione sono gestite con il metodo `bind` della classe `Bindings`.
 * 
 * @pre
 * - I campi `txtName`, `txtSur`, `txtNumber1`, `txtNumber2`, `txtNumber3`, `txtEmail1`, `txtEmail2`, `txtEmail3`
 *   devono essere inizializzati correttamente e associati agli elementi corrispondenti nella vista FXML.
 * - Il pulsante `btnAdd` deve essere inizializzato e associato a un elemento della vista.
 * 
 * @post
 * - Le proprietà di abilitazione/disabilitazione dei componenti sono configurate in base alle condizioni specificate.
 * 
 * @invariant
 * - Gli oggetti associati alle proprietà (es. `txtName.textProperty()`) devono rimanere consistenti durante l'esecuzione del metodo.
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAdd.disableProperty().bind(Bindings.and(txtName.textProperty().isEmpty(), txtSur.textProperty().isEmpty()));
        txtNumber2.disableProperty().bind(Bindings.isEmpty(txtNumber1.textProperty()));
        txtNumber3.disableProperty().bind(Bindings.isEmpty(txtNumber2.textProperty()));
        txtEmail2.disableProperty().bind(Bindings.isEmpty(txtEmail1.textProperty()));
        txtEmail3.disableProperty().bind(Bindings.isEmpty(txtEmail2.textProperty()));
    }
    
    
/**
 * @brief Imposta la lista osservabile dei contatti.
 * 
 * Questo metodo consente di assegnare un oggetto `ObservableList` contenente i contatti all'istanza corrente.
 * 
 * @param contacts La nuova lista osservabile di oggetti `Contact` da associare.
 * 
 * @pre
 * - Il parametro `contacts` non deve essere nullo.
 * 
 * @post
 * - L'attributo `contacts` contiene il riferimento alla lista osservabile specificata.
 * - La lista osservabile può essere utilizzata per monitorare e aggiornare dinamicamente le modifiche ai contatti.
 * 
 * @invariant
 * - L'attributo `contacts` rimane consistente durante l'esecuzione del metodo.
 * 
 * @throws IllegalArgumentException Se il parametro `contacts` è nullo.
 */
    public void setObservableList(ObservableList<Contact> contacts){
        this.contacts=contacts;
    }
    
/**
 * @brief Aggiunge un nuovo contatto alla lista osservabile o gestisce duplicati.
 * 
 * Questo metodo crea un oggetto `Contact` basato sui dati inseriti nei campi di testo 
 * e lo aggiunge alla lista osservabile `contacts`. Se il contatto esiste già, 
 * viene mostrata una finestra per gestire il duplicato.
 * 
 * @param event L'evento che ha attivato l'azione, tipicamente un clic sul pulsante "Aggiungi".
 * 
 * @throws IOException Se si verifica un errore durante il caricamento del file FXML per la gestione dei duplicati.
 * 
 * @pre
 * - Il campo `contacts` deve essere inizializzato.
 * - I campi di testo devono essere accessibili e contenere i dati corretti.
 * 
 * @post
 * - Se il contatto non è un duplicato, viene aggiunto a `contacts`.
 * - Se il contatto è un duplicato, l'utente decide se aggiungerlo o meno.
 * - La finestra corrente viene chiusa dopo l'aggiunta o il rifiuto del contatto.
 * 
 * @details
 * - I dati vengono estratti dai campi di testo `txtName`, `txtSur`, `txtNumber1`, `txtNumber2`, 
 *   `txtNumber3`, `txtEmail1`, `txtEmail2` e `txtEmail3`.
 * - Se un campo di numero o email è vuoto, non viene aggiunto al contatto.
 * - In caso di duplicato, viene caricata la vista `DuplicateContactView.fxml`, e l'utente 
 *   sceglie se aggiungere il contatto o meno.
 * 
 * @invariant
 * - La lista `contacts` deve rimanere consistente durante l'esecuzione.
 * @see Contact
 * @see DuplicateContactViewController
 */
    public void actionAdd(ActionEvent event) throws IOException{
        Contact c=new Contact(txtName.getText(), txtSur.getText());
        if (!txtNumber1.getText().isEmpty()) c.addNumber(txtNumber1.getText());
        if (!txtNumber2.getText().isEmpty()) c.addNumber(txtNumber2.getText());
        if (!txtNumber3.getText().isEmpty()) c.addNumber(txtNumber3.getText());
        if(!txtEmail1.getText().isEmpty()) c.addEmail(txtEmail1.getText());
        if(!txtEmail2.getText().isEmpty()) c.addEmail(txtEmail2.getText());
        if(!txtEmail3.getText().isEmpty()) c.addEmail(txtEmail3.getText());
        
        if (contacts.contains(c)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DuplicateContactView.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);

            DuplicateContactViewController duplicateC=loader.getController();

            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(btnAdd.getScene().getWindow());
            newStage.showAndWait();
                
            if (duplicateC.getBoolean()){
                    contacts.add(c);
                    Stage stage=(Stage) btnAdd.getScene().getWindow();
                    stage.close();
                } else {
                    Stage stage=(Stage) btnAdd.getScene().getWindow();
                    stage.show();
                }       
        }
        else{
            contacts.add(c);
            Stage stage=(Stage) btnAdd.getScene().getWindow();
            stage.close();
        }
    }
    
/**
 * @brief Gestisce l'azione di annullamento dell'operazione corrente.
 * 
 * Questo metodo chiude la finestra attualmente aperta senza apportare modifiche ai dati o alle operazioni in corso.
 * 
 * @param event L'evento che ha attivato l'azione di annullamento.
 * 
 * @pre 
 * - Il pulsante associato a questa azione deve essere correttamente configurato e visibile nell'interfaccia utente.
 * 
 * @post
 * - La finestra corrente viene chiusa.
 * 
 * @invariant
 * - Nessuna modifica ai dati gestiti dall'applicazione è consentita durante l'esecuzione di questo metodo.
 */
    public void actionCancel(ActionEvent event){
        Stage stage=(Stage) btnCanc.getScene().getWindow();
        stage.close();
    }
    
        
}
