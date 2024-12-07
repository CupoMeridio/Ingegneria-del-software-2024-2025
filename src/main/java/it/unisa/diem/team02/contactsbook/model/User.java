
package it.unisa.diem.team02.contactsbook.model;

/**
 * 
 * Classe che estende la classe astratta Person. Definisce degli ulteriori attributi e metodi
 * per effettuare specifiche operazioni sugli oggetti User.
 */
public class User extends Person{
    private String password;
    private final String email;

    public User(String password, String email, String name, String surname) {
        super(name, surname);
        this.password = password;
        this.email = email;
    }
    
    /*
    * 
    * Imposta la password of the current object.
    * 
    * @param String password.
    *
    * @author team02
    */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /*
    * 
    * Restituisce l'email dell'oggetto corrente.
    * @param String password..
    */
    public String getEmail() {
        return email;
    }
    
    @Override
    public String getRole() {
        return this.getClass().toString();
    }
    
    /*
    * 
    * Restituisce le informazioni di un oggetto Person nel seguente formato:
    * Ruolo Name: <nome> Surname: <cognome> Email: <email>
    *
    */
    @Override
    public String toString(){
        return this.getRole()+" "+super.toString()+" Email: "+email;
    }
}

