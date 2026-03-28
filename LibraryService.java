package com.library;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Core service class for Library Management System.
 * Demonstrates: Collections, Generics, Exception Handling, Streams, OOP.
 */
public class LibraryService {

    private final Map<String, Book>   bookCatalog   = new HashMap<>();
    private final Map<String, Member> memberRegistry = new HashMap<>();

    // ─────────────────────────────────────────────
    //  Book Management
    // ─────────────────────────────────────────────

    public void addBook(Book book) {
        if (bookCatalog.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        bookCatalog.put(book.getIsbn(), book);
        System.out.println("✔ Book added: " + book.getTitle());
    }

    public void removeBook(String isbn) {
        Book book = getBookOrThrow(isbn);
        if (!book.isAvailable()) {
            throw new IllegalStateException("Cannot remove a checked-out book: " + book.getTitle());
        }
        bookCatalog.remove(isbn);
        System.out.println("✔ Book removed: " + book.getTitle());
    }

    public List<Book> searchByTitle(String keyword) {
        String kw = keyword.toLowerCase();
        return bookCatalog.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        String kw = author.toLowerCase();
        return bookCatalog.values().stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks() {
        return bookCatalog.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    // ─────────────────────────────────────────────
    //  Member Management
    // ─────────────────────────────────────────────

    public void registerMember(Member member) {
        if (memberRegistry.containsKey(member.getMemberId())) {
            throw new IllegalArgumentException("Member ID " + member.getMemberId() + " is already registered.");
        }
        memberRegistry.put(member.getMemberId(), member);
        System.out.println("✔ Member registered: " + member.getName());
    }

    // ─────────────────────────────────────────────
    //  Borrow / Return
    // ─────────────────────────────────────────────

    public void borrowBook(String memberId, String isbn) {
        Member member = getMemberOrThrow(memberId);
        Book   book   = getBookOrThrow(isbn);

        if (!book.isAvailable()) {
            throw new IllegalStateException("\"" + book.getTitle() + "\" is currently checked out.");
        }
        book.setAvailable(false);
        member.borrowBook(isbn);
        System.out.printf("✔ \"%s\" borrowed by %s%n", book.getTitle(), member.getName());
    }

    public void returnBook(String memberId, String isbn) {
        Member member = getMemberOrThrow(memberId);
        Book   book   = getBookOrThrow(isbn);

        if (!member.hasBorrowed(isbn)) {
            throw new IllegalStateException(member.getName() + " has not borrowed \"" + book.getTitle() + "\".");
        }
        book.setAvailable(true);
        member.returnBook(isbn);
        System.out.printf("✔ \"%s\" returned by %s%n", book.getTitle(), member.getName());
    }

    // ─────────────────────────────────────────────
    //  Reports
    // ─────────────────────────────────────────────

    public void printCatalog() {
        System.out.println("\n═══════════════ BOOK CATALOG ═══════════════");
        if (bookCatalog.isEmpty()) { System.out.println("  (no books)"); return; }
        bookCatalog.values().stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(b -> System.out.println("  " + b));
        System.out.println("════════════════════════════════════════════\n");
    }

    public void printMembers() {
        System.out.println("\n═══════════════ MEMBERS ════════════════════");
        if (memberRegistry.isEmpty()) { System.out.println("  (no members)"); return; }
        memberRegistry.values().forEach(m -> System.out.println("  " + m));
        System.out.println("════════════════════════════════════════════\n");
    }

    public Map<String, Long> getGenreStats() {
        return bookCatalog.values().stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
    }

    // ─────────────────────────────────────────────
    //  Helpers
    // ─────────────────────────────────────────────

    private Book getBookOrThrow(String isbn) {
        Book book = bookCatalog.get(isbn);
        if (book == null) throw new NoSuchElementException("No book found with ISBN: " + isbn);
        return book;
    }

    private Member getMemberOrThrow(String memberId) {
        Member member = memberRegistry.get(memberId);
        if (member == null) throw new NoSuchElementException("No member found with ID: " + memberId);
        return member;
    }

    public int getTotalBooks()     { return bookCatalog.size(); }
    public int getTotalMembers()   { return memberRegistry.size(); }
}
