package org.example.service;

import org.example.domain.Book;
import org.example.exceptions.BookNotFoundException;
import org.example.exceptions.DuplicateBookException;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Library {

    private final Map<Integer, Book> booksByIsbn = new LinkedHashMap<>();

    public void addBook(Book book) {
        requireArgument(book, "book");

        if (booksByIsbn.containsKey(book.getIbsn())) {
            throw new DuplicateBookException("A book with ISBN " + book.getIbsn() + " already exists");
        }

        booksByIsbn.put(book.getIbsn(), book);
    }

    public void displayBooks() {
        for (Book book : booksByIsbn.values()) {
            System.out.println(book);
        }
    }

    public void removeBook(Book book) {
        requireArgument(book, "book");

        if (booksByIsbn.remove(book.getIbsn()) == null) {
            throw new BookNotFoundException("No book found with ISBN " + book.getIbsn());
        }
    }

    public Set<Book> getBooksFromAuthor(String author) {
        requireArgument(author, "author");

        Set<Book> result = new LinkedHashSet<>();
        for (Book book : booksByIsbn.values()) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public Set<Book> getBooksByTitle(String title) {
        requireArgument(title, "title");

        Set<Book> result = new LinkedHashSet<>();
        String needle = title.toLowerCase();

        for (Book book : booksByIsbn.values()) {
            if (book.getTitle().toLowerCase().contains(needle)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book findBookByTitle(String title) {
        requireArgument(title, "title");

        for (Book book : booksByIsbn.values()) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }

        throw new BookNotFoundException("No book found with title '" + title + "'");
    }

    public Book getBookByIsbn(int ibsn) {
        Book book = booksByIsbn.get(ibsn);

        if (book != null) {
            return book;
        }

        throw new BookNotFoundException("No book found with ISBN " + ibsn);
    }

    private static <T> T requireArgument(T value, String argumentName) {
        if (value == null) {
            throw new IllegalArgumentException(argumentName + " must not be null");
        }

        return value;
    }
}
