
package it.unisa.diem.team02.contactsbook.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.sql.Connection;
import it.unisa.diem.team02.contactsbook.database.Database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unisa.diem.team02.App;
import java.io.IOException;


/**
 * Controller per la gestione della schermata di login e registrazione.
 * Questo controller gestisce la validazione dei campi email e password.
 * 
 * @author team02
 */
public class LoginViewController implements Initializable {

    @FXML
    private AnchorPane login; 
    
    @FXML
    private VBox vBoxLogin; 
    
    @FXML
    private Label lblLogMail; 
    
    @FXML
    private TextField txtLogMail; 
    
    @FXML
    private Label lblLogPass; 
    
    @FXML
    private PasswordField txtLogPass;
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private Label lblSignMail;
    
    @FXML
    private TextField txtSignMail;
    
    @FXML
    private Label lblSignPass;
    
    @FXML
    private PasswordField txtSignPass;
    
    @FXML
    private Label lblSignConfirm;
    
    @FXML
    private PasswordField txtConfirmPass;
    
    @FXML
    private Button btnSign; 
    
    @FXML
    private HBox hboxLogin;
    
    @FXML
    
    private VBox vboxSignin;
    
    @FXML
    private Label lblErrorPass;
    
    @FXML
    private Label lblPassInequals;
    
    @FXML
    private Label lblErrorEmail;
    
    @FXML
    private Label lblLogErr;

/**
 * @brief Inizializza il controller, configurando il binding dei bottoni e dei campi di input.
 * 
 * Questo metodo viene invocato al momento dell'inizializzazione del controller. Imposta i binding per il pulsante di registrazione
 * (`btnSign`), disabilitandolo finché uno dei campi di input non è correttamente compilato. I campi monitorati sono: 
 * la conferma della password, la mail di registrazione e la password.
 * 
 * @param url Il percorso del file FXML, utilizzato per risolvere il percorso del file di configurazione.
 * @param rb Un oggetto `ResourceBundle` contenente i dati di localizzazione per l'internazionalizzazione dell'applicazione.
 * 
 * @see Bindings#or(javafx.beans.binding.BooleanBinding, javafx.beans.binding.BooleanBinding)
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSign.disableProperty().bind(Bindings.or(txtConfirmPassInitialize(), txtSignMailInitialize()).or(txtSignPassInitialize()));
    }
    
    
/**
 * @brief Inizializza il campo di input per l'email di registrazione, impostando il binding e la validazione.
 * 
 * Questo metodo crea un `BooleanProperty` che monitora il valore del campo di input per l'email di registrazione (`txtSignMail`).
 * Aggiunge un listener che valida il formato dell'email ogni volta che il valore cambia. Se l'email è valida, il bordo del campo 
 * viene colorato di verde e l'errore viene rimosso. In caso contrario, il bordo diventa rosso e viene mostrato un messaggio di errore.
 * 
 * @return Una proprietà booleana che indica se l'email è valida o meno (false se valida, true se non valida).
 * 
 * @see isValidEmail(String)
 */
    private BooleanProperty txtSignMailInitialize(){
        BooleanProperty observableBoolean = new SimpleBooleanProperty(true);
        txtSignMail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidEmail(newValue)) {
                txtSignMail.setStyle("-fx-border-color: green;");  ///<  Bordo verde se valido
                                                                 ///< @lang en Green border if valid
                observableBoolean.set(false);
                lblErrorEmail.setText("");
                
            } else {
                txtSignMail.setStyle("-fx-border-color: red;");    ///<  Bordo rosso se non valido
                                                                 ///< @lang en Red border if invalid
                lblErrorEmail.setText("Invalid email address");
                
            }
        });
     return observableBoolean;   
    }
    
/**
 * @brief Inizializza il campo di input per la password di registrazione, impostando il binding e la validazione.
 * 
 * Questo metodo crea un `BooleanProperty` che monitora il valore del campo di input per la password di registrazione (`txtSignPass`).
 * Aggiunge un listener che valida la password ogni volta che il valore cambia. Se la password è valida, il bordo del campo 
 * viene colorato di verde e l'errore viene rimosso. In caso contrario, il bordo diventa rosso e viene mostrato un messaggio di errore.
 * La password è considerata valida se ha una lunghezza di almeno 8 caratteri e contiene almeno un carattere speciale, 
 * una lettera maiuscola, una lettera minuscola e un numero.
 * 
 * @return Una proprietà booleana che indica se la password è valida o meno (false se valida, true se non valida).
 * 
 * @see isValidPassword(String)
 */
    private BooleanProperty txtSignPassInitialize(){
        BooleanProperty observableBoolean = new SimpleBooleanProperty(true);
        txtSignPass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidPassword(newValue)) {
                txtSignPass.setStyle("-fx-border-color: green;");  ///<  Bordo verde se valido
                                                                 ///< @lang en Green border if valid
                observableBoolean.set(false);
                lblErrorPass.setText("");
                   
            } else {
                txtSignPass.setStyle("-fx-border-color: red;");    ///<  Bordo rosso se non valido
                                                                 ///< @lang en Red border if invalid
                lblErrorPass.setText("The password must be 8 characters long and contain a special \ncharacter, an uppercase, " + "a lowercase and a number");
                
            }
        }); 
        return observableBoolean;
    }
    
