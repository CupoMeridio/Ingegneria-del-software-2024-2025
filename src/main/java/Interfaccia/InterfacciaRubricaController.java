/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaccia;

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
public class InterfacciaRubricaController implements Initializable {

    @FXML
    private AnchorPane anchorUp;
    @FXML
    private HBox hboxButton;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
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
    private TextField txtSearch;
    @FXML
    private AnchorPane anchorBottom;
    @FXML
    private TableView<?> tblvRubrica;
    @FXML
    private TableColumn<?, ?> clmSur;
    @FXML
    private TableColumn<?, ?> clmName;
    @FXML
    private TableColumn<?, ?> clmEmail;
    @FXML
    private TableColumn<?, ?> clmTel;
    @FXML
    private TableColumn<?, ?> clmTag;
    @FXML
    private SplitPane interfacciaRubrica;
    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
