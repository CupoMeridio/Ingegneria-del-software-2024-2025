/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.model;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author team02
 */
public class ContactbookTest {

    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getContacts method, of class Contactbook.
     */
    @Test
    public void testGetContacts() {
        System.out.println("getContacts");
        Contactbook instance = new Contactbook();
        ObservableList<Contact> expResult = null;
        ObservableList<Contact> result = instance.getContacts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of delete method, of class Contactbook.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Contact c = null;
        Contactbook instance = new Contactbook();
        instance.delete(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Contactbook.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Contact c = null;
        Contactbook instance = new Contactbook();
        instance.add(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class Contactbook.
     */
    @Test
    public void testContains_Contact() {
        System.out.println("contains");
        Contact c = null;
        Contactbook instance = new Contactbook();
        boolean expResult = false;
        boolean result = instance.contains(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class Contactbook.
     */
    @Test
    public void testContains_Contact_Contact() {
        System.out.println("contains");
        Contact newC = null;
        Contact oldC = null;
        Contactbook instance = new Contactbook();
        boolean expResult = false;
        boolean result = instance.contains(newC, oldC);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Contactbook.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Contact c = null;
        Contactbook instance = new Contactbook();
        boolean expResult = false;
        boolean result = instance.remove(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeList method, of class Contactbook.
     */
    @Test
    public void testInitializeList() {
        System.out.println("initializeList");
        Contactbook instance = new Contactbook();
        instance.initializeList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
