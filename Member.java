package com.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Library Member.
 */
public class Member {
    private String memberId;
    private String name;
    private String email;
    private List<String> borrowedIsbns;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.borrowedIsbns = new ArrayList<>();
    }

    public String getMemberId() { return memberId; }
    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public List<String> getBorrowedIsbns() { return borrowedIsbns; }

    public void borrowBook(String isbn)  { borrowedIsbns.add(isbn); }
    public void returnBook(String isbn)  { borrowedIsbns.remove(isbn); }
    public boolean hasBorrowed(String isbn) { return borrowedIsbns.contains(isbn); }

    @Override
    public String toString() {
        return String.format("Member[%s] %s <%s> | Books borrowed: %d",
                memberId, name, email, borrowedIsbns.size());
    }
}
