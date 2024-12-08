
package it.unisa.diem.team02.contactsbook.ui.controllers;

import it.unisa.diem.team02.App;
import it.unisa.diem.team02.contactsbook.model.Contact;
import it.unisa.diem.team02.contactsbook.model.Tag;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller per la gestione della schermata principale della rubrica.
 * @author team02
 */
public class ContactsbookViewController implements Initializable {

    @FXML
    private AnchorPane anchorUp;
    @FXML
    private HBox hboxButton;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtSearch;
    @FXML
    private AnchorPane anchorBottom;
    @FXML
    private TableColumn<Contact, String> clmName;
    @FXML
    private TableColumn<Contact, String> clmEmail;
    @FXML
    private TableColumn<Contact, String> clmNum;
    @FXML
    private TableColumn<Contact, Tag> clmTag;
    @FXML
    private Button btnModify;
    @FXML
    private MenuButton mbtnSort;
    @FXML
    private RadioMenuItem rmSurInc;
    @FXML
    private RadioMenuItem rmSurDec;
    @FXML
    private RadioMenuItem rmNameInc;
    @FXML
    private RadioMenuItem rmNameDec;
    @FXML
    private MenuButton mbtnFilter;
    @FXML
    private CheckMenuItem chkmHome;
    @FXML
    private CheckMenuItem chkmUni;
    @FXML
    private CheckMenuItem chkmJob;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnExport;
    @FXML
    private TableView<Contact> tblvRubrica;
    @FXML
    private TableColumn<Contact, String> clmSur;
    @FXML
    private SplitPane interfacciaRubrica;
    @FXML
    private Button btnLogout;
    
    private ObservableList<Contact> contacts;

/**
 * @brief Metodo di inizializzazione del controller.
 * 
 * Questo metodo viene eseguito automaticamente all'avvio del controller associato alla vista. 
 * Inizializza i componenti e configura il comportamento dei pulsanti e della lista.
 * 
 * @param url URL utilizzato per risolvere il percorso del file FXML.
 * @param rb Oggetto `ResourceBundle` contenente dati di localizzazione.
 * 
 * @details 
 * - Richiama il metodo `createList` per configurare la lista principale.
 * - Richiama il metodo `initializeList` per inizializzare e popolare la lista.
 * - Configura i pulsanti di modifica e cancellazione invocando i metodi `btnMofidyInitialize` 
 *   e `btnDeleteInitialize`.
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createList();
        initializeList();
        btnMofidyInitialize();
        btnDeleteInitialize();
    }    
    
/**
 * @brief Crea e configura la lista dei contatti per la visualizzazione.
 * 
 * Questo metodo inizializza l'elenco dei contatti come una lista osservabile (`ObservableList`) e 
 * configura le colonne della tabella per associare i dati dei contatti ai rispettivi campi.
 * 
 * @details
 * - Inizializza la lista `contacts` come un'istanza di `FXCollections.observableArrayList`.
 * - Configura le colonne della tabella (`clmName`, `clmSur`, `clmNum`, `clmEmail`) 
 *   utilizzando il meccanismo di associazione tramite `PropertyValueFactory`.
 * - Associa la lista `contacts` al componente `TableView` (`tblvRubrica`) per visualizzarla nella UI.
 * 
 * @note È necessario che i nomi delle proprietà utilizzati in `PropertyValueFactory` corrispondano 
 *       esattamente ai nomi delle variabili nei modelli dei dati (ad esempio, `name`, `surname`).
 * 
 * @see FXCollections.observableArrayList()
 * @see PropertyValueFactory
 * @see TableView
 */
    public void createList(){
        contacts = FXCollections.observableArrayList();
        clmName.setCellValueFactory(new PropertyValueFactory("name"));
        clmSur.setCellValueFactory(new PropertyValueFactory("surname"));
        clmNum.setCellValueFactory(new PropertyValueFactory("number"));
        clmEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tblvRubrica.setItems(contacts);
    }
    
