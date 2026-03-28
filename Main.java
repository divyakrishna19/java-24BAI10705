package com.library;

/**
 * Main entry point for the Library Management System.
 * Demonstrates all features of the application.
 */
public class Main {

    public static void main(String[] args) {
        LibraryService library = new LibraryService();

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║    Library Management System — BYOP      ║");
        System.out.println("║    Divya Krishna | 24BAI10705             ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ── Add Books ──────────────────────────────────────────────────────
        library.addBook(new Book("ISBN001", "Clean Code",               "Robert C. Martin", "Technology"));
        library.addBook(new Book("ISBN002", "The Pragmatic Programmer",  "Andrew Hunt",      "Technology"));
        library.addBook(new Book("ISBN003", "Effective Java",            "Joshua Bloch",     "Technology"));
        library.addBook(new Book("ISBN004", "To Kill a Mockingbird",     "Harper Lee",       "Fiction"));
        library.addBook(new Book("ISBN005", "1984",                      "George Orwell",    "Fiction"));
        library.addBook(new Book("ISBN006", "A Brief History of Time",   "Stephen Hawking",  "Science"));
        library.addBook(new Book("ISBN007", "The Alchemist",             "Paulo Coelho",     "Fiction"));
        library.addBook(new Book("ISBN008", "Design Patterns",           "Gang of Four",     "Technology"));

        // ── Register Members ───────────────────────────────────────────────
        library.registerMember(new Member("M001", "Divya Krishna",  "divya@vit.ac.in"));
        library.registerMember(new Member("M002", "Arjun Sharma",   "arjun@vit.ac.in"));
        library.registerMember(new Member("M003", "Priya Nair",     "priya@vit.ac.in"));

        // ── Print Catalog & Members ────────────────────────────────────────
        library.printCatalog();
        library.printMembers();

        // ── Borrow Books ───────────────────────────────────────────────────
        System.out.println("─── Borrow Operations ───");
        library.borrowBook("M001", "ISBN003");   // Divya borrows Effective Java
        library.borrowBook("M001", "ISBN001");   // Divya borrows Clean Code
        library.borrowBook("M002", "ISBN005");   // Arjun borrows 1984
        System.out.println();

        // ── Search ─────────────────────────────────────────────────────────
        System.out.println("─── Search: title contains 'design' ───");
        library.searchByTitle("design").forEach(b -> System.out.println("  " + b));

        System.out.println("\n─── Search: author contains 'George' ───");
        library.searchByAuthor("George").forEach(b -> System.out.println("  " + b));

        // ── Available Books ────────────────────────────────────────────────
        System.out.println("\n─── Currently Available Books ───");
        library.getAvailableBooks().forEach(b -> System.out.println("  " + b));

        // ── Return a Book ──────────────────────────────────────────────────
        System.out.println("\n─── Return Operation ───");
        library.returnBook("M001", "ISBN001");

        // ── Genre Statistics ───────────────────────────────────────────────
        System.out.println("\n─── Genre Statistics ───");
        library.getGenreStats().forEach((genre, count) ->
                System.out.printf("  %-15s : %d book(s)%n", genre, count));

        // ── Exception Handling Demo ────────────────────────────────────────
        System.out.println("\n─── Exception Handling Demo ───");
        try {
            library.borrowBook("M002", "ISBN003");   // Already checked out
        } catch (IllegalStateException e) {
            System.out.println("  Caught expected error: " + e.getMessage());
        }

        try {
            library.borrowBook("M999", "ISBN001");   // Non-existent member
        } catch (Exception e) {
            System.out.println("  Caught expected error: " + e.getMessage());
        }

        // ── Summary ────────────────────────────────────────────────────────
        System.out.printf("%n═══ Summary: %d books, %d members registered ═══%n",
                library.getTotalBooks(), library.getTotalMembers());
    }
}
