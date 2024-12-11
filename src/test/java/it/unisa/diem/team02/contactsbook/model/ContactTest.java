/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
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
 * @author anuar
 */
public class ContactTest {
    private Contact Test1, Test2, Test3, Test4;
    ArrayList<String> number1, number2;
    ArrayList<String> email1, email2;
    ArrayList<Tag> tag1, tag2;
    
    @BeforeEach
    public void setUp() {
        
        //inizializzazione dei contatti
        Test1 = new Contact("Anuar", "Zouhri");
        Test2 = new Contact("Valeria","");
        Test3 = new Contact("","Postiglione","10");
        Test4 = new Contact("Anuar","Zouhri");
        
        //inizializzazione della lista di numeri number1 con il numero 3393434025
        number1 = new ArrayList<>(3);
        number1.add("3393434025");
        
        //inizializzazione della lista di numeri number2 con i numeri 333173282 e 3349891345
        number2 = new ArrayList<>(3);
        number2.add("333173282");
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
     * Test n1 of addNumber and getNumber method, of class Contact. The object has a single number 
     */
    @Test
    public void testAddAndGetNumber1() {
        System.out.println("addNumber and getNumber");
        Test1.addNumber("3393424025");
        String expResult = "3393424025";
        String result = Test1.getNumber();
        assertEquals(expResult, result);
    }
    
    /**
     * Test n2 of addNumber and getNumber method, of class Contact. The object has two numbers
     */
    @Test
    public void testAddAndGetNumber2() {
        System.out.println("addNumber and getNumber");
        Test2.addNumber("3393424025");
        Test2.addNumber("2238123");
        String expResult = "3393424025\n2238123";
        String result = Test2.getNumber();
        assertEquals(expResult, result);
    }
    

    /**
     * Test n1 of addEmail and getEmail method, of class Contact. The object has three emails
     */
    @Test
    public void testAddAndGetEmail1() {
        System.out.println("addEmail and getEmail");
        Test3.addEmail("vittorio@gmail.it");
        Test3.addEmail("vittorio@gmail.it");
        Test3.addEmail("vitt@gmail.com");
        String expResult = "vittorio@gmail.it\nvittorio@gmail.it\nvitt@gmail.com";
        String result = Test3.getEmail();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test n2 of addEmail and getEmail method, of class Contact.
     */
    @Test
    public void testAddAndGetEmail2() {
        System.out.println("addEmail and getEmail");
        Test2.addEmail("");
        String expResult = "";
        String result = Test3.getEmail();
        assertEquals(expResult, result);
          
    }

    /**
     * Test n1 of addTag and getTag method, of class Contact. The object has two tags
     */
    @Test
    public void testAddAndGetTag1() {
        System.out.println("addTag and getTag");
        Test1.addTag(Tag.Job);
        Test1.addTag(Tag.University);
        String expResult = "Job\nUniversity";
        String result = Test1.getTag();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test n2 of addTag and getTag method, of class Contact. The object has three tags
     */
    @Test
    public void testAddAndGetTag2() {
        System.out.println("addTag and getTag");
        Test1.addTag(Tag.Home);
        Test1.addTag(Tag.Job);
        Test1.addTag(Tag.University);
        String expResult = "Home\nJob\nUniversity";
        String result = Test1.getTag();
        assertEquals(expResult, result);        
    }

    /**
     * Test n1 of setNumber and getNumberList method, of class Contact. 
     */
    @Test
    public void testSetAndGetNumberList1() {
        System.out.println("setNumber and getNumberList ");
        Test1.setNumber(number1);
        assertEquals(number1, Test1.getNumberList()); 
        
    }
    
    /**
     * Test n2 of setNumber and getNumberList method, of class Contact. 
     */
    @Test
    public void testSetAndGetNumberList2() {
        System.out.println("setNumber and getNumberList ");
        Test2.setNumber(number2);
        assertEquals(number2, Test2.getNumberList()); 
        
    }

    /**
     * Test n1 of setEmail and getEmailList method, of class Contact.
     */
    @Test
    public void testSetAndGetEmailList1() {
        System.out.println("setEmail and getEmailList");
        Test2.setEmail(email1);
        assertEquals(email1, Test2.getEmailList()); 
    }
    
    /**
     * Test n2 of setEmail and getEmailList method, of class Contact.
     */
    @Test
    public void testSetAndGetEmailList2() {
        System.out.println("setEmail and getEmailList");
        Test3.setEmail(email2);
        assertEquals(email2, Test3.getEmailList()); 
    }

    /**
     * Test n1 of setTag and getTagList method, of class Contact.
     */
    @Test
    public void testSetAndGetTagList1() {
        System.out.println("setTag and getTagList");
        Test1.setTag(tag1);
        assertEquals(tag1, Test1.getTagList()); 
    }
    
    /**
     * Test n2 of setTag and getTagList method, of class Contact.
     */
    @Test
    public void testSetAndGetTagList2() {
        System.out.println("setTag and getTagList");
        Test2.setTag(tag2);
        assertEquals(tag2, Test2.getTagList()); 
    }


    /**
     * Test of getRole method, of class Contact.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        assertEquals("Contact",Test1.getRole());
    }
    
    
    /**
     * 
     * Test n1 of equals method, of class Person inherited by Contact. The test apply
     * the equals method on two objects with different name and surname. It is
     * applied assertFalse
     *
     */
    @Test
    public void testEquals1() {
        System.out.println("equals");
        assertFalse(Test1.equals(Test2));
    
    }
    
    /**
     * 
     * Test n2 of equals method, of class Person inherited by Contact. The test apply
     * the equals method on two objects with the same name and surname. It is applied
     * asserTrue
     *
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        assertTrue(Test1.equals(Test4));
    
    }


   
}
