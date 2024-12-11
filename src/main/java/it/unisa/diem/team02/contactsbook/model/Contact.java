
package it.unisa.diem.team02.contactsbook.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @brief
 * Classe che definisce i contatti memorizzabili in rubrica
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
     * @brief Crea un nuovo oggetto Contact con il nome e cognome forniti.
     * 
     * Questo costruttore inizializza il contatto con un nome e un cognome, e crea nuove liste vuote per i numeri di telefono, 
     * gli indirizzi email e i tag associati. Inoltre, genera un ID univoco per il contatto.
     * 
     * @param name Il nome del contatto.
     * @param surname Il cognome del contatto.
     * 
     * @pre
     * - Il parametro `name` e `surname` devono essere non nulli e rappresentare valori validi per un contatto.
     * 
     * @post
     * - La variabile di istanza `number` è stata inizializzata come una lista vuota di numeri di telefono.
     * - La variabile di istanza `email` è stata inizializzata come una lista vuota di indirizzi email.
     * - La variabile di istanza `tag` è stata inizializzata come una lista vuota di tag.
     * - Un ID univoco è stato generato e assegnato al contatto.
     * 
     * @see Contact#generateID() per la generazione dell'ID univoco.
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
     * @brief Crea un nuovo oggetto {@link Contact} con il nome, cognome e ID forniti.
     * 
     * Questo costruttore inizializza il contatto con un nome, un cognome e un ID, e crea nuove liste vuote per i numeri di 
     * telefono, gli indirizzi email e i tag associati.
     * 
     * @param name Il nome del contatto.
     * @param surname Il cognome del contatto.
     * @param ID L'ID univoco del contatto.
     * 
     * @pre
     * - Il parametro `name`, `surname` e `ID` devono essere non nulli e rappresentare valori validi per un contatto.
     * 
     * @post
     * - La variabile di istanza `number` è stata inizializzata come una lista vuota di numeri di telefono.
     * - La variabile di istanza `email` è stata inizializzata come una lista vuota di indirizzi email.
     * - La variabile di istanza `tag` è stata inizializzata come una lista vuota di tag.
     * - L'ID del contatto è stato impostato con il valore fornito nel parametro `ID`.
     * 
     * @invariant
     * - Il contatto avrà sempre un nome, un cognome, un ID valido e liste vuote per numeri, email e tag.
     * @note Questo costruttore consente di specificare manualmente l'ID del contatto, rendendolo 
     * utile in scenari come il caricamento di dati da un database o da file.
     */
    public Contact(String name, String surname, String ID) {
        super(name, surname);
        number = new ArrayList<>();
        email = new ArrayList<>();
        this.tag= new ArrayList<>();
        this.ID = ID;  
    }
    
    /**
     * @brief Restituisce i numeri di telefono del contatto.
     * 
     * Questo metodo restituisce i numeri di telefono associati al contatto. Se il contatto ha più di un numero, i numeri vengono 
     * restituiti separati da una nuova linea. Se non sono presenti numeri, viene restituita una stringa vuota.
     * 
     * @return Una stringa contenente i numeri di telefono del contatto, separati da nuove linee se ce ne sono più di uno.
     * 
     * @pre
     * - La lista `number` deve essere correttamente inizializzata e contenere i numeri di telefono del contatto.
     * 
     * @post
     * - La stringa restituita contiene i numeri di telefono del contatto, separati da nuove linee se presenti.
     * 
     * @invariant
     * - La lista `number` non è mai nulla e può contenere uno, due o più numeri di telefono.
     */
    public String getNumber(){
        if(number.isEmpty()) return "";
        if(number.size()==1) return number.get(0);
        if(number.size()==2) return number.get(0)+"\n"+number.get(1);
        return number.get(0)+"\n"+number.get(1)+"\n"+number.get(2);
    }

    /**
     * @brief Restituisce gli indirizzi email del contatto.
     * 
     * Questo metodo restituisce gli indirizzi email associati al contatto. Se il contatto ha più di un indirizzo, gli indirizzi 
     * vengono restituiti separati da una nuova linea. Se non sono presenti indirizzi email, viene restituita una stringa vuota.
     * 
     * @return Una stringa contenente gli indirizzi email del contatto, separati da nuove linee se ce ne sono più di uno.
     * 
     * @pre
     * - La lista `email` deve essere correttamente inizializzata e contenere gli indirizzi email del contatto.
     * 
     * @post
     * - La stringa restituita contiene gli indirizzi email del contatto, separati da nuove linee se presenti.
     * 
     * @invariant
     * - La lista `email` non è mai nulla e può contenere uno, due o più indirizzi email.
     */
    public String getEmail() {
        if(email.isEmpty()) return "";
        if(email.size()==1) return email.get(0);
        if(email.size()==2) return email.get(0)+"\n"+email.get(1);
        return email.get(0)+"\n"+email.get(1)+"\n"+email.get(2);
    }
    
    /**
     * @brief Restituisce i tag associati al contatto.
     * 
     * Questo metodo restituisce i tag associati al contatto. Se il contatto ha più di un tag, i tag vengono restituiti separati 
     * da una nuova linea. Se non sono presenti tag, viene restituita una stringa vuota.
     * 
     * @return Una stringa contenente i tag del contatto, separati da nuove linee se ce ne sono più di uno.
     * 
     * @pre
     * - La lista `tag` deve essere correttamente inizializzata e contenere i tag associati al contatto.
     * 
     * @post
     * - La stringa restituita contiene i tag del contatto, separati da nuove linee se presenti.
     * 
     * @invariant
     * - La lista `tag` non è mai nulla e può contenere uno, due o più tag.
     */
    public String getTag(){
        if(tag.isEmpty()) return "";
        if(tag.size()==1) return tag.get(0).toString();        
        if(tag.size()==2) return tag.get(0).toString()+"\n"+tag.get(1);
        return tag.get(0).toString()+"\n"+tag.get(1).toString()+"\n"+
                tag.get(2).toString();
    }
    
    /**
     * @brief Aggiunge un tag al contatto.
     * 
     * Questo metodo aggiunge un tag alla lista dei tag del contatto, se il tag non è già presente. Se il tag è nullo, viene 
     * generata un'eccezione IllegalArgumentException.
     * 
     * @param tag Il tag da aggiungere al contatto.
     * 
     * @throws IllegalArgumentException Se il parametro `tag` è nullo.
     * 
     * @pre
     * - Il parametro `tag` deve essere non nullo.
     * - Il tag non deve essere già presente nella lista dei tag.
     * 
     * @post
     * - Se il tag è valido e non duplicato, viene aggiunto alla lista dei tag del contatto.
     * 
     * @invariant
     * - La lista `tag` non contiene mai duplicati.
     */
    public void addTag(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Il tag non può essere null.");
        }        
        if (!this.tag.contains(tag)) {
            this.tag.add(tag);
        }
    }
    public void removeTag(Tag tag, int index) {
        
    
    }
    
    
    /**
     * @brief Aggiunge un numero di telefono al contatto.
     * 
     * Questo metodo aggiunge un numero di telefono alla lista dei numeri di telefono del contatto.
     * 
     * @param number Il numero di telefono da aggiungere al contatto.
     * 
     * @pre
     * - Il parametro `number` deve essere una stringa non nulla e rappresentare un numero valido.
     * 
     * @post
     * - Il numero di telefono fornito viene aggiunto alla lista dei numeri del contatto.
     * 
     * @invariant
     * - La lista `number` mantiene uno stato coerente
     */
    public void addNumber(String number) {
        this.number.add(number);
    }
    
    /**
     * @brief Aggiunge un indirizzo email al contatto.
     * 
     * Questo metodo aggiunge un indirizzo email alla lista degli indirizzi email del contatto.
     * 
     * @param email L'indirizzo email da aggiungere al contatto.
     * 
     * @pre
     * - Il parametro `email` deve essere una stringa non nulla e rappresentare un indirizzo email valido.
     * 
     * @post
     * - L'indirizzo email fornito viene aggiunto alla lista degli indirizzi email del contatto.
     * 
     * @invariant
     * - La lista `email` mantiene uno stato coerente
     */
    public void addEmail(String email){
        this.email.add(email);
    }
    
