import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Patron patron1;
    private Patron patron2;

    @Before
    public void setUp() {
        library = new Library();
        book1 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling");
        book2 = new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling");
        patron1 = new Patron("Li Chen");
        patron2 = new Patron("Diego Villa");
    }

    @Test
    public void testAddBook() {
        library.addBook(book1);
        assertTrue(library.listAvailableBooks().contains(book1));
    }

    @Test
    public void testAddDuplicateBook() {
        library.addBook(book1);
        library.addBook(book1);
        assertEquals(1, library.listAvailableBooks().size());
    }

    @Test
    public void testCheckOutBook() {
        library.addBook(book1);
        library.addPatron(patron1);
        assertTrue(library.checkOutBook(patron1, book1, 7));
        assertFalse(library.listAvailableBooks().contains(book1));
        assertTrue(patron1.hasCheckedOutBook(book1));
    }

    @Test
    public void testReturnBook() {
        library.addBook(book1);
        library.addPatron(patron1);
        library.checkOutBook(patron1, book1, 7);
        assertTrue(library.returnBook(patron1));
        assertTrue(library.listAvailableBooks().contains(book1));
        assertFalse(patron1.hasCheckedOutBook(book1));
    }

    @Test
    public void testCalculateFine() {
        library.addBook(book1);
        library.addPatron(patron1);
        library.checkOutBook(patron1, book1, 7);
        book1.setDueDate(System.currentTimeMillis() - (8 * 24 * 60 * 60 * 1000)); // 8 days overdue
        assertEquals(4.0, library.calculateFine(patron1), 0.01);
    }

    @Test
    public void testAddPatron() {
        library.addPatron(patron1);
        assertTrue(library.listPatrons().contains(patron1));
    }

    @Test
    public void testRemovePatron() {
        library.addPatron(patron1);
        library.removePatron(patron1);
        assertFalse(library.listPatrons().contains(patron1));
    }

    @Test
    public void testCheckOutNonexistentBook() {
        library.addPatron(patron1);
        assertFalse(library.checkOutBook(patron1, book1, 7));
    }

    @Test
    public void testMultiplePatrons() {
        library.addBook(book1);
        library.addBook(book2);
        library.addPatron(patron1);
        library.addPatron(patron2);
        
        library.checkOutBook(patron1, book1, 7);
        library.checkOutBook(patron2, book2, 7);
        
        assertTrue(patron1.hasCheckedOutBook(book1));
        assertTrue(patron2.hasCheckedOutBook(book2));
        assertFalse(patron1.hasCheckedOutBook(book2));
        assertFalse(patron2.hasCheckedOutBook(book1));
    }
}  
 