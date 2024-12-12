package it.unisa.diem.team02.contactsbook.model;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author team02
 */
public class ContactTest {
    private Contact Test1, Test2, Test3;
    ArrayList<String> number1, number2;
    ArrayList<String> email1, email2;
    ArrayList<Tag> tag1, tag2;
    
    @BeforeEach
    public void setUp() {
        
        //inizializzazione dei contatti
        Test1 = new Contact("Anuar", "Zouhri");
        Test2 = new Contact("Valeria","");
        Test3 = new Contact("","Postiglione","10");
        
        //inizializzazione della lista di numeri number1 con i numeri 3393434025 e ""
        number1 = new ArrayList<>(3);
        number1.add("3393434025");
        number1.add("");
        
        //inizializzazione della lista di numeri number2 con i numeri 333173282, "" e 3349891345
        number2 = new ArrayList<>(3);
        number2.add("333173282");
        number2.add("");
        number2.add("3349891345");
        
        //instanziazione della lista di email email1 con nessuna email
        email1 = new ArrayList<>(3);
        
        //inizializzazione della lista di email email2 con l'email 337897345
        email2 = new ArrayList<>(3);
        email2.add("337897345");
        
        //inizializzazione della lista dei tag tag1 con i tag Home e Job
        tag1 = new ArrayList<>(3);
        tag1.add(Tag.Home);
        tag1.add(Tag.Job);
        
        //inizializzazione della lista di tag tag2 con il tag University
        tag2 = new ArrayList<>(3);
        tag2.add(Tag.University);
        
    }
    
    @AfterEach
    public void tearDown() {
        Test1=null;
        Test2=null;
        Test3=null;
       
    }

    /**
     * Test n1 dei metodi addNumber e getNumber della classe Contact. L'oggetto ha un numero.
     */
    @Test
    public void testAddAndGetNumber1() {
        Test1.addNumber("3393424025");
        String expResult = "3393424025";
        String result = Test1.getNumber();
        assertEquals(expResult, result);
    }
    
    /**
     * Test n2 dei metodi addNumber e getNumber della classe Contact. L'oggetto ha due numeri.
     */
    @Test
    public void testAddAndGetNumber2() {
        Test2.addNumber("3393424025");
        Test2.addNumber("2238123");
        String expResult = "3393424025\n2238123";
        String result = Test2.getNumber();
        assertEquals(expResult, result);
    }
    

    /**
     * Test n1 dei metodi addEmail e getEmail method della classe Contact. L'oggetto ha tre email.
     */
    @Test
    public void testAddAndGetEmail1() {
        Test3.addEmail("vittorio@gmail.it");
        Test3.addEmail("vittorio@gmail.it");
        Test3.addEmail("vitt@gmail.com");
        String expResult = "vittorio@gmail.it\nvittorio@gmail.it\nvitt@gmail.com";
        String result = Test3.getEmail();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test n2 dei metodi addEmail e getEmail method della classe Contact.
     */
    @Test
    public void testAddAndGetEmail2() {
        Test2.addEmail("");
        String expResult = "";
        String result = Test3.getEmail();
        assertEquals(expResult, result);
          
    }

    /**
     * Test n1 dei metodi addTag e getTag della classe Contact. L'oggetto ha due tag
     */
    @Test
    public void testAddAndGetTag1() {
        Test1.addTag(Tag.Job);
        Test1.addTag(Tag.University);
        String expResult = "Job\nUniversity";
        String result = Test1.getTag();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test n2 dei metodi addTag e getTag della classe Contact. L'oggetto ha tre tag
     */
    @Test
    public void testAddAndGetTag2() {
        Test1.addTag(Tag.Home);
        Test1.addTag(Tag.Job);
        Test1.addTag(Tag.University);
        String expResult = "Home\nJob\nUniversity";
        String result = Test1.getTag();
        assertEquals(expResult, result);        
    }

    /**
     * Test n1 dei metodi setNumber e getNumberList della classe Contact. 
     */
    @Test
    public void testSetAndGetNumberList1() {
        Test1.setNumber(number1);
        assertEquals(number1, Test1.getNumberList()); 
        
    }
    
    /**
     * Test n2 dei metodi setNumber e getNumberList della classe Contact. 
     */
    @Test
    public void testSetAndGetNumberList2() {
        Test2.setNumber(number2);
        assertEquals(number2, Test2.getNumberList()); 
        
    }

    /**
     * Test n1 dei metodi setEmail e getEmailList della classe Contact.
     */
    @Test
    public void testSetAndGetEmailList1() {
        Test2.setEmail(email1);
        assertEquals(email1, Test2.getEmailList()); 
    }
    
    /**
     * Test n2 dei metodi setEmail e getEmailList della classe Contact.
     */
    @Test
    public void testSetAndGetEmailList2() {
        Test3.setEmail(email2);
        assertEquals(email2, Test3.getEmailList()); 
    }

    /**
     * Test n1 dei metodi setTag ae getTagList della classe Contact.
     */
    @Test
    public void testSetAndGetTagList1() {
        Test1.setTag(tag1);
        assertEquals(tag1, Test1.getTagList()); 
    }
    
    /**
     * Test n2 dei metodi setTag ae getTagList della classe Contact.
     */
    @Test
    public void testSetAndGetTagList2() {
        Test2.setTag(tag2);
        assertEquals(tag2, Test2.getTagList()); 
    }
    
    
    /**
     * 
     * Test n1 del metodo equals. Il  The metodo è utilizzato su due oggetti con diverso nome e cognome. 
     * Viene utilizzato assertFalse.
     *
     */
    @Test
    public void testEquals1() {
        assertFalse(Test1.equals(Test2));
    
    }
    
    /**
     * 
     * Test n2 del metodo equal. Il metodo è utilizzato su due contatti che hanno lo stesso nome
     * e cognome. Viene utilizzato AssertTrue
     */
    @Test
    public void testEquals2() {
        Contact c = new Contact(Test1.getName(),Test1.getSurname());
        assertTrue(Test1.equals(c));
    
    }
    
    /**
     * 
     * Test n3 del metodo equal. Il metodo è utilizzato su due contatti che hanno lo stesso nome
     * ma cognome diverso. Viene utilizzato AssertFalse
     */
    @Test
    public void testEquals3() {
        Contact c = new Contact(Test1.getName(),Test2.getSurname());
        assertFalse(Test1.equals(c));
    
    }
    
    /**
     * 
     * Test n4 del metodo equal. Il metodo è utilizzato su due contatti che hanno nome
     * diverso ma stesso cognome. Viene utilizzato AssertFalse
     */
    @Test
    public void testEquals4() {
        Contact c = new Contact(Test1.getName(),Test2.getSurname());
        assertFalse(Test2.equals(c));
    
    }
    


   
}
