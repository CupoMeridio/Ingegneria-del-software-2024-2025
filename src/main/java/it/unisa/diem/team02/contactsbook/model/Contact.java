/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.model;

import java.util.ArrayList;

/**
 *
 * @author cupom
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
    
    public String getNumber(){
        if(number.size()==0) return null;
        if(number.size()==1) return number.get(0);
        if(number.size()==2) return number.get(0)+"\n"+number.get(1);
        return number.get(0)+"\n"+number.get(1)+"\n"+number.get(2);
    }

    public String getEmail() {
        if(email.size()==0) return null;
        if(email.size()==1) return email.get(0);
        if(email.size()==2) return email.get(0)+"\n"+email.get(1);
        return email.get(0)+"\n"+email.get(1)+"\n"+email.get(2);
    }
    
    public String getTag(){
        return "";
    }

    public void addTag(Tag tag) {
        if (this.tag.size()>=3){
            //gestire
        }
        else this.tag.add(tag);
    }
    
    public void addNumber(String number) {
        if (this.number.size()>=3){
            //gestire
        }
        else this.number.add(number);
    }
    
    public void addEmail(String email){
        if (this.email.size()>=3){
            //gestire
        }
        else this.email.add(email);
    }
    
     public void setNumber(ArrayList<String> number) {
        this.number = number;
    }

    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }

    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }
     public ArrayList<Tag> getTagList() {
        return tag;
    }
    public ArrayList<String> getNumberList(){
        return this.number ;
    }

    public ArrayList<String> getEmailList() {
        return email;
    }
     public int getID() {
        return ID;
    }
            
    @Override
    public String getRole() {
        return ""+this.getClass();
    }
    
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
    
    
    
  