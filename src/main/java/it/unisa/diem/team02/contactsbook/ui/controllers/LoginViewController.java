package it.unisa.diem.team02.contactsbook.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
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
 * @lang it
 * Controller per la gestione della schermata di login e registrazione.
 * Questo controller gestisce la validazione dei campi email e password.
 * 
 * @lang en
 * Controller for handling the login and registration screen.
 * This controller manages the validation of the email and password fields.
 * 
 * @author team02
 */
public class LoginViewController implements Initializable {

    @FXML
    private AnchorPane login; ///< @lang it Componente principale della schermata di login. 
                               ///< @lang en Main component of the login screen.
    
    @FXML
    private VBox vBoxLogin; ///< @lang it VBox per il modulo di login.
                           ///< @lang en VBox for the login form.
    
    @FXML
    private Label lblLogMail; ///< @lang it Etichetta per il campo email del login.
                              ///< @lang en Label for the login email field.
    
    @FXML
    private TextField txtLogMail; ///< @lang it Campo di testo per l'inserimento dell'email del login.
                                 ///< @lang en Text field for entering the login email.
    
    @FXML
    private Label lblLogPass; ///< @lang it Etichetta per il campo password del login.
                              ///< @lang en Label for the login password field.
    
    @FXML
    private PasswordField txtLogPass; ///< @lang it Campo password per l'inserimento della password del login.
                                      ///< @lang en Password field for entering the login password.
   
    @FXML
    private Button btnLogin; ///< @lang it Pulsante per eseguire il login.
                            ///< @lang en Button to execute login.
    
    @FXML
    private Label lblSignMail; ///< @lang it Etichetta per il campo email della registrazione.
                              ///< @lang en Label for the registration email field.
    
    @FXML
    private TextField txtSignMail; ///< @lang it Campo di testo per l'inserimento dell'email della registrazione.
                                 ///< @lang en Text field for entering the registration email.
    
    @FXML
    private Label lblSignPass; ///< @lang it Etichetta per il campo password della registrazione.
                              ///< @lang en Label for the registration password field.
    
    @FXML
    private PasswordField txtSignPass; ///< @lang it Campo password per l'inserimento della password della registrazione.
                                      ///< @lang en Password field for entering the registration password.
    
    @FXML
    private Label lblSignConfirm; ///< @lang it Etichetta per il campo di conferma password.
                                 ///< @lang en Label for the confirm password field.
    
    @FXML
    private PasswordField txtConfirmPass; ///< @lang it Campo password per confermare la password della registrazione.
                                         ///< @lang en Password field to confirm the registration password.
    
    @FXML
    private Button btnSign; ///< @lang it Pulsante per eseguire la registrazione.
                           ///< @lang en Button to execute registration.
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
     * @lang it
     * Metodo di inizializzazione del controller.
     * Richiama i metodi che inizializzano i vari componenti.
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
        btnSign.disableProperty().bind(Bindings.or(txtConfirmPassInitialize(), txtSignMailInitialize()).or(txtSignPassInitialize()));
    }
    
    
    /**
     * @lang it
     * Configura il listener per la validazione del campo email nella registrazione.
     * Restituisce un BooleanProperty:
     *  -false se l'email è valida
     *  -true se l'email non è valida
     * 
     * @lang en
     * Configures the listener for validating the registration email field.
     * Returns a BooleanProperty:
     *  -false if the email is valid
     *  -true if the email is not valid
     */
    private BooleanProperty txtSignMailInitialize(){
        BooleanProperty observableBoolean = new SimpleBooleanProperty(true);
        txtSignMail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidEmail(newValue)) {
                txtSignMail.setStyle("-fx-border-color: green;");  ///< @lang it Bordo verde se valido
                                                                 ///< @lang en Green border if valid
                observableBoolean.set(false);
                lblErrorEmail.setText("");
                
            } else {
                txtSignMail.setStyle("-fx-border-color: red;");    ///< @lang it Bordo rosso se non valido
                                                                 ///< @lang en Red border if invalid
                lblErrorEmail.setText("Invalid email address");
                
            }
        });
     return observableBoolean;   
    }
    
    /**
     * @lang it
     * Configura il listener per la validazione del campo password nella registrazione.
     * Restituisce un BooleanProperty:
     *  -false se la password è valida
     *  -true se la password non è valida
     * 
     * @lang en
     * Configures the listener for validating the registration password field.
     * Returns a BooleanProperty:
     *  -false if the password is valid
     *  -true if the password is not valid
     */
    private BooleanProperty txtSignPassInitialize(){
        BooleanProperty observableBoolean = new SimpleBooleanProperty(true);
        txtSignPass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidPassword(newValue)) {
                txtSignPass.setStyle("-fx-border-color: green;");  ///< @lang it Bordo verde se valido
                                                                 ///< @lang en Green border if valid
                observableBoolean.set(false);
                lblErrorPass.setText("");
                   
            } else {
                txtSignPass.setStyle("-fx-border-color: red;");    ///< @lang it Bordo rosso se non valido
                                                                 ///< @lang en Red border if invalid
                lblErrorPass.setText("The password must be 8 characters long and contain a special \ncharacter, an uppercase, " + "a lowercase and a number");
                
            }
        }); 
        return observableBoolean;
    }
    
    /**
     * @lang it
     * Configura il listener per verificare che la password di conferma corrisponda alla password principale.
     * Restituisce un BooleanProperty:
     *  -false se le password coincidono
     *  -true se le password non coincidono
     * 
     * @lang en
     * Configures the listener to check that the confirm password matches the main password.
     * Returns a BooleanProperty:
     *  -false if passwords macth
     *  -true if passwords don't match
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
     * @lang it
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
     * @lang en
     * Validates an email address.
     * This method checks if the email address is valid using a regular expression, verifying that:
     * - Starts with a local part that can contain letters, numbers, periods, dashes, and the underscore character (_).
     * - It is followed by the @ symbol.
     * - The domain part (after @) can contain letters, numbers, periods and dashes.
     * - Ends with a top-level domain (TLD) made up of letters only, between 2 and 6 characters in length.
     * 
     * @param email The email to validate.
     * @return true if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
    }
    
    /**
     * @lang it
     * Valida una password in base ai requisiti di sicurezza.
     * La password deve contenere almeno una lettera maiuscola, un numero e un carattere speciale, con una lunghezza minima di 8 caratteri.
     * 
     * @param password La password da validare.
     * @return true se la password è valida, false altrimenti.
     * 
     * @lang en
     * Validates a password based on security requirements.
     * The password must contain at least one uppercase letter, one digit, and one special character, with a minimum length of 8 characters.
     * 
     * @param password The password to validate.
     * @return true if the password is valid, false otherwise.
     */
    private boolean isValidPassword(String password) {
        // Espressione regolare per la validazione della password
        // @lang it Espressione regolare per validare la password
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
    
    
    
    @FXML
    private void actionSignin(ActionEvent event) {
        
    }
    
    /**
     * @lang it
     * Permette il solo accesso locale alle operazioni sulla rubrica. Non viene 
     * effettuato la connessione con il database.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Allows only local access to contacts book operations. The connection to the database is not established.
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
