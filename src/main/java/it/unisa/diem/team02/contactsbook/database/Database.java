/**
 * @brief Gestisce la connessione al database e le operazioni CRUD relative agli utenti e ai contatti.
 * 
 * Questa classe fornisce metodi per connettersi a un database PostgreSQL, inserire e recuperare utenti,
 * verificare il login e ottenere contatti. Utilizza la libreria BCrypt per la gestione sicura delle password.
 * 
 */
package it.unisa.diem.team02.contactsbook.database;
import it.unisa.diem.team02.contactsbook.model.Contact;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author cupom
 */
public class Database {
        
/**
 * @brief Stabilisce una connessione al database PostgreSQL.
 * 
 * @param dbname Nome del database a cui connettersi.
 * @param user Nome utente per l'autenticazione.
 * @param password Password per l'autenticazione.
 * @return Oggetto Connection se la connessione è riuscita, altrimenti null.
 * @throws SQLException Se si verifica un errore durante la connessione.
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
    * @brief Inserisce un nuovo utente nella tabella specificata.
    * 
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella in cui inserire l'utente.
    * @param email Email dell'utente.
    * @param password Password dell'utente (verrà hashata prima dell'inserimento).
    * @throws SQLException Se si verifica un errore durante l'inserimento.
    */
    public void insertUtente(Connection conn, String tableName, String email, String password) throws SQLException{
    
           Statement statment;
           String pass= hashPassword(password);
           String query= String.format("insert into %s(email,password) values('%s','%s');",tableName, email, pass);
           statment= conn.createStatement();
           statment.executeUpdate(query);
           System.out.println("Utente inserito");
        
    }
    
    /**
    * @brief Recupera tutti gli utenti dal database.
    * 
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella da cui recuperare gli utenti.
    * @return HashMap contenente le coppie email-password degli utenti.
    */
    public HashMap<String, String> getUtenti(Connection conn, String tableName){
        
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
    * @brief Verifica le credenziali di login di un utente.
    * 
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella contenente gli utenti.
    * @param email Email dell'utente.
    * @param password Password inserita dall'utente.
    * @return 1 se le credenziali sono corrette, 0 se la password è errata, -1 se l'email non esiste.
    * @throws SQLException Se si verifica un errore durante l'interrogazione.
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
    
    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
        //per renderlo più sicuro conviene aggiungere un salt
    }

    private static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
    
    
    
    
    /**
    * @brief Recupera i contatti associati a un utente specifico.
    * 
    * @param conn Oggetto Connection per interagire con il database.
    * @param tableName Nome della tabella contenente i contatti.
    * @param email Email dell'utente di cui recuperare i contatti.
    * @return HashMap contenente i contatti dell'utente.
    */
     public HashMap<String, Contact> getContatti(Connection conn, String tableName,String email){
        
        Statement statement;
        ResultSet rs= null;
        Map <String,Contact>table=null;
        try {
            table =  new HashMap();
            String query= String.format("select * from %s where email='%s'", tableName,email);
            statement= conn.createStatement();
            rs= statement.executeQuery(query);
            while(rs.next()){
                String name = rs.getString("name");
                String surname= rs.getString("surname");
                String em = rs.getString("email");
                String numeri = rs.getString("number");
                String tag = rs.getString("tag");
                String em_cont = rs.getString("email_contact");
                table.put(em, createContact(name,surname,numeri,tag,em_cont));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return (HashMap<String, Contact>) table;
    }

    private Contact createContact(String name, String surname, String numeri, String tag, String em_cont) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
