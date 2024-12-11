
package it.unisa.diem.team02.contactsbook.ui.controllers;

import it.unisa.diem.team02.App;
import it.unisa.diem.team02.contactsbook.model.Contact;
import it.unisa.diem.team02.contactsbook.model.Contactbook;
import it.unisa.diem.team02.contactsbook.model.Tag;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @brief Controller per la gestione della schermata principale della rubrica.
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
    private TableColumn<Contact, String> clmTag;
    @FXML
    private Button btnModify;
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
    
    private Contactbook contactbook=new Contactbook();

/**
 * @brief Inizializza il controller e configura gli elementi dell'interfaccia utente.
 * 
 * Questo metodo viene invocato automaticamente all'avvio della scena e prepara la tabella,
 * i bottoni di modifica e cancellazione, e la funzionalità di ricerca.
 * 
 * @pre La scena e gli elementi dell'interfaccia utente devono essere già stati caricati.
 * @post Gli elementi dell'interfaccia sono stati configurati correttamente.
 * @invariant I bottoni di modifica e cancellazione sono abilitati e la ricerca è configurata.
 * 
 * @see createList(), initializeList(), btnModifyInitialize(), btnDeleteInitialize(), initializeSearch()
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindingList();
        btnMofidyInitialize();
        btnDeleteInitialize();
        initializeSearch(contactbook.getFlContacts());
        mbtnFilter.setOnShown(event->{
           actionFilter(contactbook.getFlContacts());
        });
    }    
    
/**
 * @brief Gestisce il legame tra la lista dei contatti e la tabella.
 * 
 * Questo metodo configura le colonne della tabella per visualizzare i dettagli di ciascun contatto: nome, 
 * cognome, numero di telefono, email e tag.
 * 
 * @pre La tabella e le colonne devono essere già configurate nell'interfaccia utente.
 * @post La tabella è configurata per visualizzare i dati.
 * @invariant La tabella visualizzerà correttamente i contatti con i dettagli impostati nelle rispettive colonne.
 */
    public void bindingList(){
        clmName.setCellValueFactory(new PropertyValueFactory("name"));
        clmSur.setCellValueFactory(new PropertyValueFactory("surname"));
        clmNum.setCellValueFactory(new PropertyValueFactory("number"));
        clmEmail.setCellValueFactory(new PropertyValueFactory("email"));
        clmTag.setCellValueFactory(new PropertyValueFactory("tag"));
        tblvRubrica.setItems(contactbook.getContacts());
    }
    
/**
 * @brief Apre una nuova finestra per aggiungere un nuovo contatto.
 * 
 * Questo metodo carica la vista per aggiungere un nuovo contatto, inizializza la lista dei contatti
 * da visualizzare nella nuova finestra, e mostra la finestra in modalità modale.
 * 
 * @param event L'evento di azione che attiva il metodo.
 * 
 * @throws IOException Se si verifica un errore durante il caricamento della vista FXML.
 * 
 * @pre La scena corrente deve contenere il bottone di aggiunta per attivare questa azione.
 * @post Una nuova finestra viene mostrata con la possibilità di aggiungere un nuovo contatto alla lista.
 * @invariant La nuova finestra è modale e non permette di interagire con la finestra principale fino alla sua chiusura.
 * 
 * @see AddViewController
 */
    @FXML
    public void actionAdd(ActionEvent event) throws IOException{
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddView.fxml"));
              Parent root = loader.load();
              Scene scene=new Scene(root);
              
              AddViewController addC=loader.getController();
              addC.setContactbook(contactbook);
              
    
              //gestire eccezione
              Stage newStage = new Stage();
              newStage.setScene(scene);
              
              newStage.initModality(Modality.WINDOW_MODAL);
              newStage.initOwner(btnAdd.getScene().getWindow());
              newStage.showAndWait(); 
    }
    
