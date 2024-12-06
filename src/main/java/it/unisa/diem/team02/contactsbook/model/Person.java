package it.unisa.diem.team02.contactsbook.model;

import java.util.Objects;

/**
 * @lang it
 * Classe astratta che definisce gli attributi e i metodi di una Persona generica.
 * 
 * @lang en
 * Abstract class that defines the attributes and methods of a generic Person.
 * 
 */
public abstract class Person {
    private String name;
    private String surname;
    
    
    public Person(String name, String surname){
        this.name=name;
        this.surname=surname;
    }
    
    /**
     * @lang it
     * Restituisce il nome.
     * 
     * @lang en
     * Returns the name.
     */
    public String getName() {
        return name;
    }
    
     /**
     * @lang it
     * Restituisce il cognome.
     * 
     * @lang en
     * Returns the surname.
     * 
     */
    public String getSurname() {
        return surname;
    }

    
    
    /**
     * @lang it
     * Imposta il nome.
     * 
     * @param name Il nuovo nome da impostare.
     * 
     * @lang en
     * Sets the name.
     * 
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @lang it
     * Imposta il cognome.
     * 
     * @param surname Il nuovo cognome da impostare.
     * 
     * @lang en
     * Sets the surname.
     * 
     * @param surname The new surname to set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
    /*
    * @lang it
    * Restituisce le informazioni di un oggetto {@code Person} nel seguente formato:
    * {@code Name: <nome> Surname: <cognome>}
    *
    * @lang en
    * Returns the information of a {@code Person} object in the format:
    * {@code Name: <name> Surname: <surname>}
    *
    *
    */
    @Override
    public String toString(){
        return "Name: "+name+" Surname: "+surname+"\n";
    }
    
    /**
    * @lang it
    * Confronta l'oggetto Person corrente con un altro oggetto per verificarne l'uguaglianza.
    * L'uguaglianza è determinata confrontando il nome e il cognome. 
    * Se uno o entrambi i campi sono {@code null}, vengono gestiti casi specifici:
    * - Se entrambi i nomi sono {@code null}, confronta solo i cognomi.
    * - Se entrambi i cognomi sono {@code null}, confronta solo i nomi.
    * - Se uno dei due oggetti ha nome {@code null} e l'altro no, oppure se uno
    *   dei due oggetti ha cognome {@code null} e l'altro no, restituisce {@code false}.
    * 
    * @param o L'oggetto da confrontare con l'istanza corrente.
    * @return {@code true} se gli oggetti sono considerati uguali, {@code false} altrimenti.
    * 
    * @lang en
    * Compares the current object with another object to check for equality.
    * Equality is determined by comparing the name and surname with those of the current object. 
    * If one or both fields are {@code null}, specific cases are handled:
    * - If both names are {@code null}, only surnames are compared.
    * - If both surnames are {@code null}, only names are compared.
    * - If one of the two objects has a {@code null} name while the other does not, or if one
    *   of the two objects has a {@code null} surname while the other does not, 
    *   it returns {@code false}.
    * 
    * @param o The object to compare with the current instance.
    * @return {@code true} if the objects are considered equal, {@code false} otherwise.
    */

    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        if(o==this) return true;
        if (!(o instanceof Person)) return false;
        
        Person c = (Person) o;
        
        if(c.getName()==null && this.getName()==null)
            return c.getSurname().equals(this.getSurname());
        else if(c.getSurname()==null && this.getSurname()==null)
            return c.getName().equals(this.getName());
        else if((c.getSurname()==null && this.getName()==null) || (c.getName()==null && this.getSurname()==null))
            return false;
        
        return c.getName().equals(this.getName()) && c.getSurname().equals(this.getSurname());
        
    }
    
    /*  
    * @lang it
    * Restituisce il ruolo di un oggetto {@code Person} che è rappresentato
    * dal nome della classe. Viene implementata dalle classi
    * {@code Contact} e {@code User} che estendono {@code Person}.
    * 
    * @lang en
    * Returns the role of a generic {@code Person}. represented by the 
    * object's class name. It is implemented by the classes
    * {@code Contact} and {@code User} that extend {@code Person}.
    * 
    */
    public abstract String getRole();
}
