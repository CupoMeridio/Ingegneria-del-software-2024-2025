package it.unisa.diem.team02.contactsbook.model;

import java.util.Objects;

public abstract class Person {
    private String name;
    private String surname;

    public Person(String name, String surname){
        this.name=name;
        this.surname=surname;
    }
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
    
    @Override
    public String toString(){
        return "Name: "+name+" Surname: "+surname+"\n";
    }
    
    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        if(o==this) return true;
        if (!(o instanceof Person)) return false;
        
       // Confronto tra Person (classe padre) e Contact
        Contact c = (Contact) o;
        
        if(c.getName()==null && this.getName()==null)
            return c.getSurname().equals(this.getSurname());
        else if(c.getSurname()==null && this.getSurname()==null)
            return c.getName().equals(this.getName());
        else if((c.getSurname()==null && this.getName()==null) || (c.getName()==null && this.getSurname()==null))
            return false;
        
        return c.getName().equals(this.getName()) && c.getSurname().equals(this.getSurname());
        
    }
    
    public abstract String getRole();
}