# Library Management System

A Java-based library management system that supports concurrent access by multiple patrons.

## Features

- Book management (add, remove, check availability)
- Patron management (add, remove)
- Book checkout and return operations
- Fine calculation for overdue books
- Thread-safe operations for concurrent access
- Comprehensive unit testing

## Thread Safety

The library system is designed to handle concurrent access by multiple patrons. Key features include:

- Synchronized access to shared resources (books, patrons, and checkout records)
- Thread-safe book checkout and return operations
- Consistent state management under heavy concurrent load

The system uses fine-grained locking to ensure thread safety while maintaining good performance. Each major resource (books, patrons, and checkout records) has its own lock, allowing for better concurrency than a single global lock.

## Testing

The system includes comprehensive unit tests, including:

- Basic functionality tests
- Thread safety tests
- Concurrent operation tests

To run the tests:

```bash
mvn test
```

## Dependencies

- JUnit 4.13.2
- Maven

## Building

```bash
mvn clean install
```

## Usage

```java
// Create a library
Library library = new Library();

// Add books
Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald");
library.addBook(book);

// Add patrons
Patron patron = new Patron("John Doe");
library.addPatron(patron);

// Check out a book
library.checkOutBook(patron, book, 14); // 14 days loan period

// Return a book
library.returnBook(patron);

// Calculate fines
double fine = library.calculateFine(patron);
```

## Thread Safety Considerations

When using the library system in a multithreaded environment:

1. The system automatically handles concurrent access to shared resources
2. Book checkout and return operations are atomic
3. The system maintains consistency even under heavy concurrent load
4. Fine-grained locking ensures good performance with multiple concurrent users

## License

MIT License