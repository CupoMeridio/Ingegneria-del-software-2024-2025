
package it.unisa.diem.team02.contactsbook.model;

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
    
    public abstract String getRole();
}