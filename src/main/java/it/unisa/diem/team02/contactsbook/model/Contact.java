/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.model;

/**
 * @lang it
 * Classe che estende la classe astratta {@code Person}. Definisce degli ulteriori attributi e metodi
 * per effettuare specifiche operazioni sugli oggetti {@code Contact}.
 * 
 * @lang en
 * Class that extends the abstract class {@code Person}. It defines additional attributes and methods
 * to perform specific operations on {@code Contact} objects.
 * 
 */
public class Contact extends Person{
    private String[] number;
    private String[] email;
    private Tag[] tag;
    
    public Contact(String name, String surname) {
        super(name, surname);
        number = new String[3];
        email = new String[3];
        this.tag= new Tag[3];
    }
    
   /*
   * @lang it
   * 
   * 
   * 
   *
   */  
    public String getNumber(){
        if(number[1]==null) return number[0];
        if (number[2]==null) return number[0]+"\n"+number[1];
        return number[0]+"\n"+number[1]+"\n"+number[2];
    }

    public String getEmail() {
        if(email[1]==null) return email[0];
        if (email[2]==null) return email[0]+"\n"+email[1];
        return email[0]+"\n"+email[1]+"\n"+email[2];
    }

    public void addTag(Tag tag) {
        /* Vittorio: eh? lenght è sempre 3...
        if(this.tag.length<3)
            this.tag[this.tag.length] = tag;
        //gestire l' else
        */
        
        //Questa implementazione mancava
        if (this.tag[0]==null) this.tag[0]=tag;
        else 
            if (this.tag[1]==null) this.tag[1]=tag;
            else 
                if (this.tag[2]==null) this.tag[2]=tag;
    }
    
    public void addNumber(String number) {
        /*
        Vittorio: ma wtf? lenght è sempre 3
        if (this.number.length>=3){
            //gestire
       
        }*/
        
        if (this.number[0]==null) this.number[0]=number;
        else 
            if (this.number[1]==null) this.number[1]=number;
            else 
                if (this.number[2]==null) this.number[2]=number;
    }
    
    public void addEmail(String email){
        /*
        Vittorio: stesso problema...
        if (this.email.length>=3){
            //gestire
        }
        */
              if (this.email[0]==null) this.email[0]=email;
        else 
            if (this.email[1]==null) this.email[1]=email;
            else 
                if (this.email[2]==null) this.email[2]=email;
    }
    
    
    public void setNumber(String[] number){
        this.number=number;
    }
    
    public void setEmail(String[] email){
        this.email=email;
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
        for (int i=0; i<this.number.length;i++)
            sb=sb.append(" Phone number: ").append(number[i]);
        for (int i=0; i<this.email.length;i++)
            sb=sb.append(" Email: ").append(email[i]);
        return sb.toString();
    }
}
    
    
    
  