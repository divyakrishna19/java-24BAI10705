# 📚 Library Management System

**BYOP (Bring Your Own Project) — Java Course Capstone**
**Author:** Divya Krishna | **Reg. No.:** 24BAI10705
**Date:** 29 March 2026

---

## 📌 What is this project?

A fully object-oriented **Library Management System** built in Java that allows librarians to manage books and members, handle borrow/return transactions, perform searches, and generate statistics — all from a clean, well-structured codebase.

This project applies core Java concepts covered in the course:

| Concept | Where Used |
|---|---|
| OOP (Classes, Encapsulation) | `Book`, `Member`, `LibraryService` |
| Collections & Generics | `HashMap`, `List`, `Map` |
| Exception Handling | `try-catch`, custom error messages |
| Java Streams & Lambdas | Search, filter, statistics |
| Unit Testing (JUnit 5) | `LibraryServiceTest.java` |
| Maven Build System | `pom.xml` |

---

## 📁 Project Structure

```
library-management-system/
├── pom.xml
└── src/
    ├── main/java/com/library/
    │   ├── Book.java           # Book entity
    │   ├── Member.java         # Member entity
    │   ├── LibraryService.java # Core business logic
    │   └── Main.java           # Entry point / demo
    └── test/java/com/library/
        └── LibraryServiceTest.java  # JUnit 5 tests
```

---

## ⚙️ Prerequisites

- **Java 17+** — [Download](https://adoptium.net/)
- **Maven 3.8+** — [Download](https://maven.apache.org/download.cgi)

Verify installation:
```bash
java -version
mvn -version
```

---

## 🚀 How to Run

### 1. Clone the Repository
```bash
git clone https://github.com/<divyakrishna19>/library-management-system.git
cd library-management-system
```

### 2. Build the Project
```bash
mvn clean package
```

### 3. Run the Application
```bash
java -jar target/library-management-system-1.0.0.jar
```

---

## 🧪 Running Tests

```bash
mvn test
```

Expected output: **8 tests passed, 0 failed.**

---

## ✨ Features

- ➕ **Add / Remove Books** with ISBN, title, author, genre
- 👤 **Register Members** with unique member IDs
- 📖 **Borrow & Return** books with full validation
- 🔍 **Search** by title keyword or author name
- 📊 **Genre Statistics** using Java Streams
- ⚠️ **Exception Handling** for duplicate entries, unavailable books, missing IDs
- ✅ **Unit Tests** covering all major scenarios

---

## 💡 Sample Output

```
╔══════════════════════════════════════════╗
║    Library Management System — BYOP      ║
║    Divya Krishna | 24BAI10705             ║
╚══════════════════════════════════════════╝

✔ Book added: Clean Code
✔ Book added: Effective Java
...
✔ Member registered: Divya Krishna
...
✔ "Effective Java" borrowed by Divya Krishna
...
─── Genre Statistics ───
  Technology      : 5 book(s)
  Fiction         : 3 book(s)
  Science         : 1 book(s)
```

---

## 📄 License

This project is submitted as an academic capstone (BYOP) for the Java Programming course at VIT.
