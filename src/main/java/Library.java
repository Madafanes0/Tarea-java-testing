import java.time.LocalDate;  
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List; 
  
public class Library {  
    private List<Book> books;  
    private List<Patron> patrons;  
    private HashMap<Patron, Book> checkedOutBooks;  
  
    public Library() {  
        this.books = new ArrayList<>();  
        this.patrons = new ArrayList<>();  
        this.checkedOutBooks = new HashMap<>();  
    }  
  
    public void addBook(Book book) {  
        if (!books.contains(book)) {  
            books.add(book);  
        }  
    }  
  
    public void removeBook(Book book) {  
        books.remove(book);  
    }  
  
    public void addPatron(Patron patron) {  
        if (!patrons.contains(patron)) {  
            patrons.add(patron);  
        }  
    }  
  
    public void removePatron(Patron patron) {  
        patrons.remove(patron);  
    }  
  
    public boolean checkOutBook(Patron patron, Book book, int daysToDue) {  
        if (books.contains(book) && !book.isCheckedOut() && patrons.contains(patron)) {  
            book.checkOut(daysToDue);  
            patron.checkOutBook(book);  
            checkedOutBooks.put(patron, book);  
            return true;  
        }  
        return false;  
    }  
  
    public boolean returnBook(Patron patron) {  
        Book book = checkedOutBooks.get(patron);  
        if (book != null) {  
            book.returnBook();  
            patron.returnBook(book);  
            checkedOutBooks.remove(patron);  
            return true;  
        }  
        return false;  
    }  
  
    public double calculateFine(Patron patron) {  
        Book book = checkedOutBooks.get(patron);  
        if (book != null && book.isOverdue()) {  
            long daysOverdue = (System.currentTimeMillis() - book.getDueDate()) / (24 * 60 * 60 * 1000);  
            return daysOverdue * 0.5; // $0.50 per day  
        }  
        return 0.0;  
    }  
  
    public List<Book> listAvailableBooks() {  
        List<Book> availableBooks = new ArrayList<>();  
        for (Book book : books) {  
            if (!book.isCheckedOut()) {  
                availableBooks.add(book);  
            }  
        }  
        return availableBooks;  
    }  
  
    public List<Patron> listPatrons() {  
        return new ArrayList<>(patrons);  
    }  
}  
