package it.unisa.diem.team02.contactsbook.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @brief Classe che modella una rubrica.
 * 
 * Contiene una lista di contatti.
 * 
 * @author team02
 */
public class Contactbook {
    private ObservableList<Contact> contacts;
    
    /**
     * @brief Crea un nuovo oggetto Contactbook inizializzando la lisra di contatti.
     * 
     * @post La lista di contatti è inizializzata.
     */
    public Contactbook(){
        contacts=FXCollections.observableArrayList();
    }

    /**
    * @brief Restituisce la lista dei contatti della rubrica.
    *
    * @pre La variabile d'istanza contacts deve essere stata inizializzata correttamente.
    * @post Si ottiene un riferimento alla lista di contatti della rubrica.
    *
    * @return Una lista osservabile contenente tutti i contatti della rubrica.
    */
    public ObservableList<Contact> getContacts() {
        return contacts;
    }
    
    /**
     * @brief Rimuove, se presente, il contatto dalla lista dei contatti.
     * 
     * @param c Il contatto da rimuovere dalla rubrica.
     * 
     * @pre La variabile d'istanza contacts deve essere stata inizializzata correttamente.
     * @post Il contatto non è presente nella lista.
     * 
     */
    public void delete(Contact c){
        contacts.remove(c);
    }
    
    
    /**
     * @brief Aggiunge il contatto passato come parametro alla lista dei contatti.
     * 
     * @param c Il contatto da aggiungere alla rubrica.
     * 
     * @pre La variabile d'istanza contacts deve essere stata inizializzata correttamente.
     * @post Il contatto viene aggiunto alla lista.
     * 
     */
    public void add(Contact c){
        contacts.add(c);
    }
    
    /**
     * @brief Il metodo verifica se un contatto è presente nella rubrica.
     * 
     * @param c Il contatto da cercare.
     * @return true se il contatto è presente in rubrica, false altrimenti.
     * 
     * @pre La variabile d'istanza contacts deve essere stata inizializzata correttamente.
     * @post Si ottiene un valore booleano che indica se il contatto specificato è presente o meno
     * in rubrica.
     */
    public boolean contains(Contact c){
        return contacts.contains(c);
    }
    
    /**
     * @brief Il metodo verifica se un contatto è presente nella rubrica, a meno dell'istanza
     * passata come secondo parametro. 
     * 
     * @param newC È il contatto da cercare.
     * @param oldC È l'istanza del contatto da ignorare nella ricerca.
     * @return true se la condizione è verificata, false altrimenti.
     */
    public boolean contains(Contact newC, Contact oldC){
        for (Contact c: contacts){
            if (c!=oldC && c.equals(newC))
                return true;
        }
        return false;
    }
        
    /**
     * Inizializza la lista osservabile con i contatti presenti nel database/file locale.
     */
    public void initializeList(){
        
    }
}
