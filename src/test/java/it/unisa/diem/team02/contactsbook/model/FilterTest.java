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
    Filter flContacts;
    
    @BeforeEach
    public void setUp() {
        ObservableList<Contact> listTest = FXCollections.observableArrayList();
        
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    public void testConstructor1() {
        System.out.println("FIlter constructor");
        
    
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
