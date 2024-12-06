/**
 * @brief Gestisce la connessione al database e le operazioni CRUD relative agli utenti e ai contatti.
 * 
 * Questa classe fornisce metodi per connettersi a un database PostgreSQL, inserire e recuperare utenti,
 * verificare il login e ottenere contatti. Utilizza la libreria BCrypt per la gestione sicura delle password.
 * 
 */
package it.unisa.diem.team02.contactsbook.database;
import it.unisa.diem.team02.contactsbook.model.Contact;
import it.unisa.diem.team02.contactsbook.model.Tag;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author cupom
 */
public class Database  {
        
/**
 * @lang it
 * @brief Stabilisce una connessione al database PostgreSQL.
 * 
 * @param dbname Nome del database a cui connettersi.
 * @param user Nome del possessore del database in pgAdmin.
 * @param password Password per accedere al Database .
 * @return Oggetto Connection se la connessione è riuscita, altrimenti null.
 * @throws SQLException Se si verifica un errore durante la connessione.
 * 
 * @lang en
 * @brief Establishes a connection to the PostgreSQL database.
 *
 * @param dbname Name of the database to connect to.
 * @param user Name of the database owner in pgAdmin.
 * @param password Password to access the database.
 * @return Connection object if the connection is successful, otherwise null.
 * @throws SQLException If an error occurs during the connection.
 */
    