/**
 * @brief Inizializza il campo di input per la conferma della password di registrazione, impostando il binding e la validazione.
 * 
 * Questo metodo crea una `BooleanProperty` che monitora il valore del campo di input per la conferma della password (`txtConfirmPass`).
 * Aggiunge un listener che verifica se il valore inserito nel campo di conferma della password corrisponde al valore della password principale (`txtSignPass`).
 * Se le password corrispondono, il bordo del campo di conferma viene colorato di verde e il messaggio di errore viene rimosso. In caso contrario, il bordo 
 * diventa rosso e viene mostrato un messaggio di errore che indica che le password non corrispondono.
 * 
 * @return Una proprietà booleana che indica se le password corrispondono o meno (false se corrispondono, true se non corrispondono).
 */
    private BooleanProperty txtConfirmPassInitialize(){
        BooleanProperty b=new SimpleBooleanProperty(true);
        txtConfirmPass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(txtSignPass.getText())) {
                txtConfirmPass.setStyle("-fx-border-color: green;");  // Bordo verde se corrispondono
                lblPassInequals.setText("");
                b.set(false);
            } else {
                txtConfirmPass.setStyle("-fx-border-color: red;");    // Bordo rosso se non corrispondono
                lblPassInequals.setText("The passwords do not match");
                
            }
        });
        return b;
    }
    
    
    
/**
 * 
 * Valida un indirizzo email.
 * Questo metodo controlla se l'indirizzo email è valido seguendo una espressione regolare, verificando che:
 * - Inizia con una parte locale che può contenere lettere, numeri, punti, trattini e il carattere di sottolineatura (_).
 * - È seguito dal simbolo @.
 * - La parte del dominio (dopo @) può contenere lettere, numeri, punti e trattini.
 * - Termina con un dominio di primo livello (TLD) composto solo da lettere, con una lunghezza compresa tra 2 e 6 caratteri.
 * 
 * @param email L'email da validare.
 * @return true se l'email è valida, false altrimenti.
 * 
 */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
    }
    
/**
 * @brief Verifica la validità della password in base ai criteri di sicurezza.
 * 
 * Questo metodo utilizza una espressione regolare per verificare che la password soddisfi determinati criteri di sicurezza:
 * - Contiene almeno una lettera maiuscola.
 * - Contiene almeno un numero.
 * - Contiene almeno un carattere speciale (come !, @, #, $, %, ecc.).
 * - Ha una lunghezza minima di 8 caratteri.
 * 
 * @param password La password da validare.
 * 
 * @return `true` se la password soddisfa i criteri di sicurezza, altrimenti `false`.
 * 
 * @note L'espressione regolare utilizzata è: 
 *        ^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?/]).{8,}$
 */
    private boolean isValidPassword(String password) {
        // Espressione regolare per la validazione della password
        //  Espressione regolare per validare la password
        // @lang en Regular expression to validate the password
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?/]).{8,}$";
        return password.matches(passwordRegex);
    }
    
    
    /**
    * @brief Inizializza il pulsante di login e gestisce l'autenticazione dell'utente.
    * 
    * Questo metodo imposta un listener sul pulsante di login. Verifica le credenziali inserite
    * con il database e fornisce un feedback all'utente. Se il login è corretto, carica la vista principale dei contatti.
    * 
    * @details
    * - Connette al database PostgreSQL utilizzando la classe Database.
    * - Verifica l'email e la password attraverso il metodo checkLogin().
    * - Mostra messaggi di errore specifici in caso di fallimento.
    * 
    * @throws SQLException Se si verifica un problema con la connessione al database.
    */
    
    @FXML
    private void actionLogin(ActionEvent event) {
        Database database= new Database();
        Connection connection = database.ConnectionDB("rubrica", "postgres", "postgres");
            try {
                int controllo=database.checkLogin(connection, "utenti", txtLogMail.getText(), txtLogPass.getText());
                switch(controllo){
                    case -1:
                    lblLogErr.setText("Error. There is no account associated with this email.");    
                    break;
                    
                    case 1:
                    lblLogErr.setText("Login successfully."); 
                    {
                        try {
                            App.setRoot("ContactsbookView");
                        } catch (IOException ex) {
                            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                    
                    case 0:
                    lblLogErr.setText("Incorrect password.");
                    
                    default:
                    lblLogErr.setText("Oops, something went wrong...");
                }
            } catch (SQLException ex) {
                lblLogErr.setText("Unable to contact the server, please try again later.");
            }
    }
    
    /*
    * Implementa l'azione associata al pulsante Sign-In. Se le credenziali inserite
    * non sono già presenti nel database permette all'utente di registrarsi
    */
    
    @FXML
    private void actionSignin(ActionEvent event) {
        
    }
    
/**
 * Gestisce l'azione di login locale.
 * 
 * Questo metodo viene chiamato quando l'utente interagisce con il componente di login per accedere all'applicazione. 
 * Dopo una validazione, se il login è riuscito, il metodo carica la vista dei contatti.
 * 
 * @param event L'evento che ha attivato il metodo, tipicamente un'azione di clic su un pulsante.
 * @throws IOException Se si verifica un errore durante il caricamento della vista dei contatti.
 */
    
    @FXML
    private void actionLoginLocal(ActionEvent event){
        try{ 
            App.setRoot("ContactsbookView");} 
        catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
