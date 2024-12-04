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
    
    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        if(o==this) return true;
        if(!(o.getClass()==Person.class)) return false;
        
        Person p = (Person) o;
        
        if(p.name==null && this.name==null){
            if(this.surname==null || p.surname==null)
                return false;
            return this.surname.equals(p.surname);
        }
        else if(p.surname==null && this.surname==null) {
            if(this.name==null || p.name==null)
                return false;
            return p.name.equals(this.name);
        }

        return p.name.equals(this.name) && p.surname.equals(this.surname);
         
        
    
    }
    
    public abstract String getRole();
}