/**
 * @brief Apre una finestra modale per modificare un contatto selezionato.
 * 
 * Questo metodo carica la vista per modificare un contatto esistente, inizializza la lista dei contatti,
 * e mostra la finestra in modalità modale. Inoltre, imposta il contatto selezionato nella lista
 * per essere modificato nella nuova finestra.
 * 
 * @param event L'evento di azione che attiva il metodo.
 * 
 * @throws IOException Se si verifica un errore durante il caricamento della vista FXML.
 * 
 * @pre La scena corrente deve contenere il bottone di modifica e un contatto selezionato dalla lista.
 * @post Una nuova finestra viene mostrata con il contatto selezionato, pronto per essere modificato.
 * @invariant La finestra di modifica è modale e non permette di interagire con la finestra principale fino alla sua chiusura.
 * 
 * @see ModifyViewController
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
              modifyC.setContactbook(contactbook);
              modifyC.setContact(selectedContact);
    }
    
/**
 * @brief Inizializza il comportamento del bottone di modifica.
 * 
 * Questo metodo imposta lo stato del bottone di modifica (`btnModify`) su disabilitato inizialmente. 
 * Inoltre, aggiunge un listener alla selezione della tabella dei contatti (`tblvRubrica`). Quando viene selezionato un contatto,
 * il bottone di modifica viene abilitato; se nessun contatto è selezionato, il bottone viene disabilitato.
 * 
 * @pre La tabella dei contatti deve essere visibile e contenere degli elementi.
 * @post Il bottone di modifica sarà disabilitato finché non viene selezionato un contatto nella tabella.
 * @invariant Il bottone di modifica rimane disabilitato se nessun contatto è selezionato.
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
 * @brief Inizializza il comportamento del bottone di eliminazione.
 * 
 * Questo metodo imposta lo stato del bottone di eliminazione (`btnDelete`) su disabilitato inizialmente. 
 * Viene poi aggiunto un listener alla selezione della tabella dei contatti (`tblvRubrica`). Quando viene selezionato un contatto,
 * il bottone di eliminazione viene abilitato; se nessun contatto è selezionato, il bottone viene disabilitato.
 * 
 * @details Il bottone di eliminazione permette all'utente di eliminare un contatto dalla rubrica. 
 * Tuttavia, il bottone è disabilitato quando non è selezionato alcun contatto, impedendo azioni non desiderate.
 * 
 * @pre La tabella dei contatti deve essere visibile e contenere degli elementi.
 * @post Il bottone di eliminazione sarà disabilitato finché non viene selezionato un contatto nella tabella.
 * @invariant Il bottone di eliminazione rimane disabilitato se nessun contatto è selezionato.
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
 * @brief Elimina un contatto selezionato dalla tabella.
 * 
 * Questo metodo rimuove il contatto attualmente selezionato dalla tabella dei contatti 
 * (`tblvRubrica`) dalla lista `contacts`. L'eliminazione è eseguita sulla base della selezione 
 * effettuata dall'utente. Se non viene selezionato alcun contatto, il metodo non avrà alcun effetto.
 * 
 * @details Quando l'utente clicca sul bottone di eliminazione, il contatto selezionato viene 
 * rimosso dalla lista `contacts` associata alla tabella. Questo comporta l'aggiornamento 
 * dinamico della vista della tabella, con la rimozione visibile del contatto.
 * 
 * @pre Un contatto deve essere selezionato nella tabella (`tblvRubrica`).
 * @post Il contatto selezionato viene rimosso dalla lista `contacts` e dalla vista della tabella.
 * @invariant La lista `contacts` rimane consistente dopo l'eliminazione del contatto.
 * 
 * @see contacts
 */
    @FXML
    private void actionDelete(ActionEvent event) {
        Contact selectedContact = tblvRubrica.getSelectionModel().getSelectedItem();
        contactbook.delete(selectedContact);
    }
    
