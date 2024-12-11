
package it.unisa.diem.team02.contactsbook.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * 
 * Classe che estende la classe astratta Person. Definisce degli ulteriori attributi e metodi
 * per effettuare specifiche operazioni sugli oggetti Contact. Gli attributi
 * number, email e tag sono implementati tramite un ArrayList
 * 
 * @author team02
 */
public class Contact extends Person{
    private ArrayList<String> number;
    private ArrayList<String> email;
    private ArrayList<Tag> tag;
    //private static int contatore; // bisogna Salvare il numero di contatti da rivedere 
    private final String ID;
    
    
/**
 * @brief Costruttore della classe `Contact`.
 * 
 * Inizializza un nuovo oggetto `Contact` con il nome, il cognome e i campi predefiniti per numeri di telefono, email e tag.
 * 
 * @param name Il nome del contatto.
 * @param surname Il cognome del contatto.
 * 
 * @details 
 * - Il costruttore chiama il costruttore della superclasse per inizializzare il nome e il cognome.
 * - Inizializza i campi `number`, `email` e `tag` come liste vuote con una capacità iniziale di 3 elementi.
 * - Assegna un identificativo unico al contatto (`ID`) utilizzando un contatore statico.
 * - Incrementa il contatore statico dopo aver assegnato l'ID.
 */
    public Contact(String name, String surname) {
        super(name, surname);
        number = new ArrayList<String>();
        email = new ArrayList<String>();
        this.tag= new ArrayList<Tag>();
        this.ID = this.generateID();
    }
    
   /* public static void setContatore(int contatore) {
        Contact.contatore = contatore;
    }*/
    
  /**
  * lang it
  * @brief Costruttore della classe Contact con ID specificato.
  * 
  * Inizializza un nuovo oggetto `Contact` con il nome, il cognome e un ID specificato. 
  * Inoltre, inizializza le liste `number`, `email` e `tag` come vuote.
  * 
  * @param name Nome del contatto.
  * @param surname Cognome del contatto.
  * @param ID Identificatore univoco del contatto.
  * 
  * @note Questo costruttore consente di specificare manualmente l'ID del contatto, 
  *       rendendolo utile in scenari come il caricamento di dati da un database o da file.
  * 
  */
    public Contact(String name, String surname, String ID) {
        super(name, surname);
        number = new ArrayList<>();
        email = new ArrayList<>();
        this.tag= new ArrayList<>();
        this.ID = ID;  
    }
    
 /**
 * 
 * @brief Restituisce i numeri di telefono del contatto in formato leggibile.
 * 
 * Questo metodo restituisce i numeri di telefono associati al contatto in base alla 
 * quantità presente nella lista `number`. Se ci sono:
 * - Nessun numero: restituisce `null`.
 * - Un numero: restituisce il numero.
 * - Due numeri: restituisce i due numeri separati da una nuova riga (`\n`).
 * - Tre o più numeri: restituisce i primi tre numeri separati da nuove righe.
 * 
 * @return Una stringa contenente i numeri di telefono formattati o `null` se 
 *         la lista è vuota.
 * 
 */
    public String getNumber(){
        if(number.isEmpty()) return "";
        if(number.size()==1) return number.get(0);
        if(number.size()==2) return number.get(0)+"\n"+number.get(1);
        return number.get(0)+"\n"+number.get(1)+"\n"+number.get(2);
    }
 /**
 *
 * @brief Restituisce gli indirizzi email del contatto in formato leggibile.
 * 
 * Questo metodo restituisce gli indirizzi email associati al contatto in base alla 
 * quantità presente nella lista `email`.
 * Se ci sono:
 * - Nessun indirizzo: restituisce `null`.
 * - Un indirizzo: restituisce l'indirizzo.
 * - Due indirizzi: restituisce i due indirizzi separati da una nuova riga (`\n`).
 * - Tre o più indirizzi: restituisce i primi tre indirizzi separati da nuove righe.
 * 
 * @return Una stringa contenente gli indirizzi email formattati o `null` se 
 *         la lista è vuota.
 * 
 * @note Il metodo restituisce al massimo tre indirizzi, anche se la lista `email` 
 *       contiene più di tre elementi.
 * 
 */
    public String getEmail() {
        if(email.isEmpty()) return "";
        if(email.size()==1) return email.get(0);
        if(email.size()==2) return email.get(0)+"\n"+email.get(1);
        return email.get(0)+"\n"+email.get(1)+"\n"+email.get(2);
    }
    