/**
 * @brief Imposta il campo number con la lista fornita.
 *
 * @param number L'ArrayList di stringhe da assegnare al campo number.
 *
 * @pre Il parametro `number` non deve essere null.
 * @post Il campo `number` dell'oggetto viene aggiornato con la lista fornita.
 * @invariant Il campo `number` deve sempre contenere un ArrayList valido di stringhe (i valori null non sono ammessi).
 */
    public void setNumber(ArrayList<String> number) {
        this.number = number;
    }
    
/**
 * @brief Imposta il campo email con la lista fornita.
 *
 * @param email L'ArrayList di stringhe da assegnare al campo email.
 *
 * @pre Il parametro `email` non deve essere null.
 * @post Il campo `email` dell'oggetto viene aggiornato con la lista fornita.
 * @invariant Il campo `email` deve sempre contenere un ArrayList valido di stringhe (i valori null non sono ammessi).
 */
    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }
    
/**
 * @brief Imposta il campo tag con la lista fornita.
 *
 * @param tag L'ArrayList di oggetti Tag da assegnare al campo tag.
 *
 * @pre Il parametro `tag` non deve essere null.
 * @post Il campo `tag` dell'oggetto viene aggiornato con la lista fornita.
 * @invariant Il campo `tag` deve sempre contenere un ArrayList valido di oggetti Tag (i valori null non sono ammessi).
 */
    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }
    
