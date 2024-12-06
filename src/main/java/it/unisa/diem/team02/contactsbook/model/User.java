
package it.unisa.diem.team02.contactsbook.model;

/**
 * @lang it
 * Classe che estende la classe astratta {@code Person}. Definisce degli ulteriori attributi e metodi
 * per effettuare specifiche operazioni sugli oggetti {@code User}.
 * 
 * @lang en
 * Class that extends the abstract class {@code Person}. It defines additional attributes and methods
 * to perform specific operations on {@code User} objects.
 * 
 */
public class User extends Person{
    private String password;
    private final String email;

    public User(String password, String email, String name, String surname) {
        super(name, surname);
        this.password = password;
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public String getRole() {
        return this.getClass().toString();
    }
    
    /*
    * @lang it
    * Restituisce le informazioni di un oggetto {@code Person} nel seguente formato:
    * {@code Ruolo Name: <nome> Surname: <cognome> Email: <email>}
    *
    * @lang en
    * Returns the information of a {@code Person} object in the format:
    * {@code Role Name: <name> Surname: <surname> Email: <email>}
    *
    *
    */
    @Override
    public String toString(){
        return this.getRole()+" "+super.toString()+" Email: "+email;
    }
}

