import java.time.LocalDate;  
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;

public class Library {  
    private final List<Book> books;  
    private final List<Patron> patrons;  
    private final Map<Book, Patron> checkedOutBooks;  
    private final Object booksLock = new Object();  
    private final Object patronsLock = new Object();  
    private final Object checkedOutBooksLock = new Object();
  
    public Library() {  
        this.books = new ArrayList<>();  
        this.patrons = new ArrayList<>();  
        this.checkedOutBooks = new HashMap<>();  
    }  
  
    public void addBook(Book book) {  
        synchronized (booksLock) {  
            if (!books.contains(book)) {  
                books.add(book);  
            }  
        }  
    }  
  
    public void removeBook(Book book) {  
        synchronized (booksLock) {  
            books.remove(book);  
        }  
    }  
  
    public void addPatron(Patron patron) {  
        synchronized (patronsLock) {  
            if (!patrons.contains(patron)) {  
                patrons.add(patron);  
            }  
        }  
    }  
  
    public void removePatron(Patron patron) {  
        synchronized (patronsLock) {  
            patrons.remove(patron);  
        }  
    }  
  
    public boolean checkOutBook(Patron patron, Book book, int days) {  
        synchronized (checkedOutBooksLock) {  
            if (!patrons.contains(patron)) {  
                return false;  
            }  
            if (!books.contains(book)) {  
                return false;  
            }  
            if (checkedOutBooks.containsKey(book)) {  
                return false;  
            }  
            if (book.isCheckedOut()) {  
                return false;  
            }  
            
            book.checkOut(days);  
            patron.checkOutBook(book);  
            checkedOutBooks.put(book, patron);  
            return true;  
        }  
    }  
  
    public boolean returnBook(Patron patron) {  
        synchronized (checkedOutBooksLock) {  
            List<Book> patronBooks = patron.getCheckedOutBooks();  
            if (patronBooks.isEmpty()) {  
                return false;  
            }  
            
            Book book = patronBooks.get(0);
            book.returnBook();  
            patron.returnBook(book);  
            checkedOutBooks.remove(book);  
            return true;  
        }  
    }  
  
    public double calculateFine(Patron patron) {  
        synchronized (checkedOutBooksLock) {  
            List<Book> patronBooks = patron.getCheckedOutBooks();  
            if (patronBooks.isEmpty()) {  
                return 0.0;  
            }  
            
            Book book = patronBooks.get(0);  
            if (checkedOutBooks.containsKey(book) && book.isOverdue()) {  
                long daysOverdue = (System.currentTimeMillis() - book.getDueDate()) / (24 * 60 * 60 * 1000);  
                return Math.max(0, daysOverdue * 0.50);  
            }  
            return 0.0;  
        }  
    }  
  
    public List<Book> getBooks() {  
        synchronized (booksLock) {  
            return new ArrayList<>(books);  
        }  
    }  
  
    public List<Patron> getPatrons() {  
        synchronized (patronsLock) {  
            return new ArrayList<>(patrons);  
        }  
    }  
  
    public Map<Book, Patron> getCheckedOutBooks() {  
        synchronized (checkedOutBooksLock) {  
            return new HashMap<>(checkedOutBooks);  
        }  
    }  

    public List<Book> listAvailableBooks() {
        synchronized (booksLock) {
            List<Book> availableBooks = new ArrayList<>();
            for (Book book : books) {
                if (!book.isCheckedOut()) {
                    availableBooks.add(book);
                }
            }
            return availableBooks;
        }
    }

    public List<Patron> listPatrons() {
        synchronized (patronsLock) {
            return new ArrayList<>(patrons);
        }
    }
}  