    /*
    * 
    * Restituisce tutti i tag dell'oggetto corrente.
    */
    public String getTag(){
        if(tag.isEmpty()) return "";
        if(tag.size()==1) return tag.get(0).toString();        
        if(tag.size()==2) return tag.get(0).toString()+"\n"+tag.get(1);
        return tag.get(0).toString()+"\n"+tag.get(1).toString()+"\n"+
                tag.get(2).toString();
    }
    
    /**
    * @brief Aggiunge un tag alla lista di tag del contatto.
    * 
    * Questo metodo consente di associare un nuovo tag al contatto, assicurandosi che 
    * non vengano aggiunti valori null o tag duplicati.
    * 
    * @param tag L'oggetto `Tag` da aggiungere alla lista.
    * 
    * @throws IllegalArgumentException Se il parametro `tag` è null.
    * 
    * @details 
    * - Se il tag fornito non è già presente nella lista, viene aggiunto.
    * - Il metodo utilizza il metodo `contains` della lista per verificare la presenza del tag.
    * - I tag duplicati non vengono aggiunti.
    * 
    */
    public void addTag(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Il tag non può essere null.");
        }        
        if (!this.tag.contains(tag)) {
            this.tag.add(tag);
        }
    }
    
   /**
    * @brief Aggiunge un numero alla lista di numeri del contatto.
    * 
    * Questo metodo consente di aggiungere un nuovo numero alla lista dei numeri di telefono del contatto.
    * 
    * @param number Il numero di telefono da aggiungere alla lista.
    * 
    * @details 
    * - Il numero fornito viene aggiunto alla lista `number` del contatto.
    * - Non sono presenti controlli per la validità o unicità del numero, che potrebbero essere aggiunti se necessario.
    */
    public void addNumber(String number) {
        this.number.add(number);
    }
    
    /**
    * @brief Aggiunge un indirizzo email alla lista di email del contatto.
    * 
    * Questo metodo consente di aggiungere un nuovo indirizzo email alla lista di email del contatto.
    * 
    * @param email L'indirizzo email da aggiungere alla lista.
    * 
    * @details 
    * - L'indirizzo email fornito viene aggiunto alla lista `email` del contatto.
    * - Non sono presenti controlli per la validità o unicità dell'email, che potrebbero essere aggiunti se necessario.
    */
    public void addEmail(String email){
        this.email.add(email);
    }
    
   /**
    * @brief Imposta la lista di numeri del contatto.
    * 
    * Questo metodo consente di assegnare una nuova lista di numeri di telefono al contatto.
    * 
    * @param number La lista di numeri di telefono da associare al contatto.
    * 
    * @details 
    * - Il parametro `number` è un oggetto `ArrayList` contenente i numeri di telefono che si desidera assegnare al contatto.
    * - Il metodo sostituisce la lista di numeri esistente con la nuova lista fornita.
    */
    public void setNumber(ArrayList<String> number) {
        this.number = number;
    }
    
    /**
    * @brief Imposta la lista di indirizzi email del contatto.
    * 
    * Questo metodo consente di assegnare una nuova lista di indirizzi email al contatto.
    * 
    * @param email La lista di indirizzi email da associare al contatto.
    * 
    * @details 
    * - Il parametro `email` è un oggetto `ArrayList` contenente gli indirizzi email da assegnare al contatto.
    * - Il metodo sostituisce la lista di indirizzi email esistente con la nuova lista fornita.
    */
    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }
    
/**
 * @brief Imposta la lista di tag del contatto.
 * 
 * Questo metodo consente di assegnare una nuova lista di tag al contatto.
 * 
 * @param tag La lista di tag da associare al contatto.
 * 
 * @details 
 * - Il parametro `tag` è un oggetto `ArrayList` contenente i tag da assegnare al contatto.
 * - Il metodo sostituisce la lista di tag esistente con la nuova lista fornita.
 */
    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }
    