/**
 * @brief Applica un filtro alla lista di contatti mostrata nella tabella.
 *
 * Questo metodo imposta la lista filtrata `FilteredList` di contatti sulla tabella `tblvRubrica` 
 * e aggiunge dei listener alle checkbox (`chkmHome`, `chkmJob`, `chkmUni`) per aggiornare il filtro
 * ogni volta che una di queste viene selezionata o deselezionata. Ogni cambio di stato nelle checkbox
 * farà chiamare il metodo `updateFilter` per aggiornare la visualizzazione della lista dei contatti.
 *
 * @pre La lista `flContacts` deve essere una lista filtrata valida di contatti.
 * @pre La tabella `tblvRubrica` deve essere inizializzata correttamente e pronta per l'aggiornamento.
 * @post La lista filtrata viene applicata alla tabella e i listener sono stati aggiunti per monitorare
 *       le modifiche nei checkbox e aggiornare il filtro di conseguenza.
 * @invariant Il filtro sui contatti deve essere applicato correttamente in base alle selezioni delle checkbox.
 *
 * @param flContacts La lista filtrata di contatti da visualizzare nella tabella.
 */
    private void actionFilter(FilteredList<Contact> flContacts) {
        tblvRubrica.setItems(flContacts);
        chkmHome.selectedProperty().addListener((obs, oldValue, newValue) -> updateFilter());
        chkmJob.selectedProperty().addListener((obs, oldValue, newValue) -> updateFilter());
        chkmUni.selectedProperty().addListener((obs, oldValue, newValue) -> updateFilter());
        }

    
    /**
 * @brief Aggiorna il filtro dei contatti in base ai criteri di ricerca e selezione dei tag.
 *
 * Questo metodo applica un filtro alla lista di contatti `flContacts` in base al testo di ricerca inserito
 * nell'elemento `txtSearch` e alle selezioni delle checkbox dei tag (`chkmHome`, `chkmUni`, `chkmJob`).
 * Se il testo di ricerca non è vuoto, il filtro cercherà i contatti che contengono il testo nelle loro
 * proprietà `name`, `surname`, `number` ed `email`. Inoltre, il filtro applica i tag selezionati
 * (Home, University, Job) se corrispondono al tag associato al contatto.
 *
 * @pre Il campo di ricerca `txtSearch` deve essere inizializzato e contenere il testo di ricerca.
 * @pre Le checkbox `chkmHome`, `chkmUni` e `chkmJob` devono essere inizializzate e il loro stato deve
 *      riflettere le preferenze dell'utente.
 * @post La lista `flContacts` è filtrata in base al testo di ricerca e alle selezioni dei tag, aggiornando
 *       i contatti visibili nella lista.
 * @invariant I contatti che soddisfano i criteri di ricerca e tag selezionati saranno visibili nella lista.
 *
 * @return Nessun valore restituito. La lista di contatti viene modificata direttamente.
 */
    
     private void updateFilter() {
            contactbook.getFlContacts().setPredicate(contact -> {
                
            String lowerCaseFilter = txtSearch.getText().toLowerCase();
            boolean match = lowerCaseFilter.isEmpty() ||  
                    contact.getName().toLowerCase().contains(lowerCaseFilter) ||
                    contact.getSurname().toLowerCase().contains(lowerCaseFilter) ||
                    contact.getNumber().toLowerCase().contains(lowerCaseFilter) ||
                    contact.getEmail().toLowerCase().contains(lowerCaseFilter);
            

            boolean noTag = !chkmHome.isSelected() && !chkmUni.isSelected() && !chkmJob.isSelected();


            boolean home = chkmHome.isSelected() && contact.getTag().toLowerCase().contains("home");
            boolean uni = chkmUni.isSelected() && contact.getTag().toLowerCase().contains("university");
            boolean job = chkmJob.isSelected() && contact.getTag().toLowerCase().contains("job");

           
            return match && (noTag || home || uni || job);
        });
    }
    
         
    /**
     * 
     * Vengono visualizzati solo i contatti della rubrica contenenti la sottostringa inserita nella barra
     * di ricerca
     * 
     * @param event
     * 
     */
    private void initializeSearch(FilteredList<Contact> flContacts) {
        
        tblvRubrica.setItems(flContacts);
        SimpleStringProperty string = new SimpleStringProperty("");
        
        txtSearch.textProperty().bindBidirectional(string);
        
        //Listener al campo testo
        txtSearch.textProperty().addListener((obs, oldValue, newValue) -> updateFilter());
        
        
        
    }
    