    /**
     * Inizializza la lista osservabile con i contatti presenti nel database/file locale.
     */
    public void initializeList(){
        
    }
    
/**
 * lang it
 * @brief Gestisce l'evento di aggiunta di un nuovo contatto.
 * 
 * Questo metodo viene invocato quando si verifica un evento di tipo `ActionEvent`, 
 * come il clic su un pulsante per aggiungere un nuovo contatto. Carica la vista FXML 
 * per l'aggiunta di un contatto, imposta un controller con una lista osservabile di 
 * contatti e mostra la finestra modale per l'inserimento di un nuovo contatto.
 * 
 * @param event L'evento che ha scatenato l'azione.
 * 
 * @throws IOException Se c'è un errore nel caricamento del file FXML.
 * 
 * @note La finestra di dialogo viene aperta come finestra modale, impedendo l'interazione 
 *       con altre finestre finché non viene chiusa.
 */
    @FXML

    public void actionAdd(ActionEvent event) throws IOException{
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddView.fxml"));
              Parent root = loader.load();
              Scene scene=new Scene(root);
              
              AddViewController addC=loader.getController();
              addC.setObservableList(contacts);
              
    
              //gestire eccezione
              Stage newStage = new Stage();
              newStage.setScene(scene);
              
              newStage.initModality(Modality.WINDOW_MODAL);
              newStage.initOwner(btnAdd.getScene().getWindow());
              newStage.show();
              
    }
    
/**
 * @brief Gestisce l'azione di modifica di un contatto selezionato.
 * 
 * Questo metodo apre una nuova finestra per consentire la modifica delle informazioni relative al 
 * contatto selezionato dalla tabella dei contatti.
 * 
 * @param event L'evento che ha scatenato l'azione, tipicamente un clic sul bottone di modifica.
 * 
 * @throws IOException Se si verifica un errore durante il caricamento del file FXML della vista di modifica.
 * 
 * @details 
 * - Carica la vista di modifica da un file FXML (`ModifyView.fxml`) utilizzando un `FXMLLoader`.
 * - Crea una nuova scena e associa il controller `ModifyViewController` per gestire la logica della modifica.
 * - Configura una nuova finestra (`Stage`) con modalità modale (`WINDOW_MODAL`) per bloccare l'interazione con 
 *   la finestra principale finché la finestra di modifica non viene chiusa.
 * - Recupera il contatto selezionato nella tabella dei contatti e lo passa al controller della vista di modifica 
 *   tramite il metodo `setContact`.
 * - Passa anche la lista dei contatti al controller tramite il metodo `setObservableList`.
 * 
 * @note Il metodo presuppone che un contatto sia selezionato nella tabella.
 * 
 * @see ModifyViewController#setContact(Contact)
 * @see ModifyViewController#setObservableList(ObservableList)
 * @see FXMLLoader
 */
    @FXML
    public void actionModify(ActionEvent event) throws IOException{
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModifyView.fxml"));
            
              Parent root = loader.load();
              Scene scene=new Scene(root);
              
              ModifyViewController modifyC=loader.getController();          
    
              //gestire eccezione
              Stage newStage = new Stage();
              newStage.setScene(scene);
              
              newStage.initModality(Modality.WINDOW_MODAL);
              newStage.initOwner(btnModify.getScene().getWindow());
              newStage.show();
              
              Contact selectedContact = tblvRubrica.getSelectionModel().getSelectedItem();
              modifyC.setObservableList(contacts);
              modifyC.setContact(selectedContact);
    }
    
/**
 * @brief Inizializza il comportamento del pulsante di modifica.
 * 
 * Questo metodo disabilita inizialmente il pulsante di modifica e aggiunge un listener alla selezione della tabella
 * per abilitare o disabilitare il pulsante a seconda che un contatto sia selezionato o meno.
 * 
 * @details
 * - Inizialmente, il pulsante `btnModify` è disabilitato (non cliccabile).
 * - Aggiunge un listener alla proprietà di selezione della tabella (`tblvRubrica`). Ogni volta che la selezione cambia,
 *   il listener verifica se un contatto è stato selezionato. Se un contatto è selezionato, il pulsante viene abilitato,
 *   altrimenti rimane disabilitato.
 * 
 * @see ChangeListener
 * @see ObservableValue
 * @see TableView
 */
    public void btnMofidyInitialize(){
        btnModify.setDisable(true);

        tblvRubrica.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                btnModify.setDisable(newValue == null);
            }
        });
    }
    
