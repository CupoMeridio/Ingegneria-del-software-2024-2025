module it.unisa.diem.team02 {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens it.unisa.diem.team02.contactsbook.ui.controllers to javafx.fxml;
    opens it.unisa.diem.team02 to javafx.fxml;
    
    exports it.unisa.diem.team02;
    exports it.unisa.diem.team02.contactsbook.ui.controllers;
    
    
}
