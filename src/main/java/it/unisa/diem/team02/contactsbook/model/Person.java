package it.unisa.diem.team02.contactsbook.model;

import java.util.Objects;

/**
 * 
 * Classe astratta che definisce gli attributi e i metodi di una Persona generica.
 * 
 * @author team02
 */
public abstract class Person {
    private String name;
    private String surname;
    
    
    public Person(String name, String surname){
        this.name=name;
        this.surname=surname;
    }
    
    /**
     * 
     * Restituisce il nome.
     */
    public String getName() {
        return name;
    }
    
     /**
     * 
     * Restituisce il cognome.
     * 
     */
    public String getSurname() {
        return surname;
    }

    
    
    /**
     * 
     * Imposta il nome.
     * 
     * @param name Il nuovo nome da impostare.
     * 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * Imposta il cognome.
     * 
     * @param surname Il nuovo cognome da impostare.
     * 
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
    /*
    * 
    * Restituisce le informazioni di un oggetto Person nel seguente formato:
    * Name: <nome> Surname: <cognome>
    * 
    */
    @Override
    public String toString(){
        return "Name: "+name+" Surname: "+surname+"\n";
    }
    
    /**
    * 
    * @brief Confronta l'oggetto Person corrente con un altro oggetto per verificarne l'uguaglianza.
    * L'uguaglianza è determinata confrontando il nome e il cognome. 
    * Se uno o entrambi i campi sono null, vengono gestiti casi specifici:
    * - Se entrambi i nomi sono null, confronta solo i cognomi.
    * - Se entrambi i cognomi sono null, confronta solo i nomi.
    * - Se uno dei due oggetti ha nome null e l'altro no, oppure se uno
    *   dei due oggetti ha cognome null e l'altro no, restituisce false.
    * 
    * @param o L'oggetto da confrontare con l'istanza corrente.
    * @return true se gli oggetti sono considerati uguali, false altrimenti.
    * 
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
    * 
    * Restituisce il ruolo di un oggetto Person che è rappresentato
    * dal nome della classe. Viene implementata dalle classi
    * Contact e User che estendono Person.
    */
    public abstract String getRole();
}
