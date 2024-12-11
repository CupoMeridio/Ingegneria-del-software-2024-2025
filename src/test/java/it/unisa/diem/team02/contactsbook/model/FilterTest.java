/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author team02
 */
public class FilterTest {
    Filter flContactTest;
    ObservableList<Contact> listTest; 
    Contact contactTest1, contactTest2, contactTest3;
    
    @BeforeEach
    public void setUp() {
        listTest = FXCollections.observableArrayList();
        
        //inizializzazione oggetti Contact
        contactTest1 = new Contact("","Zouhri");
        contactTest2 = new Contact("Costantino","");
        contactTest3 = new Contact("Antonietta","Ferrara");
        
        contactTest1.addEmail("a.zouhri@gmail.com");
        contactTest2.addNumber("57891011");
        contactTest3.addEmail("yoga@mystress.it");
        contactTest3.addEmail("english@geometry4.it");
        
        //aggiunta contatti alla lista
        listTest.add(contactTest1);
        listTest.add(contactTest2);
        listTest.add(contactTest3);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    public void testConstructor1() {
        System.out.println("Filter constructor");
        flContactTest = new Filter (listTest);
        assertEquals(3,flContactTest.getFlContacts().size(),"La lista filtrata deve contenere tutti gli elementi iniziali");
        assertFalse(flContactTest.getIsSelectedHome(), "Il valore iniziale di isSelectedHome deve essere false");
        assertFalse(flContactTest.getIsSelectedUniversity(),"Il valore iniziale di isSelectedUni deve essere false");
        assertFalse(flContactTest.getIsSelectedJob(),"Il valore iniziale di isSelectedJob deve essere false");
        
    
    }
    
    
    
    /**
     * Test of getFlContacts method, of class Filter.
     */
    @Test
    public void testGetFlContacts() {
        System.out.println("getFlContacts");
        Filter instance = null;
        FilteredList<Contact> expResult = null;
        FilteredList<Contact> result = instance.getFlContacts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateFilter method, of class Filter.
     */
    @Test
    public void testUpdateFilter() {
        System.out.println("updateFilter");
        String string = "";
        boolean h = false;
        boolean u = false;
        boolean j = false;
        Filter instance = null;
        instance.updateFilter(string, h, u, j);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
