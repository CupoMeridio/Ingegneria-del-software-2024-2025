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
        
        contactTest2.addTag(Tag.Home);
        contactTest2.addTag(Tag.University);
        contactTest2.addTag(Tag.Job);
        contactTest3.addTag(Tag.Job);
        
        
        //aggiunta contatti alla lista
        listTest.add(contactTest1);
        listTest.add(contactTest2);
        listTest.add(contactTest3);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    
    /**
     * Test of the constructor and get method
     * 
     */
    @Test
    public void testConstructor() {
        System.out.println("Filter constructor");
        flContactTest = new Filter (listTest);
        assertEquals(3,flContactTest.getFlContacts().size(),"La lista filtrata deve contenere tutti gli elementi iniziali");
        assertFalse(flContactTest.getIsSelectedHome(), "Il valore iniziale di isSelectedHome deve essere false");
        assertFalse(flContactTest.getIsSelectedUniversity(),"Il valore iniziale di isSelectedUni deve essere false");
        assertFalse(flContactTest.getIsSelectedJob(),"Il valore iniziale di isSelectedJob deve essere false");

    }
    

    /**
     * Test n1 of updateFilter method, of class Filter. Update only by substring
     */
    @Test
    public void testUpdateFilter1() {
        System.out.println("updateFilter");
        flContactTest = new Filter(listTest);
        String string = "Zou";
        boolean h = false;
        boolean u = false;
        boolean j = false;
        flContactTest.updateFilter(string, h, u, j);
        
        assertEquals(1,flContactTest.getFlContacts().size());
        
    }
    
    /**
     * Test n2 of updateFilter method, of class Filter. Update only by tag
     */
    @Test
    public void testUpdateFilter2() {
        System.out.println("updateFilter");
        flContactTest = new Filter(listTest);
        String string = "";
        boolean h = false;
        boolean u = false;
        boolean j = true;
        flContactTest.updateFilter(string, h, u, j);
        
        assertEquals(2,flContactTest.getFlContacts().size());
        
    }
    
    /**
     * Test n3 of updateFilter method, of class Filter. No filter selected
     */
    @Test
    public void testUpdateFilter3() {
        System.out.println("updateFilter");
        flContactTest = new Filter(listTest);
        String string = "";
        boolean h = false;
        boolean u = false;
        boolean j = false;
        flContactTest.updateFilter(string, h, u, j);
        
        assertEquals(3,flContactTest.getFlContacts().size());
    }
    
    /**
     * Test n4 of updateFilter method, of class Filter.
     */
    @Test
    public void testUpdateFilter4() {
        System.out.println("updateFilter");
        flContactTest = new Filter(listTest);
        String string = "an";
        boolean h = true;
        boolean u = false;
        boolean j = true;
        flContactTest.updateFilter(string, h, u, j);
        
        assertEquals(2,flContactTest.getFlContacts().size());
        
    }
    
}