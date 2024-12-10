
package it.unisa.diem.team02.contactsbook.model;

/**
 * Classe astratta che definisce gli attributi e i metodi di una Persona generica.
 * @author team02
 */
public abstract class Person {
    private String name;
    private String surname;
    
/**
 * @brief Costruttore per inizializzare un oggetto Person con nome e cognome.
 *
 * @pre I parametri `name` e `surname` devono essere non null e non vuoti.
 * @post L'oggetto Person viene creato con i valori di `name` e `surname` forniti.
 * @invariant La variabile `name` deve contenere il nome della persona e `surname`
 *            deve contenere il cognome, entrambi devono essere coerenti con i dati forniti.
 *
 * @param name Il nome della persona.
 * @param surname Il cognome della persona.
 */
    public Person(String name, String surname){
        this.name=name;
        this.surname=surname;
    }
    
/**
 * @brief Restituisce il nome della persona.
 *
 * @pre La variabile d'istanza `name` deve essere stata inizializzata correttamente.
 * @post La stringa restituita rappresenta il nome della persona e non è modificata dal metodo.
 * @invariant La variabile `name` deve sempre contenere un valore valido che rappresenta
 *            il nome della persona.
 *
 * @return Una stringa contenente il nome della persona.
 */
    public String getName() {
        return name;
    }
    
/**
 * @brief Restituisce il cognome della persona.
 *
 * @pre La variabile d'istanza `surname` deve essere stata inizializzata correttamente.
 * @post La stringa restituita rappresenta il cognome della persona e non è modificata dal metodo.
 * @invariant La variabile `surname` deve sempre contenere un valore valido che rappresenta
 *            il cognome della persona.
 *
 * @return Una stringa contenente il cognome della persona.
 */
    public String getSurname() {
        return surname;
    }

    
    
/**
 * @brief Imposta il nome della persona.
 *
 * @pre Il parametro `name` non deve essere null e deve rappresentare un nome valido.
 * @post La variabile d'istanza `name` viene aggiornata con il valore del parametro `name`.
 * @invariant La variabile `name` deve sempre contenere un valore valido che rappresenta
 *            il nome della persona.
 *
 * @param name Il nuovo nome della persona.
 */
    public void setName(String name) {
        this.name = name;
    }
    
    
/**
 * @brief Imposta il cognome della persona.
 *
 * @pre Il parametro `surname` non deve essere null e deve rappresentare un cognome valido.
 * @post La variabile d'istanza `surname` viene aggiornata con il valore del parametro `surname`.
 * @invariant La variabile `surname` deve sempre contenere un valore valido che rappresenta
 *            il cognome della persona.
 *
 * @param surname Il nuovo cognome della persona.
 */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
/**
 * @brief Restituisce una rappresentazione testuale dell'oggetto.
 *
 * @pre La variabile d'istanza `name` e `surname` devono essere state inizializzate correttamente.
 * @post Viene restituita una stringa formattata che contiene il nome e il cognome della persona.
 * @invariant Le variabili `name` e `surname` devono rimanere consistenti e non devono essere
 *            modificate da questo metodo.
 *
 * @return Una stringa che rappresenta l'oggetto, contenente il nome e il cognome della persona.
 */
    @Override
    public String toString(){
        return "Name: "+name+" Surname: "+surname+"\n";
    }
    
/**
 * @brief Confronta questo oggetto con un altro per verificarne l'uguaglianza.
 *
 * Questo metodo confronta l'oggetto corrente con un altro oggetto di tipo `Person` per determinare
 * se hanno lo stesso nome e cognome. La comparazione tiene conto del fatto che uno dei due potrebbe
 * avere `null` come nome o cognome, trattandolo correttamente.
 *
 * @pre L'oggetto passato come parametro deve essere non null.
 * @post Il metodo restituisce `true` se l'oggetto corrente è uguale all'oggetto passato, 
 *       altrimenti restituisce `false`. La comparazione è basata sui campi `name` e `surname`.
 * @invariant L'oggetto `name` e `surname` devono contenere valori consistenti (non null quando necessario).
 *
 * @param o L'oggetto da confrontare con l'oggetto corrente.
 * @return `true` se l'oggetto corrente è uguale all'oggetto passato, `false` altrimenti.
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
    
/**
 * @brief Restituisce il ruolo dell'oggetto Person.
 *
 * Questo metodo restituisce il ruolo dell'oggetto `Person`, che è rappresentato dal nome
 * della classe dell'oggetto. Il metodo è implementato dalle classi che estendono `Person`, 
 * come `Contact` e `User`, per fornire una definizione specifica del ruolo.
 *
 * @pre Il metodo deve essere implementato nelle classi concrete che estendono `Person`.
 * @post Il metodo restituirà una stringa che rappresenta il ruolo dell'oggetto, generalmente
 *       basato sul nome della classe.
 * @invariant Le classi che estendono `Person` devono fornire un'implementazione coerente di questo metodo.
 *
 * @return Una stringa che rappresenta il ruolo dell'oggetto.
 */
    public abstract String getRole();
}
