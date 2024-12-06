/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.ui.controllers;

import it.unisa.diem.team02.contactsbook.model.Contact;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @lang it
 * Controller per la gestione della schermata di modifica di un contatto già esistente.
 * 
 * @lang en
 * Controller for handling the edit of an existing contact.
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
    private Contact c;

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
        btnModify.disableProperty().bind(Bindings.and(txtName.textProperty().isEmpty(), txtSur.textProperty().isEmpty()));
    }    

    /**
     * @lang it
     * Specifica l'azione associcata al tasto cancel: si torna alla visualizzazione della rubrica e
     * non viene effettuata alcuna modifica
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Specifies the action associated with the cancel button: returns to the Contactbook view, and 
     * no changes are made.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionCancel(ActionEvent event) {
        Stage stage=(Stage) btnCanc.getScene().getWindow();
        stage.close();
    }

//    public void setObservableList(ObservableList<Contact> contacts){
//        this.contacts=contacts;
//    }
    
    /**
     * @lang it
     * Setta il campo c con il contatto passato come parametro.
     * 
     * @param Conatct contact è il contatto da modificare.
     * 
     * @lang en
     *
     * Sets the c field with the contact passed as a parameter.
     * 
     * @param Contact contact is the contact to be edited. 
     */
    public void setContact(Contact contact){
        c=contact;
        if (c.getName()!=null) txtName.setText(c.getName());
        if (c.getSurname()!=null) txtSur.setText(c.getSurname());
        String number=c.getNumber();
        if (number!=null){
            String[] numbers=number.split("\n");
            txtNumber1.setText(numbers[0]);
            if (numbers[1]!=null){
                txtNumber2.setText(numbers[1]);
                if (numbers[2]!=null){
                    txtNumber3.setText(numbers[3]);
                }
            }
        }
        
        String email=c.getEmail();
        if (email!=null){
            String[] emails=email.split("\n");
            txtEmail1.setText(emails[0]);
            if (emails[1]!=null){
                txtEmail2.setText(emails[1]);
                if (emails[2]!=null){
                    txtEmail3.setText(emails[3]);
                }
            }
        }
        
        
    }
    //Vittorio: Qualcosa qui non funziona. Devo capire bene come funziona
    
    /**
     * @lang it
     * Specifica l'azione associcata al tasto modify: viene creato un nuovo contatto con le informazioni
     * presenti nei campi di testo e aggiunto alla lista. Il contatto precedente viene rimosso. 
     * 
     * @param ActionEvent event
     * 
     * @lang en
     * Specifies the action associated with the add button: a new contact is created with the 
     * informations in the text fields and added to the list. The previous contact is removed.
     * 
     * @param ActionEvent event
     */
    @FXML
    private void actionModify(ActionEvent event) {
        c.setName(txtName.getText());
        c.setSurname(txtSur.getText());
        String [] numbers=new String[3];
        String [] emails=new String [3];
        if (txtNumber1.getText()!=null) numbers[0]=txtNumber1.getText();
        if (txtNumber2.getText()!=null) numbers[1]=txtNumber2.getText();
        if (txtNumber3.getText()!=null) numbers[2]=txtNumber3.getText();

        if(txtEmail1.getText()!=null) emails[0]=txtEmail1.getText();
        if(txtEmail2.getText()!=null) emails[1]=txtEmail2.getText();
        if(txtEmail3.getText()!=null) emails[2]=txtEmail3.getText();

        
        if (contacts.contains(c))
            ;//contatto duplicato        
        
        
        
        Stage stage=(Stage) btnModify.getScene().getWindow();
        stage.close();
    }
    
}
