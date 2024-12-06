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
 * @lang it
 * Controller per la gestione della schermata principale della rubrica.
 * 
 * @lang en
 * Controller for handling the contactbook main screen.
 * 
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
        createList();
        initializeList();
        btnMofidyInitialize();
        btnDeleteInitialize();
    }    
    
    /**
     * @lang it
     * Crea una lista osservabile con campi: Name, Surname, Number, Email e la associa alla tabella 
     * dell'interfaccia grafica.
     * 
     * @lang en
     * Creates an observable list with the fields: Name, Surname, Number, Email and it associate it to the 
     * table af the graphical interface.
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
     * @lang it
     * Inizializza la lista osservabile con i contatti presenti nel database/file locale.
     * 
     * @lang en
     * Initialize the observable list with the contacts present in the database/local file.
     */
    public void initializeList(){
        
    }
    
    /**
     * @lang it
     * Implementa l'azione associcata al tasto add: si apre una schermata per aggiungere un contatto e la 
     * lista osservabile viene passata al controller.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the add button: a screen opens to add a contact, and the 
     * observable list is passed to the controller.
     * 
     * @param ActionEvent event
     * @throws IOException
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
     * @lang it
     * Implementa l'azione associcata al tasto modify: si apre una schermata per modificare il contatto 
     * selezionato e la lista osservabile viene passata al controller.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the modify button: a screen opens to modify the selected 
     * contact, and the observable list is passed to the controller.
     * 
     * @param ActionEvent event
     * @throws IOException
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
     * @lang it
     * Il bottone Modify non può essere premuto se non è stato selezionato alcun contatto. Per
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * The Modify button cannot be pressed if no contact has been selected.
     * 
     * @param ActionEvent event
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
     * @lang it
     * Il bottone Delete non può essere premuto se non è stato selezionato alcun contatto. 
     * 
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * The Delete button cannot be pressed if no contact has been selected.
     * 
     * @param ActionEvent event
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
     * @lang it
     * Implementa l'azione associcata al tasto delete: il contatto selezionato viene eliminato dalla rubrica.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the delete button: the selected contact is deleted from the 
     * contactbook.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionDelete(ActionEvent event) {
        Contact selectedContact = tblvRubrica.getSelectionModel().getSelectedItem();
        contacts.remove(selectedContact); 
    }
    
    /**
     * @lang it
     * Implementa l'azione associcata al menu button Filter: vengono visualizzati solo i contatti associati
     * al tag selezionato.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the Filter menu button: only the contacts associated with 
     * the selected tag are displayed.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionFilter(ActionEvent event) {
    }

    /**
     * @lang it
     * Implementa l'azione associcata al tasto Import: tutti i contatti di un file indicato sono aggiunti
     * nella rubrica.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the Import button: all contacts from a specified file are 
     * added to the contactbook. 
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionImport(ActionEvent event) {
    }

    /**
     * @lang it
     * Implementa l'azione associcata al tasto Export: tutti i contatti della rubrica sono esportati in 
     * un file specificato.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the Export button: all contacts in the contactbook are 
     * exported in a specified file.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionExport(ActionEvent event) {
    }
    
    /**
     * @lang it
     * Vengono visualizzati solo i contatti della rubrica contenenti la sottostringa inserita nella barra
     * di ricarca
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Only the contacts in the contactbook containing the substring entered in the search bar are 
     * displayed.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionSearch(ActionEvent event) {
    }
    
    /**
     * @lang it
     * Implementa l'azione associcata al tasto Logout: l'account viene disconnesso e si torna alla schermata
     * di login/sign in.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the Logout button: The account is logged out, and the system
     * returns to the login/sign-in screen.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionLogout(ActionEvent event) {
        //Se l'accesso è stato effettuato con il database bisogna prima disconnettersi e poi si può tornare alla schermata di login.
        //Il controllo del tipo di accesso si può realizzare con una variabile statica booleana di LoginViewController.
        //Se è vera è stato fatto l'acceso con il database, altrimenti no
        
        try{
            App.setRoot("LoginView");} 
        catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
}
