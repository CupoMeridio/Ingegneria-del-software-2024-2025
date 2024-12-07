/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.model;

import java.util.ArrayList;

/**
 * 
 * Classe che estende la classe astratta Person. Definisce degli ulteriori attributi e metodi
 * per effettuare specifiche operazioni sugli oggetti Contact. Gli attributi
 * number, email e tag sono implementati tramite un ArrayList
 * 
 * @author team02
 */
public class Contact extends Person{
    private ArrayList<String> number;
    private ArrayList<String> email;
    private ArrayList<Tag> tag;
    private static int contatore; // bisogna Salvare il numero di contatti, da rivedere 
    private final int ID;
    
    
/**
 * 
 * @brief Costruttore della classe Conatct
 * Inizializza un nuovo oggetto `Contact` con il nome e il cognome forniti, 
 * inizializzando anche le liste `number`, `email` e `tag` con una capacità iniziale 
 * di 3 elementi ciascuna. Assegna inoltre un ID univoco al contatto basato su un 
 * contatore statico incrementale.
 * 
 * @param name Nome del conatto
 * @param surname Cognome del contatto
 */    
    public Contact(String name, String surname) {
        super(name, surname);
        number = new ArrayList<String>(3);
        email = new ArrayList<String>(3);
        this.tag= new ArrayList<Tag>(3);
        this.ID = contatore;
         contatore++;
        
    }
    
  /**
  * lang it
  * @brief Costruttore della classe Contact con ID specificato.
  * 
  * Inizializza un nuovo oggetto `Contact` con il nome, il cognome e un ID specificato. 
  * Inoltre, inizializza le liste `number`, `email` e `tag` come vuote.
  * 
  * @param name Nome del contatto.
  * @param surname Cognome del contatto.
  * @param ID Identificatore univoco del contatto.
  * 
  * @note Questo costruttore consente di specificare manualmente l'ID del contatto, 
  *       rendendolo utile in scenari come il caricamento di dati da un database o da file.
  * 
  */
    public Contact(String name, String surname, int ID) {
        super(name, surname);
        number = new ArrayList<String>();
        email = new ArrayList<String>();
        this.tag= new ArrayList<Tag>();
        this.ID = ID;  
    }
    
 /**
 * 
 * @brief Restituisce i numeri di telefono del contatto in formato leggibile.
 * 
 * Questo metodo restituisce i numeri di telefono associati al contatto in base alla 
 * quantità presente nella lista `number`. Se ci sono:
 * - Nessun numero: restituisce `null`.
 * - Un numero: restituisce il numero.
 * - Due numeri: restituisce i due numeri separati da una nuova riga (`\n`).
 * - Tre o più numeri: restituisce i primi tre numeri separati da nuove righe.
 * 
 * @return Una stringa contenente i numeri di telefono formattati o `null` se 
 *         la lista è vuota.
 * 
 */
    public String getNumber(){
        if(number.size()==0) return null;
        if(number.size()==1) return number.get(0);
        if(number.size()==2) return number.get(0)+"\n"+number.get(1);
        return number.get(0)+"\n"+number.get(1)+"\n"+number.get(2);
    }
 /**
 *
 * @brief Restituisce gli indirizzi email del contatto in formato leggibile.
 * 
 * Questo metodo restituisce gli indirizzi email associati al contatto in base alla 
 * quantità presente nella lista `email`.
 * Se ci sono:
 * - Nessun indirizzo: restituisce `null`.
 * - Un indirizzo: restituisce l'indirizzo.
 * - Due indirizzi: restituisce i due indirizzi separati da una nuova riga (`\n`).
 * - Tre o più indirizzi: restituisce i primi tre indirizzi separati da nuove righe.
 * 
 * @return Una stringa contenente gli indirizzi email formattati o `null` se 
 *         la lista è vuota.
 * 
 * @note Il metodo restituisce al massimo tre indirizzi, anche se la lista `email` 
 *       contiene più di tre elementi.
 * 
 */
    public String getEmail() {
        if(email.size()==0) return null;
        if(email.size()==1) return email.get(0);
        if(email.size()==2) return email.get(0)+"\n"+email.get(1);
        return email.get(0)+"\n"+email.get(1)+"\n"+email.get(2);
    }
    
    /*
    * 
    * Restituisce tutti i tag dell'oggetto corrente.
    */
    public String getTag(){
        return "";
    }
    
    /*
    * 
    * Inserisce un tag tra {Home, University, Job}, per un massimo di tre tag.
    */
    public void addTag(Tag tag) {
        if (this.tag.size()>=3){
            //gestire
        }
        else this.tag.add(tag);
    }
    
    /*
    * 
    * Inserisce un numero di telefono per un massimo di tre.
    */
    
    public void addNumber(String number) {
        if (this.number.size()>=3){
            //gestire
        }
        else this.number.add(number);
    }
    
    /*
    * @brief Non ancora implementata
    * 
    * Inserisce un indirizzo email per un massimo di tre.
    */
    public void addEmail(String email){
        if (this.email.size()>=3){
            //gestire
        }
        else this.email.add(email);
    }
    
   /**
    * Imposta la lista dei numeri di telefono.
    * @param number
    */
    public void setNumber(ArrayList<String> number) {
        this.number = number;
    }
    
    /**
    * 
    * Imposta la lista degli indirizzi email.
    * @param email
    */
    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }
    
    /**
     * Imposta la lista dei tag associati.
     * @param tag
     */
    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }
    
    /**
    * Restituisce la lista dei tag associati.
    */
     public ArrayList<Tag> getTagList() {
        return tag;
    }
     
    /**
    * 
    * Restituisce la lista dei numeri di telefono.
    * 
    */ 
    public ArrayList<String> getNumberList(){
        return this.number ;
    }
    
    /**
    * 
    * Restituisce la lista degli indirizzi email.
    * 
    */
    public ArrayList<String> getEmailList() {
        return email;
    }
    
 /**
 * 
 * @brief Restituisce l'ID del contatto.
 * Questo metodo restituisce l'identificatore univoco (ID) associato al contatto.
 * 
 * @return L'ID del contatto come valore intero.
 * 
 * @return The contact ID as an integer value.
 */
     public int getID() {
        return ID;
    }
    
 /**
 * 
 * @brief Restituisce il ruolo della classe corrente.
 * Questo metodo sovrascrive il metodo `getRole` della classe base. 
 * Restituisce il nome completo della classe corrente come stringa, utilizzando 
 * il metodo `toString` dell'oggetto `Class`.
 * 
 * @return Una stringa che rappresenta il nome completo della classe corrente.
 * 
 */
    @Override
    public String getRole() {
        return this.getClass().toString();
    }
    
    
    /**
    * 
    * Restituisce una rappresentazione dell'oggetto corrente sotto forma di stringa, 
    * includendo il ruolo, le informazioni della classe padre e i numeri di telefono ed email associati.
    * Utilizza un StringBuffer per concatenare le informazioni:
    * - Il ruolo dell'oggetto (ottenuto tramite getRole()).
    * - Le informazioni dell'oggetto della classe padre (ottenute tramite super.toString()).
    * - I numeri di telefono associati all'oggetto, se presenti.
    * - Gli indirizzi email associati all'oggetto, se presenti.
    * 
    */

    @Override
    public String toString(){
        StringBuffer sb=new StringBuffer(this.getRole()+" "+super.toString());
        for (int i=0; i<this.number.size();i++)
            sb=sb.append(" Phone number: ").append(number.get(i));
        for (int i=0; i<this.email.size();i++)
            sb=sb.append(" Email: ").append(email.get(i));
        return sb.toString();
    }
}
    
    
    
  