/**
 * @brief Restituisce la lista di tag.
 *
 * @return L'ArrayList di oggetti Tag contenuto nel campo tag.
 *
 * @pre Il campo `tag` deve essere inizializzato.
 * @post Nessuna modifica allo stato dell'oggetto.
 * @invariant Il campo `tag` deve sempre contenere un ArrayList valido di oggetti Tag (i valori null non sono ammessi).
 */
     public ArrayList<Tag> getTagList() {
        return tag;
    }
     
/**
 * @brief Restituisce la lista dei numeri.
 *
 * @pre La variabile d'istanza `number` deve essere stata inizializzata correttamente.
 *      Non deve essere null.
 * @post La lista restituita è immutata rispetto a prima della chiamata del metodo.
 * @invariant L'oggetto `number` rimane consistente in ogni stato dell'oggetto.
 *
 * @return Un oggetto ArrayList contenente la lista dei numeri.
 */
    public ArrayList<String> getNumberList(){
        return this.number ;
    }
    
/**
 * @brief Restituisce la lista delle email.
 *
 * @pre La variabile d'istanza `email` deve essere stata inizializzata correttamente
 *      e non deve essere null.
 * @post La lista restituita non viene modificata durante l'esecuzione del metodo.
 * @invariant La variabile `email` rimane consistente e non viene alterata
 *            da questo metodo.
 *
 * @return Un oggetto ArrayList contenente la lista delle email.
 */
    public ArrayList<String> getEmailList() {
        return email;
    }
    
/**
 * @brief Restituisce l'ID associato a questo oggetto.
 *
 * @pre La variabile d'istanza `ID` deve essere stata inizializzata e non deve essere null.
 * @post Il valore della variabile `ID` rimane immutato dopo la chiamata del metodo.
 * @invariant La variabile `ID` deve sempre contenere un valore valido (non null e coerente con il contesto).
 *
 * @return Una stringa contenente l'ID associato a questo oggetto.
 */
     public String getID() {
        return ID;
    }
    
/**
 * @brief Restituisce il ruolo dell'oggetto come stringa.
 *
 * @pre L'oggetto deve essere correttamente istanziato e il metodo può essere chiamato su qualsiasi istanza valida.
 * @post Il valore restituito è una rappresentazione testuale della classe dell'oggetto.
 * @invariant L'implementazione del metodo `getClass()` deve fornire informazioni consistenti sulla classe dell'oggetto.
 *
 * @return Una stringa che rappresenta il ruolo dell'oggetto, basato sulla classe dell'istanza.
 */
    @Override
    public String getRole() {
        return "Contact";
    }
    
    
/**
 * @brief Genera una rappresentazione testuale dell'oggetto.
 *
 * @pre La variabile d'istanza `number` deve essere stata inizializzata correttamente 
 *      (non null) e può contenere una lista di numeri di telefono.
 * @pre La variabile d'istanza `email` deve essere stata inizializzata correttamente 
 *      (non null) e può contenere una lista di indirizzi email.
 * @post La stringa restituita include il ruolo dell'oggetto, il risultato del metodo
 *       `super.toString()`, i numeri di telefono e gli indirizzi email associati.
 * @invariant Le variabili `number` ed `email` rimangono consistenti e non vengono
 *            modificate durante l'esecuzione del metodo.
 *
 * @return Una stringa che rappresenta l'oggetto, contenente il ruolo, 
 *         i numeri di telefono e gli indirizzi email associati.
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
    
/**
 * @brief Genera un ID basato sulla data e ora correnti con precisione ai nanosecondi.
 *
 * @pre Il sistema deve essere in grado di ottenere la data e l'ora correnti.
 * @post Viene restituito un identificatore unico sotto forma di stringa che rappresenta
 *       la data e l'ora correnti fino ai nanosecondi.
 * @invariant Il formato dell'ID generato è sempre coerente con il pattern specificato 
 *            "yyyy-MM-dd HH:mm:ss.SSSSSSSSS" e non dipende dallo stato dell'oggetto.
 *
 * @return Una stringa che rappresenta la data e ora correnti con precisione ai nanosecondi.
 */
        private  String generateID(){
    
        LocalDateTime now = LocalDateTime.now();
    
        // Formatta l'ora corrente con nanosecondi 
        DateTimeFormatter nanoFormatter; 
        nanoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        String id = now.format(nanoFormatter); 
        return id;
    }
}
    
    
    
  