/**
 * @brief Inizializza il comportamento del pulsante di eliminazione.
 * 
 * Questo metodo disabilita inizialmente il pulsante di eliminazione e aggiunge un listener alla selezione della tabella
 * per abilitare o disabilitare il pulsante a seconda che un contatto sia selezionato o meno.
 * 
 * @details
 * - Inizialmente, il pulsante `btnDelete` è disabilitato (non cliccabile).
 * - Aggiunge un listener alla proprietà di selezione della tabella (`tblvRubrica`). Ogni volta che la selezione cambia,
 *   il listener verifica se un contatto è stato selezionato. Se un contatto è selezionato, il pulsante viene abilitato,
 *   altrimenti rimane disabilitato.
 * 
 * @see ChangeListener
 * @see ObservableValue
 * @see TableView
 */
    public void btnDeleteInitialize(){
        btnDelete.setDisable(true);

        tblvRubrica.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                btnDelete.setDisable(newValue == null);
            }
        });
    }

/**
 * @brief Gestisce l'azione di eliminazione di un contatto selezionato.
 * 
 * Questo metodo viene chiamato quando l'utente seleziona un contatto dalla tabella e clicca sul pulsante di eliminazione.
 * Il contatto selezionato viene rimosso dalla lista dei contatti.
 * 
 * @param event L'evento generato dal clic sul pulsante di eliminazione.
 * 
 * @see Contact
 * @see TableView
 */
    @FXML
    private void actionDelete(ActionEvent event) {
        Contact selectedContact = tblvRubrica.getSelectionModel().getSelectedItem();
        contacts.remove(selectedContact); 
    }
    
    /**
     * 
     * Implementa l'azione associcata al menu button Filter: vengono visualizzati solo i contatti associati
     * al tag selezionato.
     * 
     * @param event
     * 
     */
    @FXML
    private void actionFilter(ActionEvent event) {
    }

    /**
     * 
     * Implementa l'azione associcata al tasto Import: tutti i contatti di un file indicato sono aggiunti
     * nella rubrica.
     * 
     * @param event
     * 
     */
    @FXML
    private void actionImport(ActionEvent event) {
    }

    /**
     * 
     * Implementa l'azione associcata al tasto Export: tutti i contatti della rubrica sono esportati in 
     * un file specificato.
     * 
     * @param event
     * 
     */
    @FXML
    private void actionExport(ActionEvent event) {
    }
    
    /**
     * 
     * Vengono visualizzati solo i contatti della rubrica contenenti la sottostringa inserita nella barra
     * di ricarca
     * 
     * @param event
     * 
     */
    @FXML
    private void actionSearch(ActionEvent event) {
    }
    
/**
 * @brief Gestisce l'azione di logout e il ritorno alla schermata di login.
 * 
 * Questo metodo viene chiamato quando l'utente clicca sul pulsante di logout. Se l'accesso è stato effettuato tramite database,
 * il metodo si occupa prima di disconnettere l'utente dal database e poi cambia la vista per tornare alla schermata di login.
 * Il controllo del tipo di accesso viene gestito tramite una variabile statica booleana nella classe `LoginViewController`.
 * 
 * @param event L'evento generato dal clic sul pulsante di logout.
 * 
 * @note Se l'accesso è stato effettuato tramite il database, è necessario prima disconnettersi prima di tornare alla schermata di login.
 *       Se l'accesso non è stato tramite database, la disconnessione non è necessaria.
 * 
 * @see App#setRoot(String)
 * @see LoginViewController
 */
    @FXML
    private void actionLogout(ActionEvent event) {
        try{
            App.setRoot("LoginView");} 
        catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
}