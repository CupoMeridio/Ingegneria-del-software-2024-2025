/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.ui.controllers;

import it.unisa.diem.team02.contactsbook.model.Contact;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private Contact oldContact;
    private Contact newContact=new Contact("", "");

    /**
     * @lang it
     * Metodo di inizializzazione del controller.
     * Gestisce la disattivazione dei vari bottoni.
     * 
     * @param {@code URL} url utilizzato per risolvere il percorso del file FXML.
     * @param {@code ResourceBundle} rb contenente dati di localizzazione.
     * 
     * @lang en
     * Initializes the controller.
     * Handles the deactivation of various buttons.
     * 
     * @param {@code URL} url used to resolve the FXML file path.
     * @param  {@code ResourceBundle} rb containing localization data.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModify.disableProperty().bind(Bindings.and(txtName.textProperty().isEmpty(), txtSur.textProperty().isEmpty()));
        txtNumber2.disableProperty().bind(Bindings.isEmpty(txtNumber1.textProperty()));
        txtNumber3.disableProperty().bind(Bindings.isEmpty(txtNumber2.textProperty()));
        txtEmail2.disableProperty().bind(Bindings.isEmpty(txtEmail1.textProperty()));
        txtEmail3.disableProperty().bind(Bindings.isEmpty(txtEmail2.textProperty()));
    }    

    /**
     * @lang it
     * Implementa l'azione associcata al tasto cancel: si torna alla visualizzazione della rubrica e
     * non viene effettuata alcuna modifica
     * 
     * @param {@code ActionEvent} event
     * 
     * @lang en
     * Implements the action associated with the cancel button: returns to the Contactbook view, and 
     * no changes are made.
     * 
     * @param {@code ActionEvent} event
     */
    @FXML
    private void actionCancel(ActionEvent event) {
        Stage stage=(Stage) btnCanc.getScene().getWindow();
        stage.close();
    }

    /**
     * @lang it
     * Setta il campo contacts con la lista passata come parametro.
     * 
     * @param {@code ObservableList<Contact>} contacts è la lista osservabile che rappresenta la rubrica.
     * 
     * @lang en
     *
     * Sets the contacts field with the list passed as a parameter.
     * 
     * @param {@code ObservableList<Contact>} contacts is the observable list representing the contactbook.
     */
    public void setObservableList(ObservableList<Contact> contacts){
        this.contacts=contacts;
    }
    
    /**
     * @lang it
     * Setta il campo c con il contatto passato come parametro.
     * 
     * @param {@code Contact} contact è il contatto da modificare.
     * 
     * @lang en
     *
     * Sets the c field with the contact passed as a parameter.
     * 
     * @param {@code Contact} contact is the contact to be edited. 
     */
    public void setContact(Contact contact){
        oldContact=contact;
        if (oldContact.getName()!=null) txtName.setText(oldContact.getName());
        if (oldContact.getSurname()!=null) txtSur.setText(oldContact.getSurname());
        String number=oldContact.getNumber();
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
        
        String email=oldContact.getEmail();
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

    /**
     * @lang it
     * Implementa l'azione associcata al tasto modify: viene creato un nuovo contatto con le informazioni
     * presenti nei campi di testo e aggiunto alla lista. Il contatto precedente viene rimosso. 
     * 
     * @param {@code ActionEvent} event
     * 
     * @lang en
     * Implements the action associated with the add button: a new contact is created with the 
     * informations in the text fields and added to the list. The previous contact is removed.
     * 
     * @param {@code ActionEvent} event
     * @throws IOExcpetion
     */
    @FXML
    private void actionModify(ActionEvent event) throws IOException {
        newContact.setName(txtName.getText());
        newContact.setSurname(txtSur.getText());
        ArrayList<String> numbers=new ArrayList<>(3);
        ArrayList<String> emails=new ArrayList<>(3);
        if (!txtNumber1.getText().isEmpty())
            numbers.add(txtNumber1.getText());
        if (!txtNumber2.getText().isEmpty())
            numbers.add(txtNumber2.getText());
        if (!txtNumber3.getText().isEmpty())
            numbers.add(txtNumber3.getText());

        if (!txtEmail1.getText().isEmpty())
            emails.add(txtEmail1.getText());
        if (!txtEmail2.getText().isEmpty())
            emails.add(txtEmail2.getText());
        if (!txtEmail3.getText().isEmpty())
            emails.add(txtEmail3.getText());

        newContact.setNumber(numbers);
        newContact.setEmail(emails);
            if(!contacts.contains(newContact)){
                contacts.remove(oldContact);
                contacts.add(newContact);
                Stage stage=(Stage) btnModify.getScene().getWindow();
                stage.close();
            } else{ 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DuplicateContactView.fxml"));
                Parent root = loader.load();
                Scene scene=new Scene(root);
                Stage newStage = new Stage();
                newStage.setScene(scene);
                
                DuplicateContactViewController duplicateC=loader.getController();
                Contact c1=new Contact("Prova", "Prova");
                duplicateC.set(c1);
                
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.initOwner(btnModify.getScene().getWindow());
                newStage.showAndWait();
                  
                if (c1.getName().equals("")){
                    contacts.remove(oldContact);
                    contacts.add(newContact);
                    Stage stage=(Stage) btnModify.getScene().getWindow();
                    stage.close();
                } else {
                    Stage stage=(Stage) btnModify.getScene().getWindow();
                    stage.show();
                }  
            }
    }
    
}
