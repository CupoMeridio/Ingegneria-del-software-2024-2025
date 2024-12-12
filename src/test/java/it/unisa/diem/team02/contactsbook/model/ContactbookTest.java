import it.unisa.diem.team02.contactsbook.model.Contact;
import it.unisa.diem.team02.contactsbook.model.Contactbook;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;

public class ContactbookTest {
    private Contactbook contactbook;
    private Contact contact1;
    private Contact contact2;

    @BeforeEach
    public void setUp() {
        contactbook = new Contactbook();
        contact1 = new Contact("Valeria", "Quaranta");
        contact2 = new Contact("Mario", "Rossi");
    }

    @Test
    public void testAddContact() {
        contactbook.add(contact1);
        ObservableList<Contact> contacts = contactbook.getContacts();
        assertTrue(contacts.contains(contact1));
    }

    @Test
    public void testDeleteContact() {
        contactbook.add(contact1);
        contactbook.delete(contact1);
        ObservableList<Contact> contacts = contactbook.getContacts();
        assertFalse(contacts.contains(contact1));
    }

    @Test
    public void testContainsContact() {
        contactbook.add(contact1);
        assertTrue(contactbook.contains(contact1));
        assertFalse(contactbook.contains(contact2));
    }

    @Test
    public void testContainsContactWithOldContact() {
        contactbook.add(contact1);
        contactbook.add(contact2);
        Contact newContact = new Contact("Mario", "Rossi");
        assertFalse(contactbook.contains(newContact, contact2));
        assertTrue(contactbook.contains(newContact, contact1));
    }

    @Test
    public void testSalvaSuFile() throws IOException {
        contactbook.add(contact1);
        File file = new File("contacts.csv");
        contactbook.salvaSuFile(file);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testCaricaDaFile() throws IOException {
        File file = new File("contacts.csv");
        contactbook.add(contact1);
        contactbook.salvaSuFile(file);

        Contactbook newContactbook = new Contactbook();
        newContactbook.caricaDaFile(file);
        ObservableList<Contact> contacts = newContactbook.getContacts();
        assertTrue(contacts.contains(contact1));
        file.delete();
    }
}
