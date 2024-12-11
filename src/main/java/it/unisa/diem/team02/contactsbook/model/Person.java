
package it.unisa.diem.team02.contactsbook.model;

/**
 * Classe astratta che definisce gli attributi e i metodi di una Persona generica.
 * @author team02
 */
public abstract class Person {
    private String name;
    private String surname;
    
/**
 * @brief Costruttore per creare un oggetto `Person` con nome e cognome.
 * 
 * Questo costruttore inizializza un oggetto `Person` con i valori di nome e cognome forniti.
 * 
 * @param name Il nome della persona.
 * @param surname Il cognome della persona.
 * 
 * @details 
 * - Il costruttore accetta due parametri, `name` e `surname`, che vengono utilizzati per inizializzare le rispettive variabili d'istanza.
 */
    public Person(String name, String surname){
        this.name=name;
        this.surname=surname;
    }
    
/**
 * @brief Restituisce il nome della persona.
 * 
 * Questo metodo restituisce il nome associato all'oggetto `Person`.
 * 
 * @return Il nome della persona come stringa.
 * 
 * @details 
 * - Il metodo restituisce la variabile d'istanza `name`, che contiene il nome della persona.
 */
    public String getName() {
        return name;
    }
    
/**
 * @brief Restituisce il cognome della persona.
 * 
 * Questo metodo restituisce il cognome associato all'oggetto `Person`.
 * 
 * @return Il cognome della persona come stringa.
 * 
 * @details 
 * - Il metodo restituisce la variabile d'istanza `surname`, che contiene il cognome della persona.
 */
    public String getSurname() {
        return surname;
    }

    
    
/**
 * @brief Imposta il nome della persona.
 * 
 * Questo metodo consente di assegnare un nuovo nome all'oggetto `Person`.
 * 
 * @param name Il nuovo nome da associare alla persona.
 * 
 * @details 
 * - Il parametro `name` viene utilizzato per aggiornare la variabile d'istanza `name`.
 * - Il nuovo valore di `name` sostituirà quello precedente.
 */
    public void setName(String name) {
        this.name = name;
    }
    
/**
 * @brief Imposta il cognome della persona.
 * 
 * Questo metodo consente di assegnare un nuovo cognome all'oggetto `Person`.
 * 
 * @param surname Il nuovo cognome da associare alla persona.
 * 
 * @details 
 * - Il parametro `surname` viene utilizzato per aggiornare la variabile d'istanza `surname`.
 * - Il nuovo valore di `surname` sostituirà quello precedente.
 */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
/**
 * @brief Restituisce una rappresentazione testuale della persona.
 * 
 * Questo metodo sovrascrive il metodo `toString` della classe `Object` per fornire una descrizione testuale
 * contenente il nome e il cognome della persona.
 * 
 * @return Una stringa che rappresenta la persona, includendo il nome e il cognome.
 * 
 * @details 
 * - La stringa restituita è nel formato: `"Name: nome Surname: cognome"`.
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
