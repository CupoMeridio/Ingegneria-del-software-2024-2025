
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
 * @brief Inizializza il controller e gestisce i binding dei componenti dell'interfaccia utente.
 * 
 * Questo metodo viene chiamato automaticamente durante il processo di inizializzazione del controller
 * associato a un file FXML. Configura i binding per abilitare o disabilitare dinamicamente i bottoni e 
 * i campi di testo basandosi sullo stato degli altri campi di input.
 * 
 * @param url L'URL utilizzato per risolvere il percorso del file FXML. Può essere `null` se il percorso non è necessario.
 * @param rb Un oggetto `ResourceBundle` contenente dati di localizzazione. Può essere `null` se non sono necessari dati localizzati.
 * 
 * @details 
 * - Il bottone `btnAdd` viene disabilitato se sia `txtName` che `txtSur` sono vuoti.
 * - Il campo `txtNumber2` viene disabilitato se `txtNumber1` è vuoto.
 * - Il campo `txtNumber3` viene disabilitato se `txtNumber2` è vuoto.
 * - Il campo `txtEmail2` viene disabilitato se `txtEmail1` è vuoto.
 * - Il campo `txtEmail3` viene disabilitato se `txtEmail2` è vuoto.
 * 
 * @note Questo metodo utilizza la classe `Bindings` per configurare i binding logici e monitorare dinamicamente 
 *       i cambiamenti delle proprietà dei campi di testo.
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
 * Questo metodo consente di assegnare un oggetto `ObservableList` contenente i contatti 
 * all'istanza corrente. La lista osservabile permette di monitorare dinamicamente i cambiamenti 
 * dei dati nei contatti.
 * 
 * @param contacts Un'istanza di `ObservableList<Contact>` che rappresenta la lista dei contatti 
 *                 da associare.
 * 
 * @details 
 * - Il parametro `contacts` sostituisce l'attuale lista associata all'oggetto.
 * - La lista osservabile viene utilizzata per aggiornare automaticamente l'interfaccia utente 
 *   quando i dati nei contatti cambiano.
 */
    public void setObservableList(ObservableList<Contact> contacts){
        this.contacts=contacts;
    }
    
/**
 * @brief Gestisce l'aggiunta di un nuovo contatto.
 * 
 * Questo metodo viene chiamato quando l'utente attiva l'azione di aggiunta di un contatto. 
 * Crea un nuovo oggetto `Contact` utilizzando i dati inseriti nei campi di input, lo aggiunge 
 * alla lista dei contatti se non è già presente o gestisce i duplicati mostrando una finestra 
 * modale per la risoluzione dei conflitti.
 * 
 * @param event L'evento che ha scatenato l'azione, ovvero un clic sul bottone di aggiunta.
 * 
 * @throws IOException Se si verifica un errore durante il caricamento della vista `DuplicateContactView.fxml`.
 * 
 * @details 
 * - I campi di testo relativi a nome, cognome, numeri di telefono ed email vengono utilizzati 
 *   per creare un nuovo contatto.
 * - Se uno o più campi di testo sono vuoti, il relativo dato non viene aggiunto al contatto.
 * - Se il contatto è già presente nella lista:
 *   - Viene caricata una finestra modale che consente di gestire i duplicati.
 *   - Il controller della vista dei duplicati (`DuplicateContactViewController`) viene inizializzato 
 *     con un contatto fittizio e aggiornato in base all'input dell'utente.
 * - Se il contatto non è un duplicato, viene aggiunto alla lista dei contatti.
 * - Dopo l'aggiunta, la finestra corrente viene chiusa.
 * 
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
            Contact c1=new Contact("Prova", "Prova");
            duplicateC.set(c1);

            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(btnAdd.getScene().getWindow());
            newStage.showAndWait();
                
            if (c1.getName().equals("")){
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
 * @brief Gestisce l'azione di annullamento.
 * 
 * Questo metodo viene chiamato quando l'utente attiva l'azione di annullamento. Chiude la finestra 
 * attualmente aperta senza apportare modifiche ai dati o eseguire ulteriori operazioni.
 * 
 * @param event L'evento che ha scatenato l'azione, tipicamente un clic sul bottone di annullamento.
 * 
 * @details 
 * - Recupera la finestra (stage) associata al bottone di annullamento `btnCanc`.
 * - Chiude la finestra corrente utilizzando il metodo `close()` dello stage.
 * 
 * @note Questo metodo non modifica alcun dato e si limita a chiudere la finestra.
 */
    public void actionCancel(ActionEvent event){
        Stage stage=(Stage) btnCanc.getScene().getWindow();
        stage.close();
    }
    
        
}
