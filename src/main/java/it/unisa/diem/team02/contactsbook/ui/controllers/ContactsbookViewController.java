/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.ui.controllers;

import it.unisa.diem.team02.contactsbook.model.Contact;
import it.unisa.diem.team02.contactsbook.model.Tag;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
 * FXML Controller class
 *
 * @author anuar
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
    private TableColumn<Contact, String> clmTel;
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createList();
        initializeList();
        txtMofidyInitialize();
        txtDeleteInitialize();
    }    
    
    public void createList(){
        contacts = FXCollections.observableArrayList();
        clmName.setCellValueFactory(new PropertyValueFactory("name"));
        clmSur.setCellValueFactory(new PropertyValueFactory("surname"));
        clmTel.setCellValueFactory(new PropertyValueFactory("number"));
        tblvRubrica.setItems(contacts);
    }
    
    public void initializeList(){
        
    }
    
    public void actionAdd(ActionEvent event) throws IOException{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddView.fxml"));
              if(loader == null) {
                System.out.println("File FXML non trovato");
                return;
              }
              
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
    
    public void actionModify(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModifyView.fxml"));
              if(loader == null) {
                System.out.println("File FXML non trovato");
                return;
              }
              
              Parent root = loader.load();
              Scene scene=new Scene(root);
              
              ModifyViewController modifyC=loader.getController();
              modifyC.setObservableList(contacts);
              
    
              //gestire eccezione
              Stage newStage = new Stage();
              newStage.setScene(scene);
              
              newStage.initModality(Modality.WINDOW_MODAL);
              newStage.initOwner(btnModify.getScene().getWindow());
              newStage.show();
    }
    
    public void txtMofidyInitialize(){
        btnModify.setDisable(true);

        tblvRubrica.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                btnModify.setDisable(newValue == null);
            }
        });
    }
    
    public void txtDeleteInitialize(){
        btnDelete.setDisable(true);

        tblvRubrica.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                btnDelete.setDisable(newValue == null);
            }
        });
    }
}