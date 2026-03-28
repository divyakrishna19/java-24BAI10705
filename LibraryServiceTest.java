package com.library;

import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LibraryService.
 */
class LibraryServiceTest {

    private LibraryService library;

    @BeforeEach
    void setUp() {
        library = new LibraryService();
        library.addBook(new Book("ISBN001", "Effective Java",   "Joshua Bloch",  "Technology"));
        library.addBook(new Book("ISBN002", "Clean Code",       "Robert Martin", "Technology"));
        library.addBook(new Book("ISBN003", "The Alchemist",    "Paulo Coelho",  "Fiction"));
        library.registerMember(new Member("M001", "Divya Krishna", "divya@vit.ac.in"));
    }

    @Test
    @DisplayName("Borrow a book successfully")
    void testBorrowBook() {
        library.borrowBook("M001", "ISBN001");
        List<Book> available = library.getAvailableBooks();
        assertTrue(available.stream().noneMatch(b -> b.getIsbn().equals("ISBN001")));
    }

    @Test
    @DisplayName("Return a borrowed book")
    void testReturnBook() {
        library.borrowBook("M001", "ISBN001");
        library.returnBook("M001", "ISBN001");
        List<Book> available = library.getAvailableBooks();
        assertTrue(available.stream().anyMatch(b -> b.getIsbn().equals("ISBN001")));
    }

    @Test
    @DisplayName("Cannot borrow already checked-out book")
    void testBorrowCheckedOutBook() {
        library.registerMember(new Member("M002", "Arjun", "arjun@vit.ac.in"));
        library.borrowBook("M001", "ISBN001");
        assertThrows(IllegalStateException.class, () -> library.borrowBook("M002", "ISBN001"));
    }

    @Test
    @DisplayName("Search books by title keyword")
    void testSearchByTitle() {
        List<Book> results = library.searchByTitle("clean");
        assertEquals(1, results.size());
        assertEquals("Clean Code", results.get(0).getTitle());
    }

    @Test
    @DisplayName("Search books by author")
    void testSearchByAuthor() {
        List<Book> results = library.searchByAuthor("paulo");
        assertEquals(1, results.size());
        assertEquals("The Alchemist", results.get(0).getTitle());
    }

    @Test
    @DisplayName("Duplicate book ISBN throws exception")
    void testDuplicateBook() {
        assertThrows(IllegalArgumentException.class, () ->
                library.addBook(new Book("ISBN001", "Duplicate", "Author", "Genre")));
    }

    @Test
    @DisplayName("Genre statistics correct")
    void testGenreStats() {
        var stats = library.getGenreStats();
        assertEquals(2L, stats.get("Technology"));
        assertEquals(1L, stats.get("Fiction"));
    }

    @Test
    @DisplayName("Total book count is correct")
    void testTotalBooks() {
        assertEquals(3, library.getTotalBooks());
    }
}
