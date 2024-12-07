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
 * 
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
     * 
     * Metodo di inizializzazione del controller.
     * Richiama i metodi che inizializzano i vari componenti.
     * 
     * @param url utilizzato per risolvere il percorso del file FXML.
     * @param rb contenente dati di localizzazione.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSign.disableProperty().bind(Bindings.or(txtConfirmPassInitialize(), txtSignMailInitialize()).or(txtSignPassInitialize()));
    }
    
    
    /**
     * 
     * Configura il listener per la validazione del campo email nella registrazione.
     * Restituisce un BooleanProperty:
     *  -false se l'email è valida
     *  -true se l'email non è valida
     * 
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
     * 
     * Configura il listener per la validazione del campo password nella registrazione.
     * Restituisce un BooleanProperty:
     *  -false se la password è valida
     *  -true se la password non è valida
     * 
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
     * 
     * Configura il listener per verificare che la password di conferma corrisponda alla password principale.
     * Restituisce un BooleanProperty:
     *  -false se le password coincidono
     *  -true se le password non coincidono
     * 
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
     * 
     * Valida una password in base ai requisiti di sicurezza.
     * La password deve contenere almeno una lettera maiuscola, un numero e un carattere speciale, con una lunghezza minima di 8 caratteri.
     * 
     * @param password La password da validare.
     * @return true se la password è valida, false altrimenti.
     * 
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
    * 
    * Implementa l'azione associata al pulsante Sign-In. Se le credenziali inserite
    * non sono già presenti nel database permette all'utente di registrarsi
    *
    */
    
    @FXML
    private void actionSignin(ActionEvent event) {
        
    }
    
    /**
     * 
     * Permette il solo accesso locale alle operazioni sulla rubrica. Non viene 
     * effettuata la connessione con il database.
     * 
     * @param event
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