    public Connection ConnectionDB(String dbname, String user, String password) {
        Connection conn=null;
        
       
        try {
            Class.forName("org.postgresql.Driver");
            System.out.print("Driver trovato ");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,password);
            if(conn!=null){
                System.out.println("Connessione Stabilita");
            }else{
                System.out.println("Connessione Fallita");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
           return conn;
    }
    
    
    /**
    * @lang it
    * @brief Inserisce un nuovo utente nella tabella specificata.
    * 
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella in cui inserire l'utente.
    * @param email Email dell'utente.
    * @param password Password dell'utente (verrà criptata  prima dell'inserimento).
    * @throws SQLException Se si verifica un errore durante l'inserimento.
    * @lang en
    * 
    * @brief Inserts a new user into the specified table.
    * 
    * @param conn Connection object to interact with the database.
    * @param tableName Name of the table in which to insert the user.
    * @param email Email of the user.
    * @param password Password of the user (will be encrypted before insertion).
    * @throws SQLException If an error occurs during the insertion.
    */

    public void insertUser(Connection conn, String tableName, String email, String password) throws SQLException{
    
           Statement statment;
           String pass= hashPassword(password);
           String query= String.format("insert into %s(email,password) values('%s','%s');",tableName, email, pass);
           statment= conn.createStatement();
           statment.executeUpdate(query);
           System.out.println("Utente inserito");
        
    }
    
    /**
    * @lang it
    * @brief Recupera tutti gli utenti dal database.
    * 
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella da cui recuperare gli utenti.
    * @return HashMap contenente le coppie email-password degli utenti.
    * @lang en
    * 
    * @brief Retrieves all users from the database.
    * 
    * @param conn Connection object to interact with the database.
    * @param tableName Name of the table from which to retrieve the users.
    * @return HashMap containing the email-password pairs of the users.
    */

  
    public HashMap<String, String> getUser(Connection conn, String tableName){
        
        Statement statement;
        ResultSet rs= null;
        Map <String,String>table=null;
        String email;
        String password;
        try {
            table =  new HashMap();
            String query= String.format("select * from %s", tableName);
            statement= conn.createStatement();
            rs= statement.executeQuery(query);
            while(rs.next()){
                email=rs.getString("email");
                password=rs.getString("password");
                table.put(email, password);
                
               /* System.out.print(email+" ");
                System.out.print(password+" ");*/
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return (HashMap<String, String>) table;
    }
    
    
    
    /**
    * @lang it
    * @brief Verifica le credenziali di login di un utente.
    * 
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella contenente gli utenti.
    * @param email Email dell'utente.
    * @param password Password inserita dall'utente.
    * @return 1 se le credenziali sono corrette, 0 se la password è errata, -1 se l'email non esiste.
    * @throws SQLException Se si verifica un errore durante l'interrogazione.
    * @lang en
    * 
    * @brief Verifies the login credentials of a user.
    * 
    * @param conn Connection object to interact with the database.
    * @param tableName Name of the table containing the users.
    * @param email Email of the user.
    * @param password Password entered by the user.
    * @return 1 if the credentials are correct, 0 if the password is incorrect, -1 if the email does not exist.
    * @throws SQLException If an error occurs during the query.
    */

    
    public int checkLogin(Connection conn,String tableName, String email, String password) throws SQLException{
    
    ResultSet rs= null;
    int esito;  
            Statement statment;
            String query= String.format("select * from %s where email='%s'", tableName,email);
            statment= conn.createStatement();
            rs=statment.executeQuery(query);
         
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found."); 
                esito= -1;// -1 perchè email non è presente nel db
            }else{
                do{
                    String em=rs.getString("email");
                    String hashed = rs.getString("password");
                    System.out.print("\n"+email+"   checkLogin");
                   

                    if( checkPassword( password,hashed)){
                         System.out.print(" \n password Giusta ");
                         esito=1; // email e password sono corette e l' utente entra
                    }else{
                       System.out.print(" \n password Sbagliata ");
                       esito= 0;// email corretta e password sbagliata
                    }
                }while(rs.next());
           }       
        return esito;
    }
    
    /**
    * @lang it
    * @brief Cripta la password passata dal utente 
    * 
    * @param password è la password passata alla funzione che verra criptata
    * @return  Una stringa criptata
    * @lang en
    * 
    * @brief Encrypts the password provided by the user.
    * 
    * @param password The password passed to the function to be encrypted.
    * @return An encrypted string.
    */

    
    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
        //per renderlo più sicuro conviene aggiungere un salt
    }
    
    /**
    * @lang it
    * @brief Verifica se le 2 password corrispondono 
    * 
    * @param password è la password non criptata passata alla funzione 
    * @param hashed è la password criptata passata alla funzione 
    * @return  true se la password è uguale a hashed, se sono diverse false
    * @lang en
    * 
    * @brief Verifies if the two passwords match.
    * 
    * @param password The unencrypted password passed to the function.
    * @param hashed The encrypted password passed to the function.
    * @return true if the password matches the hashed password, false otherwise.
    */


    private static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
    
    
    
    
    /**
    * @lang it
    * @brief Recupera i contatti associati a un utente specifico.
    * 
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella contenente i contatti.
    * @param email Email dell'utente di cui recuperare i contatti.
    * @return HashMap contenente i contatti dell'utente.
    * @lang en
    * 
    * @brief Retrieves the contacts associated with a specific user.
    * 
    * @param conn Connection object to interact with the database.
    * @param tableName Name of the table containing the contacts.
    * @param email Email of the user whose contacts are to be retrieved.
    * @return HashMap containing the user's contacts.
    */

    
   public HashMap<String, Contact> getContact(Connection conn, String tableName,String email){
        
        Statement statement;
        ResultSet rs= null;
        Map <String,Contact>table=null;
        try {
            table =  new HashMap();
            String query= String.format("select * from %s where email='%s'", tableName,email);
            statement= conn.createStatement();
            rs= statement.executeQuery(query);
            while(rs.next()){
            //  String em = rs.getString("email");
                String name = rs.getString("name");
                String surname= rs.getString("surname");
                String numeri = rs.getString("number");
                String tag = rs.getString("tag");
                String em_cont = rs.getString("email_contact");
                String ID = rs.getString("id");
                table.put(ID, createContact(name,surname,numeri,tag,em_cont,ID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return (HashMap<String, Contact>) table;
    }
    
    /**
    * @lang it 
    * @brief Crea il contatto partendo dai parametri passati.
    * 
    * @param name nome del contatto.
    * @param surname cognome del contatto.
    * @param numeri stringa contenete i numeri del contatto con dei separatori.
    * @param tag stringa contenete i tag del contatto con dei separatori.
    * @param em_cont stringa contenete l' emails del contatto con dei separatori.
    * @param ID contiene l' identificativo univoco del contatto
    * @return Contact contenente con tutti i parametri passati.
    * @lang en
    * @brief Creates a contact from the given parameters.
    * 
    * @param name Name of the contact.
    * @param surname Surname of the contact.
    * @param numeri String containing the contact's numbers with separators.
    * @param tag String containing the contact's tags with separators.
    * @param em_cont String containing the contact's emails with separators.
    * @param ID Contains the unique identifier of the contact.
    * @return Contact containing all the passed parameters.
 */

   private Contact createContact(String name, String surname, String numeri, String tag, String em_cont,String ID) {
        Contact c= new Contact(name,surname, Integer.valueOf(ID));
        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> e = new ArrayList<>();
        ArrayList<Tag> t = new ArrayList<>();
        
        
            Scanner i = new Scanner(tag);
            i.useDelimiter(";");
             while(i.hasNext()){
                 t.add( Tag.valueOf(i.next()));
            }
             System.out.print(t+" \n");
             c.setTag(t);
             
            i = new Scanner(numeri);
            i.useDelimiter(";");
            while(i.hasNext()){
                 n.add( i.next());
            }
             System.out.print(n+" \n");
             c.setNumber(n);
            
            i = new Scanner(em_cont);
            i.useDelimiter(";");
            while(i.hasNext()){
                 e.add( i.next());
            }
            System.out.print(e+" \n");
             c.setEmail(e);
        
        return c;
    }
    
    /**
    * @lang it 
    * @brief Inserisce il contatto associato ad un utente specifico.
    * 
    * @param cont contatto da aggiungere
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella contenente i contatti.
    * @param email_Utente Email dell'utente che ha fatto il login.
    * @throws SQLException Se si verifica un errore durante l'interrogazione.
    * @lang en
    * @brief Inserts the contact associated with a specific user.
    * 
    * @param cont Contact to be added.
    * @param conn Connection object to interact with the database.
    * @param tableName Name of the table containing the contacts.
    * @param email_Utente Email of the user who logged in.
    * @throws SQLException If an error occurs during the query.
    */

    
     public void insertContact(Connection conn, String tableName, Contact cont, String email_Utente) throws SQLException{
    
           Statement statment;
           String nm=cont.getName();
           String srn=cont.getSurname();
           String ID= String.valueOf(cont.getID());
           
           ArrayList<String> e = cont.getEmailList();
           ArrayList<String> n = cont.getNumberList();
           ArrayList<Tag> t =cont.getTagList();
           
            /*formatto le email*/
           String email= formattaOut(e);
           
            /*formatto i numeri*/
           String number= formattaOut(n);
           
           /*formatto i tag*/
           ArrayList<String> St= new ArrayList<>();
           for (Tag i : t) { 
               St.add(i.name());
           }
           String tag= formattaOut(St);
           
           
           String query;
        query = String.format("insert into %s(email,name,surname,number,tag,email_contact,id) values('%s','%s','%s','%s','%s','%s','%s');",tableName,  email_Utente,nm,srn,number,tag,email,ID);
           statment= conn.createStatement();
           statment.executeUpdate(query);
           System.out.println("Contatto inserito");
        
    }
    
    /**
    * @lang it 
    * @brief Permette di impostare un ArrayList su una strinca aggiungendo un separatore specifico.
    * @param s ArrayList da formattare.
    * @return Una stringa con tutti i valori dis separati da un separatore
    * @lang en
    * @brief Allows setting an ArrayList to a string by adding a specific separator.
    * @param s ArrayList to format.
    * @return A string with all values separated by a separator.
 */

    private String formattaOut(ArrayList<String> s) {
        
        String formattata="";
        for(String i: s){
          
            formattata=formattata+i+";";
        }
        return formattata;
    }
    
    /**
    * @lang it
    * @brief Modifica il contatto associato a un utente specifico nel Database.
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella contenente i contatti.
    * @param cont il contatto da modificare nel Database
    * @param email_Utente Email dell'utente che ha fatto il login.
    * @throws SQLException Se si verifica un errore durante l'interrogazione.
    * @lang en
    * @brief Modifies the contact associated with a specific user in the database.
    * 
    * @param conn Connection object to interact with the database.
    * @param tableName Name of the table containing the contacts.
    * @param cont The contact to be modified in the database.
    * @param email_Utente Email of the user who logged in.
    * @throws SQLException If an error occurs during the query.
    */

    public void modifyContact(Connection conn, String tableName, Contact cont, String email_Utente) throws SQLException{
        
         Statement statment;
           String nm=cont.getName();
           String srn=cont.getSurname();
           String ID= String.valueOf(cont.getID());
           
           ArrayList<String> e = cont.getEmailList();
           ArrayList<String> n = cont.getNumberList();
           ArrayList<Tag> t =cont.getTagList();
        
             /*formatto le email*/
           String email= formattaOut(e);
           
            /*formatto i numeri*/
           String number= formattaOut(n);
           
           /*formatto i tag*/
           ArrayList<String> St= new ArrayList<>();
           for (Tag i : t) { 
               St.add(i.name());
           }
           String tag= formattaOut(St);
           
           
      String query = String.format("UPDATE into %s(email,name,surname,number,tag,email_contact,id) values('%s','%s','%s','%s','%s','%s','%s'); WHERE email='%s'",tableName,  email_Utente,nm,srn,number,tag,email,ID);
      
      statment= conn.createStatement();
      statment.executeUpdate(query);
      System.out.println("Contatto modificato");
    }
      /**
    * @lang it
    * @brief Elimina il contatto (riconosciuto per ID) associato a un utente specifico nel Database.
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella contenente i contatti.
    * @param ID il contatto da modificare nel Database
    * 
    * @throws SQLException Se si verifica un errore durante l'interrogazione.
    * @lang en
    * 
    * @brief Deletes the contact (identified by ID) associated with a specific user in the database.
    * 
    * @param conn Connection object to interact with the database.
    * @param tableName Name of the table containing the contacts.
    * @param ID The contact to be deleted in the database.
    * 
    * @throws SQLException If an error occurs during the query.
    */

    public void RemuveContactByID(Connection conn, String tableName, String ID) throws SQLException{
    
       Statement statment;
       
       String query= String.format("delete from %s where ID='%s'",tableName,ID);
       statment= conn.createStatement();
       statment.execute(query);
       System.out.print("\n Dato eliminato per ID");
    }
    /**
    * @lang it
    * @brief Chiude la connessione col database
    * @param conn Oggetto Connection per interagire con il database.
    * 
    * @throws SQLException Se si verifica un errore durante l'interrogazione.
    * @lang en
    * 
    * @brief Closes the connection to the database.
    * 
    * @param conn Connection object to interact with the database.
    * 
    * @throws SQLException If an error occurs during the query.
    */

    
    public void CloseConnection(Connection conn) throws SQLException{
        conn.close();
    }
}
