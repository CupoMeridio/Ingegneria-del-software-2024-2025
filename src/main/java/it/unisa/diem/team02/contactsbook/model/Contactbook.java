package it.unisa.diem.team02.contactsbook.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.cell.PropertyValueFactory;

public class Contactbook {
    private ObservableList<Contact> contacts;
    private FilteredList<Contact> flContacts;
    
    public Contactbook(){
        contacts=FXCollections.observableArrayList();
        flContacts=new FilteredList<>(contacts, c->true);
    }

    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    public FilteredList<Contact> getFlContacts() {
        return flContacts;
    }
    
    public void createList(){
        contacts = FXCollections.observableArrayList();
        flContacts = new FilteredList(contacts, c->true);
    }
    
    public void delete(Contact c){
        contacts.remove(c);
    }
    
    public void add(Contact c){
        contacts.add(c);
    }
    
    public boolean contains(Contact c){
        return contacts.contains(c);
    }
    
    public boolean contains(Contact newC, Contact oldC){
        for (Contact c: contacts){
            if (c!=oldC && c.equals(newC))
                return true;
        }
        return false;
    }
    
    public boolean remove(Contact c){
        return contacts.remove(c);
    }
        
    /**
     * Inizializza la lista osservabile con i contatti presenti nel database/file locale.
     */
    public void initializeList(){
        
    }
}
