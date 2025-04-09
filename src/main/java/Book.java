import java.time.LocalDate;  
  
public class Book {  
    private String title;  
    private String author;  
    private boolean isCheckedOut;  
    private long dueDate;  
  
    public Book(String title, String author) {  
        this.title = title;  
        this.author = author;  
        this.isCheckedOut = false;  
        this.dueDate = 0;  
    }  
  
    public String getTitle() {  
        return title;  
    }  
  
    public String getAuthor() {  
        return author;  
    }  
  
    public boolean isCheckedOut() {  
        return isCheckedOut;  
    }  
  
    public long getDueDate() {  
        return dueDate;  
    }  
  
    public void checkOut(int daysToDue) {  
        this.isCheckedOut = true;  
        this.dueDate = System.currentTimeMillis() + (daysToDue * 24 * 60 * 60 * 1000);  
    }  
  
    public void returnBook() {  
        this.isCheckedOut = false;  
        this.dueDate = 0;  
    }  

    public void setDueDate(long dueDate) {  
        if (isCheckedOut) {  
            this.dueDate = dueDate;  
        } else {  
            throw new IllegalStateException("Cannot set due date for a book that is not checked out.");  
        }  
    }  

    public boolean isOverdue() {  
        return isCheckedOut && System.currentTimeMillis() > dueDate;  
    }  

    @Override  
    public boolean equals(Object obj) {  
        if (this == obj) return true;  
        if (obj == null || getClass() != obj.getClass()) return false;  
        Book book = (Book) obj;  
        return title.equals(book.title) && author.equals(book.author);  
    }  

    @Override  
    public int hashCode() {  
        return title.hashCode() + author.hashCode();  
    }  
}  