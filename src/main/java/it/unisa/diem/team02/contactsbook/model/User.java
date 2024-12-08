
package it.unisa.diem.team02.contactsbook.model;

/**
 * Classe che estende la classe astratta Person. Definisce degli ulteriori attributi e metodi
 * per effettuare specifiche operazioni sugli oggetti User.
 */
public class User extends Person{
    private String password;
    private final String email;

    
/**
 * @brief Costruttore per creare un oggetto `User` con password, email, nome e cognome.
 * 
 * Questo costruttore inizializza un oggetto `User` con i valori di password, email, nome e cognome forniti.
 * 
 * @param password La password dell'utente.
 * @param email L'email dell'utente.
 * @param name Il nome dell'utente.
 * @param surname Il cognome dell'utente.
 * 
 * @details 
 * - Il costruttore accetta i parametri `password`, `email`, `name` e `surname` e li utilizza per inizializzare
 *   le rispettive variabili d'istanza.
 * - Il nome e il cognome vengono passati al costruttore della classe padre tramite la chiamata a `super(name, surname)`.
 * - La password e l'email sono specifiche per ogni utente.
 */
    public User(String password, String email, String name, String surname) {
        super(name, surname);
        this.password = password;
        this.email = email;
    }
    
/**
 * @brief Imposta la password dell'utente.
 * 
 * Questo metodo consente di assegnare una nuova password all'oggetto `User`.
 * 
 * @param password La nuova password da associare all'utente.
 * 
 */
    public void setPassword(String password) {
        this.password = password;
    }
    
/**
 * @brief Restituisce l'email dell'utente.
 * 
 * Questo metodo restituisce l'email associata all'oggetto `User`.
 * 
 * @return L'email dell'utente come stringa.
 * 
 */
    public String getEmail() {
        return email;
    }
    
    
/**
 * @brief Restituisce il ruolo dell'utente come stringa.
 * 
 * Questo metodo sovrascrive il metodo `getRole` e restituisce una rappresentazione del ruolo dell'oggetto 
 * basata sul nome completo della classe dell'oggetto.
 * 
 * @return Una stringa che rappresenta il ruolo dell'utente, in questo caso il nome completo della classe.
 */
    @Override
    public String getRole() {
        return this.getClass().toString();
    }
    
/**
 * @brief Restituisce una rappresentazione testuale dell'utente.
 * 
 * Questo metodo sovrascrive il metodo `toString` della classe `Object` per fornire una descrizione completa dell'oggetto `User`.
 * La rappresentazione include il ruolo dell'utente, le informazioni ereditate dalla classe padre e l'email dell'utente.
 * 
 * @return Una stringa che rappresenta l'utente, comprendente il ruolo, il nome e cognome ereditati dalla classe `Person`, 
 *         e l'email dell'utente.
 * 
 * @details 
 * - La stringa restituita avrà il formato: 
 *   `"Role: ruolo Name: nome Surname: cognome Email: eemail"`.
 */
    @Override
    public String toString(){
        return this.getRole()+" "+super.toString()+" Email: "+email;
    }
}

