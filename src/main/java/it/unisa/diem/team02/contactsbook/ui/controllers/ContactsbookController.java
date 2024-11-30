/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author anuar
 */
public class ContactsbookController implements Initializable {

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
    private TableColumn<?, ?> clmName;
    @FXML
    private TableColumn<?, ?> clmEmail;
    @FXML
    private TableColumn<?, ?> clmTel;
    @FXML
    private TableColumn<?, ?> clmTag;
    @FXML
    private SplitPane Rubrica;
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
    private TableView<?> tblvRubrica;
    @FXML
    private TableColumn<?, ?> clmSur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}