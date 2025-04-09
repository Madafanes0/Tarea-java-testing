# Library Management System with Unit Testing

Este proyecto implementa un sistema de gestión de biblioteca con pruebas unitarias utilizando JUnit 4.

## Estructura del Proyecto

```
LibraryManagementUnitTesting/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Book.java
│   │       ├── Library.java
│   │       └── Patron.java
│   └── test/
│       └── java/
│           └── LibraryTest.java
├── pom.xml
└── README.md
```

## Requisitos Previos

- Java JDK 11 o superior
- Maven 3.6 o superior
- Git (opcional, para clonar el repositorio)

## Instrucciones para Ejecutar

1. **Clonar el Repositorio** (opcional):
   ```bash
   git clone https://github.com/Madafanes0/Tarea-java-testing.git
   cd Tarea-java-testing
   ```

2. **Compilar el Proyecto**:
   ```bash
   mvn clean compile
   ```

3. **Ejecutar los Tests**:
   ```bash
   mvn test
   ```

## Descripción de las Clases

### Book.java
Representa un libro en la biblioteca con atributos como título, autor y estado de préstamo.

### Library.java
Implementa la lógica principal de la biblioteca, incluyendo:
- Agregar/remover libros
- Registrar/remover usuarios
- Prestar libros
- Devolver libros
- Calcular multas

### Patron.java
Representa un usuario de la biblioteca con su lista de libros prestados.

## Tests Implementados

Los tests cubren las siguientes funcionalidades:
- Agregar libros a la biblioteca
- Prestar libros a usuarios
- Devolver libros
- Calcular multas por retraso
- Manejo de múltiples usuarios
- Casos de error (libros inexistentes, etc.)

## Ejemplo de Uso

```java
// Crear una biblioteca
Library library = new Library();

// Crear libros
Book book1 = new Book("Harry Potter", "J.K. Rowling");
Book book2 = new Book("El Señor de los Anillos", "J.R.R. Tolkien");

// Crear usuarios
Patron patron1 = new Patron("Juan Pérez");
Patron patron2 = new Patron("María García");

// Agregar libros a la biblioteca
library.addBook(book1);
library.addBook(book2);

// Registrar usuarios
library.addPatron(patron1);
library.addPatron(patron2);

// Prestar un libro
library.checkOutBook(patron1, book1, 7); // 7 días de préstamo

// Devolver un libro
library.returnBook(patron1);

// Calcular multa (si hay retraso)
double fine = library.calculateFine(patron1);
```

## Contribuciones

Las contribuciones son bienvenidas. Por favor, asegúrate de:
1. Seguir el estilo de código existente
2. Incluir pruebas unitarias para nuevas funcionalidades
3. Actualizar la documentación según sea necesario

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo LICENSE para más detalles.