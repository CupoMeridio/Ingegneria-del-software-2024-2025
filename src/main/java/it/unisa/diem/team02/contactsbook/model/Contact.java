/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.model;

import java.util.ArrayList;

/**
 * @lang it
 * Classe che estende la classe astratta {@code Person}. Definisce degli ulteriori attributi e metodi
 * per effettuare specifiche operazioni sugli oggetti {@code Contact}. Gli attributi
 * {@code number}, {@code email} e {@code tag} sono implementati tramite un
 * {@code ArrayList}
 * 
 * @lang en
 * Class that extends the abstract class {@code Person}. It defines additional attributes and methods
 * to perform specific operations on {@code Contact} objects. The attributes {@code number},
 * {@code email} e {@code tag} are implemented by an {@code ArrayList}
 * 
 * @author team02
 */
public class Contact extends Person{
    private ArrayList<String> number;
    private ArrayList<String> email;
    private ArrayList<Tag> tag;
    private static int contatore; // bisogna Salvare il numero di contatti da rivedere 
    private final int ID;
    
   public Contact(String name, String surname) {
        super(name, surname);
        number = new ArrayList<String>(3);
        email = new ArrayList<String>(3);
        this.tag= new ArrayList<Tag>(3);
        this.ID = contatore;
         contatore++;
        
    }
   public Contact(String name, String surname, int ID) {
        super(name, surname);
        number = new ArrayList<String>();
        email = new ArrayList<String>();
        this.tag= new ArrayList<Tag>();
        this.ID = ID;  
    }
    
   /*
   * @lang it
   * Restituisce tutti i numeri di telefono dell'oggetto corrente.
   * 
   * lang en
   * Returns all the phone number of the current object.
   */  
    public String getNumber(){
        if(number.size()==0) return null;
        if(number.size()==1) return number.get(0);
        if(number.size()==2) return number.get(0)+"\n"+number.get(1);
        return number.get(0)+"\n"+number.get(1)+"\n"+number.get(2);
    }
    /*
    * @lang it
    * Restituisce tutti gli indirizzi email dell'oggetto corrente.
    * 
    * @lang en
    * Returns all the email address of the current object.
    */
    public String getEmail() {
        if(email.size()==0) return null;
        if(email.size()==1) return email.get(0);
        if(email.size()==2) return email.get(0)+"\n"+email.get(1);
        return email.get(0)+"\n"+email.get(1)+"\n"+email.get(2);
    }
    
    /*
    * @lang it
    * Restituisce tutti i tag dell'oggetto corrente.
    *
    * @lang en
    * Returns all the tags of the current object.
    */
    public String getTag(){
        return "";
    }
    
    /*
    * @lang it
    * Inserisce un tag tra {Home, University, Job}, per un massimo di tre tag.
    * 
    * 
    * @lang en
    * Inserts a tag from {Home, University, Job}, with a maximum of three tags.
    *
    */
    public void addTag(Tag tag) {
        if (this.tag.size()>=3){
            //gestire
        }
        else this.tag.add(tag);
    }
    
    /*
    * @lang it
    * Inserisce un numero di telefono per un massimo di tre.
    * 
    * 
    * @lang en
    * Inserts a phone number with a maximum of three number.
    *
    */
    
    public void addNumber(String number) {
        if (this.number.size()>=3){
            //gestire
        }
        else this.number.add(number);
    }
    
     /*
    * @brief Non ancora implementata
    * @lang it
    * Inserisce un indirizzo email per un massimo di tre.
    * 
    * 
    * @lang en
    * Inserts a email adress with a maximum of three number.
    *
    */
    public void addEmail(String email){
        if (this.email.size()>=3){
            //gestire
        }
        else this.email.add(email);
    }
    
   /**
    * @lang it
    * Imposta la lista dei numeri di telefono.
    * 
    * @param {@code ArrayList<String>} number.
    * 
    * @lang en
    * Sets the list of phone numbers.
    * 
    * @param {@code ArrayList<String>} number.
    */
    public void setNumber(ArrayList<String> number) {
        this.number = number;
    }
    
    /**
    * @lang it
    * Imposta la lista degli indirizzi email.
    * 
    * @param {@code ArrayList<String>} email.
    * 
    * @lang en
    * Sets the list of email addresses.
    * 
    * @param {@code ArrayList<String>} email
    */
    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }
    
    /**
     * @lang it
     * Imposta la lista dei tag associati.
     * 
     * @param {@code ArrayList<String>} tag.
     * 
     * @lang en
     * Sets the list of associated tags.
     * 
     * @param {@code ArrayList<String>} tag.
     */
    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }
    
    /**
    * @lang it
    * Restituisce la lista dei tag associati.
    * 
    * @lang en
    * Returns the list of associated tags.
    */
     public ArrayList<Tag> getTagList() {
        return tag;
    }
     
    /**
    * @lang it
    * Restituisce la lista dei numeri di telefono.
    * 
    * @lang en
    * Returns the list of phone numbers.
    */ 
    public ArrayList<String> getNumberList(){
        return this.number ;
    }
    
    /**
    * @lang it
    * Restituisce la lista degli indirizzi email.
    * 
    * @lang en
    * Returns the list of email addresses.
    */
    public ArrayList<String> getEmailList() {
        return email;
    }
    
    /*
    * @lang it
    * Restitusce il campo id del contatto
    *
    * @lang en
    * Returns the id of the contact.
    *
    */
     public int getID() {
        return ID;
    }
    
   
    @Override
    public String getRole() {
        return this.getClass().toString();
    }
    
    
    /**
    * @lang it
    * Restituisce una rappresentazione dell'oggetto corrente sotto forma di stringa, 
    * includendo il ruolo, le informazioni della classe padre e i numeri di telefono ed email associati.
    * Utilizza un {@code StringBuffer} per concatenare le informazioni:
    * - Il ruolo dell'oggetto (ottenuto tramite {@code getRole()}).
    * - Le informazioni dell'oggetto della classe padre (ottenute tramite {@code super.toString()}).
    * - I numeri di telefono associati all'oggetto, se presenti.
    * - Gli indirizzi email associati all'oggetto, se presenti.
    * 
    * @lang en
    * Returns a string representation of the current object, including the role, 
    * parent class information, and associated phone numbers and emails.
    * It uses a {@code StringBuffer} to concatenate the information:
    * - The object's role (obtained through {@code getRole()}).
    * - The information from the parent class (obtained through {@code super.toString()}).
    * - The phone numbers associated with the object, if any.
    * - The email addresses associated with the object, if any.
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
    
    
    
  