/**
 * @brief Importa i contatti da un file CSV e li aggiunge alla rubrica.
 * 
 * Questo metodo permette all'utente di selezionare un file CSV tramite un dialogo di selezione 
 * file. Ogni riga del file CSV viene letta e i dati vengono utilizzati per creare nuovi oggetti 
 * `Contact`, che vengono successivamente aggiunti alla lista `contacts`. Il formato del file CSV 
 * deve essere conforme alla struttura prevista, con i campi separati da punto e virgola.
 * 
 * @details Il metodo apre un file CSV selezionato tramite un `FileChooser`, legge ogni riga e 
 * divide i dati nei rispettivi campi (nome, cognome, numeri di telefono, e-mail). I contatti 
 * creati vengono aggiunti alla lista `contacts` della rubrica. Ogni campo viene controllato per 
 * determinare se è presente o vuoto prima di essere aggiunto come numero di telefono o e-mail.
 * 
 * @pre Il file selezionato deve essere un file CSV valido contenente i dati dei contatti.
 * @post I contatti letti dal file vengono aggiunti alla lista `contacts` e visualizzati nella tabella.
 * @invariant La lista `contacts` è aggiornata con i nuovi contatti e la tabella è sincronizzata con la lista.
 * 
 * @throws IOException Se si verifica un errore durante la lettura del file.
 * @throws ClassNotFoundException Se il tipo di dato non è trovato durante il caricamento dei dati.
 */
    @FXML
    private void actionImport(ActionEvent event) throws IOException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Apri un file");

        Window window = tblvRubrica.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);
        try(BufferedReader br=new BufferedReader(new FileReader(selectedFile))){
            if (br.readLine() == null) return;
            String line;
            while((line=br.readLine())!= null){
                String campi []=line.split(";",-1);
                Contact c=new Contact(campi[0], campi[1]);
                if (!campi[2].equals(""))
                    c.addNumber(campi[2]);
                if (!campi[3].equals(""))
                    c.addNumber(campi[3]);
                if (!campi[4].equals(""))
                    c.addNumber(campi[4]);

                if (!campi[5].equals(""))
                    c.addEmail(campi[5]);
                if (!campi[6].equals(""))
                    c.addEmail(campi[6]);
                if (!campi[7].equals(""))
                    c.addEmail(campi[7]);
                
                if(!campi[8].equals(""))
                    c.addTag(Tag.Home);
                if(!campi[9].equals(""))
                    c.addTag(Tag.University);
                if(!campi[10].equals(""))
                    c.addTag(Tag.Job);
                

                contactbook.add(c);
            }
        }
    }
    
/**
 * @brief Esporta la lista dei contatti in un file CSV.
 *
 * Questo metodo consente all'utente di scegliere un file in cui salvare i contatti presenti nella rubrica.
 * La lista dei contatti viene esportata in formato CSV, con i seguenti campi: "NOME", "COGNOME", "NUMERO DI TELEFONO", 
 * "EMAIL" e "TAG". I numeri di telefono e le email vengono separati da un carattere di nuova linea e ogni valore
 * è separato da un punto e virgola.
 *
 * @pre La lista `contacts` deve essere inizializzata e contenere i contatti da esportare.
 * @post I contatti vengono esportati nel file CSV selezionato dall'utente. Il file viene creato o sovrascritto
 *       con i dati esportati.
 * @invariant I dati dei contatti vengono esportati correttamente, con ogni campo separato da un punto e virgola e ogni
 *            contatto su una nuova riga.
 *
 * @param event L'evento che ha causato l'azione (ad esempio, il clic del pulsante).
 * @throws FileNotFoundException Se il file selezionato non può essere trovato.
 * @throws IOException Se si verifica un errore durante la scrittura nel file.
 */
    @FXML
    private void actionExport(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Scegli un file in cui salvare");
     
        Window window = (tblvRubrica.getParent().getScene().getWindow());
        File selectedFile = fileChooser.showSaveDialog(window);
       
        try(PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(selectedFile)))){
            pw.println("NOME;COGNOME;NUMERO DI TELEFONO;EMAIL;TAG;");
            for (Contact c: contactbook.getContacts()){
                pw.append(c.getName());
                pw.append(';');
                pw.append(c.getSurname());
                pw.append(';');
                
                String[] number=c.getNumber().split("\n");
                if(number.length>=1)
                    pw.append(number[0]);
                pw.append(';');
                if (number.length>=2)
                    pw.append(number[1]);
                pw.append(';');
                 if (number.length>=3)
                    pw.append(number[2]);
                 pw.append(';');
                 
                String[] email=c.getEmail().split("\n");
                if (email.length>=1)
                    pw.append(email[0]);
                pw.append(';');
                if (email.length>=2)
                    pw.append(email[1]);
                pw.append(';');
                 if (email.length>=3)
                    pw.append(email[2]);
                pw.append(';');
                
                String[] tag=c.getTag().split("\n");
                if (tag.length>=1)
                    pw.append(tag[0]);
                pw.append(';');
                if (tag.length>=2)
                    pw.append(tag[1]);
                pw.append(';');
                 if (tag.length>=3)
                    pw.append(tag[2]);
                pw.append(';');
                    
                pw.append('\n');
            }
        }
    }
    