/**
 * @brief Restituisce la lista di tag del contatto.
 * 
 * Questo metodo restituisce la lista di tag associata al contatto.
 * 
 * @return La lista di tag del contatto.
 * 
 * @details 
 * - Il metodo restituisce direttamente la lista di tag, consentendo l'accesso ai tag associati al contatto.
 * - La lista restituita è la stessa utilizzata internamente per memorizzare i tag del contatto.
 */
     public ArrayList<Tag> getTagList() {
        return tag;
    }
     
/**
 * @brief Restituisce la lista di numeri di telefono del contatto.
 * 
 * Questo metodo restituisce la lista di numeri di telefono associata al contatto.
 * 
 * @return La lista di numeri di telefono del contatto
 * 
 * @details 
 * - Il metodo restituisce direttamente la lista dei numeri di telefono, consentendo l'accesso ai numeri associati al contatto.
 * - La lista restituita è la stessa utilizzata internamente per memorizzare i numeri di telefono del contatto.
 */
    public ArrayList<String> getNumberList(){
        return this.number ;
    }
    
/**
 * @brief Restituisce la lista di indirizzi email del contatto.
 * 
 * Questo metodo restituisce la lista di indirizzi email associata al contatto.
 * 
 * @return La lista di indirizzi email del contatto
 * 
 * @details 
 * - Il metodo restituisce direttamente la lista degli indirizzi email, consentendo l'accesso agli indirizzi associati al contatto.
 * - La lista restituita è la stessa utilizzata internamente per memorizzare gli indirizzi email del contatto.
 */
    public ArrayList<String> getEmailList() {
        return email;
    }
    
/**
 * @brief Restituisce l'ID del contatto.
 * 
 * Questo metodo restituisce l'identificativo unico del contatto.
 * 
 * @return L'ID del contatto come valore intero (`int`).
 * 
 * @details 
 * - L'ID è un valore numerico univoco assegnato al contatto.
 * - Questo metodo fornisce un accesso diretto all'ID, che potrebbe essere utilizzato per identificare il contatto in modo univoco all'interno di un sistema.
 */
     public String getID() {
        return ID;
    }
    
 /**
 * 
 * @brief Restituisce il ruolo della classe corrente.
 * Questo metodo sovrascrive il metodo `getRole` della classe base. 
 * Restituisce il nome completo della classe corrente come stringa, utilizzando 
 * il metodo `toString` dell'oggetto `Class`.
 * 
 * @return Una stringa che rappresenta il nome completo della classe corrente.
 * 
 */
    @Override
    public String getRole() {
        return this.getClass().toString();
    }
    
    
/**
 * @brief Restituisce una rappresentazione testuale del contatto.
 * 
 * Questo metodo sovrascrive il metodo `toString` della classe `Object` per fornire una descrizione dettagliata
 * del contatto, inclusi il ruolo, i numeri di telefono e gli indirizzi email associati.
 * 
 * @return Una stringa contenente la rappresentazione del contatto.
 * 
 * @details 
 * - La rappresentazione include il ruolo del contatto, che viene recuperato tramite il metodo `getRole()`.
 * - Vengono poi aggiunti i numeri di telefono e gli indirizzi email associati al contatto.
 * - Ogni numero di telefono è preceduto dalla stringa "Phone number: ", mentre ogni indirizzo email è preceduto da "Email: ".
 * - La rappresentazione è costruita utilizzando un oggetto `StringBuffer` per migliorare le performance durante
 *   la concatenazione delle stringhe.
 */
    @Override
    public String toString(){
        StringBuffer sb=new StringBuffer(this.getRole()+" "+super.toString());
        for (int i=0; i<this.number.size();i++)
            sb=sb.append(" Phone number: ").append(number.get(i));
        for (int i=0; i<this.email.size();i++)
            sb=sb.append(" Email: ").append(email.get(i));
        return sb.toString();
    }
    
    
    private  String generateID(){
    
        LocalDateTime now = LocalDateTime.now();
    
        // Formatta l'ora corrente con nanosecondi 
        DateTimeFormatter nanoFormatter; 
        nanoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        String id = now.format(nanoFormatter); 
        return id;
    }
}
    
    
    
  
