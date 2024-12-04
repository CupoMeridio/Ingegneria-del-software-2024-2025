
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
 * FXML Controller class
 *
 * @author anuar
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAdd.disableProperty().bind(Bindings.and(txtName.textProperty().isEmpty(), txtSur.textProperty().isEmpty()));
        txtNumber2.disableProperty().bind(Bindings.isEmpty(txtNumber1.textProperty()));
        txtNumber3.disableProperty().bind(Bindings.isEmpty(txtNumber2.textProperty()));
        txtEmail2.disableProperty().bind(Bindings.isEmpty(txtEmail1.textProperty()));
        txtEmail3.disableProperty().bind(Bindings.isEmpty(txtEmail2.textProperty()));
    }
    
    public void setObservableList(ObservableList<Contact> contacts){
        this.contacts=contacts;
    }
    
    public void actionAdd(ActionEvent event){
        Contact c=new Contact(txtName.getText(), txtSur.getText());
        if (!txtNumber1.getText().isEmpty()) c.addNumber(txtNumber1.getText());
        if (!txtNumber2.getText().isEmpty()) c.addNumber(txtNumber2.getText());
        if (!txtNumber3.getText().isEmpty()) c.addNumber(txtNumber3.getText());
        if(!txtEmail1.getText().isEmpty()) c.addEmail(txtEmail1.getText());
        if(!txtEmail2.getText().isEmpty()) c.addEmail(txtEmail2.getText());
        if(!txtEmail3.getText().isEmpty()) c.addEmail(txtEmail3.getText());
        
        if (!contacts.contains(c))
            contacts.add(c);
        
        Stage stage=(Stage) btnAdd.getScene().getWindow();
        stage.close();
    }
    
    public void actionCancel(ActionEvent event){
        Stage stage=(Stage) btnCanc.getScene().getWindow();
        stage.close();
    }
}