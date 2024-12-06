
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
import it.unisa.diem.team02.contactsbook.model.UserInteractionDataInterface;

/**
 * @lang it
 * Controller per la gestione della schermata di aggiunta di un nuovo contatto.
 * 
 * @lang en
 * Controller for handling the add of a new contact.
 * 
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
    
    private ObservableList contacts;

    /**
     * @lang it
     * Metodo di inizializzazione del controller.
     * Gestisce la disattivazione dei vari bottoni.
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
        btnAdd.disableProperty().bind(Bindings.and(txtName.textProperty().isEmpty(), txtSur.textProperty().isEmpty()));
        txtNumber2.disableProperty().bind(Bindings.isEmpty(txtNumber1.textProperty()));
        txtNumber3.disableProperty().bind(Bindings.isEmpty(txtNumber2.textProperty()));
        txtEmail2.disableProperty().bind(Bindings.isEmpty(txtEmail1.textProperty()));
        txtEmail3.disableProperty().bind(Bindings.isEmpty(txtEmail2.textProperty()));
    }
    
    /**
     * @lang it
     * Setta il campo contacts con la lista passata come parametro.
     * 
     * @param ObservableList<Contact> contacts è la lista osservabile che rappresenta la rubrica.
     * 
     * @lang en
     *
     * Sets the contacts field with the list passed as a parameter.
     * 
     * @param ObservableList<Contact> contacts is the observable list representing the contactbook.
     */
    public void setObservableList(ObservableList<Contact> contacts){
        this.contacts=contacts;
    }
    
    /**
     * @lang it
     * Implementa l'azione associcata al tasto add: viene creato un nuovo contatto con le informazioni
     * presenti nei campi di testo e aggiunto alla lista. Gestisce il caso in cui sia già presente 
     * un contatto con lo stesso nome e lo stesso cognome.
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the add button: a new contact is created with the 
     * informations in the text fields and added to the list. Handles the case where a contact with 
     * the same first name and last name already exists.
     * 
     * @param ActionEvent event
     * 
     * @throws IOException
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
     * @lang it
     * Implementa l'azione associcata al tasto cancel: si torna alla visualizzazione della rubrica e
     * non viene effettuata alcuna modifica
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Implements the action associated with the cancel button: returns to the Contactbook view, and 
     * no changes are made.
     * 
     * @param ActionEvent event
     */
    public void actionCancel(ActionEvent event){
        Stage stage=(Stage) btnCanc.getScene().getWindow();
        stage.close();
    }
    
        
}
