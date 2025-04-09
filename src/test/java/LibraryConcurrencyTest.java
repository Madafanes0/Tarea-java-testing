import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LibraryConcurrencyTest {
    private Library library;
    private List<Book> books;
    private List<Patron> patrons;

    @Before
    public void setUp() {
        library = new Library();
        books = new ArrayList<>();
        patrons = new ArrayList<>();

        Book book1 = new Book("Harry Potter", "J.K. Rowling");
        Book book2 = new Book("El Señor de los Anillos", "J.R.R. Tolkien");
        books.add(book1);
        books.add(book2);
        library.addBook(book1);
        library.addBook(book2);

        Patron patron1 = new Patron("Juan");
        Patron patron2 = new Patron("María");
        patrons.add(patron1);
        patrons.add(patron2);
        library.addPatron(patron1);
        library.addPatron(patron2);
    }

    @Test
    public void testPrestamoSimultaneo() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        executor.submit(() -> library.checkOutBook(patrons.get(0), books.get(0), 7));
        executor.submit(() -> library.checkOutBook(patrons.get(1), books.get(0), 7));

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        assertTrue(patrons.get(0).hasCheckedOutBook(books.get(0)) ^ 
                  patrons.get(1).hasCheckedOutBook(books.get(0)));
    }

    @Test
    public void testDevolucionSimultanea() throws InterruptedException {
        library.checkOutBook(patrons.get(0), books.get(0), 7);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        executor.submit(() -> library.returnBook(patrons.get(0)));
        executor.submit(() -> library.returnBook(patrons.get(0)));

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        assertFalse(patrons.get(0).hasCheckedOutBook(books.get(0)));
    }
} 