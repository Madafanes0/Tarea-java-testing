import java.util.ArrayList;  
import java.util.List;  
  
public class Patron {  
    private String name;  
    private List<Book> checkedOutBooks;  
  
    public Patron(String name) {  
        this.name = name;  
        this.checkedOutBooks = new ArrayList<>();  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void checkOutBook(Book book) {  
        if (!checkedOutBooks.contains(book)) {  
            checkedOutBooks.add(book);  
        }  
    }  
  
    public void returnBook(Book book) {  
        checkedOutBooks.remove(book);  
    }  
  
    public boolean hasCheckedOutBook(Book book) {  
        return checkedOutBooks.contains(book);  
    }  
  
    public List<Book> getCheckedOutBooks() {  
        return checkedOutBooks;  
    }  
  
    @Override  
    public boolean equals(Object obj) {  
        if (this == obj) return true;  
        if (obj == null || getClass() != obj.getClass()) return false;  
        Patron patron = (Patron) obj;  
        return name.equals(patron.name);  
    }  
  
    @Override  
    public int hashCode() {  
        return name.hashCode();  
    }  
}  