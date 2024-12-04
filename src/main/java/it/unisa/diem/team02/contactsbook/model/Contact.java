/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.model;

/**
 *
 * @author cupom
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
        if(this.tag.length<3)
            this.tag[this.tag.length] = tag;
        //gestire l' elese
        
    }
    
    public void addNumber(String number) {
        if (this.number.length>=3){
            //gestire
        }
        if (this.number[0]==null) this.number[0]=number;
        else 
            if (this.number[1]==null) this.number[1]=number;
            else 
                if (this.number[2]==null) this.number[2]=number;
    }
    
    public void addEmail(String email){
        if (this.email.length>=3){
            //gestire
        }
              if (this.email[0]==null) this.email[0]=email;
        else 
            if (this.email[1]==null) this.email[1]=email;
            else 
                if (this.email[2]==null) this.email[2]=email;
    }
            
    @Override
    public String getRole() {
        return ""+this.getClass();
    }
    
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