/**
 * @brief Inizializza la funzionalità di ricerca per filtrare i contatti.
 * 
 * Questo metodo imposta un campo di ricerca che permette agli utenti di filtrare i contatti 
 * nella rubrica in base ai criteri inseriti nella barra di ricerca. Ogni volta che l'utente 
 * digita un carattere, la lista dei contatti viene filtrata in tempo reale. La ricerca può essere 
 * effettuata su nome, cognome, numero di telefono ed e-mail.
 * 
 * @details La lista dei contatti è associata a un oggetto `FilteredList`, che consente di 
 * applicare un predicato di filtraggio sui contatti visibili nella `TableView`. Ogni modifica 
 * nel campo di ricerca aggiorna il predicato per eseguire il filtraggio in base al testo inserito. 
 * La ricerca è insensibile al maiuscolo/minuscolo e filtra i contatti che contengono la stringa 
 * inserita in uno dei campi (nome, cognome, numero di telefono, e-mail).
 * 
 * @pre La lista `contacts` deve essere popolata con i contatti da visualizzare nella rubrica.
 * @post La tabella `tblvRubrica` viene aggiornata per mostrare solo i contatti che corrispondono 
 *       ai criteri di ricerca.
 * @invariant La ricerca non modificherà la lista originale di contatti, ma solo quella visibile nella 
 *            `TableView`.
 */
    @FXML
    private void initializeSearch() {
        
        //lista filtrata che lavora sulla lista contacts di osservabili
        FilteredList<Contact> flContacts = new FilteredList(contactbook.getContacts(), c->true);
        tblvRubrica.setItems(flContacts);
        
        //aggiungo un listener al campo testo
        txtSearch.textProperty().addListener((obs,oldValue,newValue) -> {
               flContacts.setPredicate(contact-> {
                // se non c'è scritto nulla sulla barra di ricerca mostra tutti i contatti
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                //è necessario gestire separatamente i valori null
                return contact.getName().toLowerCase().contains(lowerCaseFilter) ||
                       contact.getSurname().toLowerCase().contains(lowerCaseFilter) ||
                       contact.getNumber().toLowerCase().contains(lowerCaseFilter) ||
                       contact.getEmail().toLowerCase().contains(lowerCaseFilter);
               });
               
        });
        
    }
    
/**
 * @brief Esegue il logout dell'utente e redirige alla schermata di login.
 * 
 * Questo metodo gestisce l'azione di logout dell'utente, riportandolo alla schermata di login. 
 * Dopo il logout, l'applicazione cambia la vista corrente alla schermata di login.
 * 
 * @details Quando l'utente esegue il logout, il metodo cerca di cambiare la vista della 
 *           finestra principale a quella di login utilizzando il metodo `App.setRoot()`.
 *           In caso di errore durante il caricamento della vista, viene loggato un errore.
 * 
 * @pre L'utente deve essere loggato e aver effettuato l'accesso alla schermata attuale.
 * @post La schermata attuale viene cambiata con la vista di login.
 * @invariant Nessuna modifica permanente ai dati dell'utente o allo stato dell'applicazione.
 * 
 * @see App#setRoot(String)
 */
    @FXML
    private void actionLogout(ActionEvent event) {
        try{
            App.setRoot("LoginView");} 
        catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
}
