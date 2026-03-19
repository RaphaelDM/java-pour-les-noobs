package ort.lyon.demo.service;

import ort.lyon.demo.domain.Book;
import ort.lyon.demo.domain.exception.BookNotFoundException;
import ort.lyon.demo.domain.exception.DuplicateBookException;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        for (Book book : getBooksSortedByIsbn()) {
            System.out.println(book);
        }
    }

    public void removeBook(Book book) {
        requireArgument(book, "book");
        removeBookByIsbn(book.getIbsn());
    }

    public Set<Book> getBooksFromAuthor(String author) { // Récupère les livres d'un auteur spécifique
        requireArgument(author, "author");
        return booksByIsbn.values().stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toCollection(java.util.LinkedHashSet::new));
    }

    public Set<Book> getBooksByTitle(String title) { // Récupère les livres dont le titre contient une chaîne spécifique
        requireArgument(title, "title");
        String needle = title.toLowerCase();
        return booksByIsbn.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(needle))
                .collect(Collectors.toCollection(java.util.LinkedHashSet::new));
    }

    public Book findBookByTitle(String title) {
        requireArgument(title, "title");
        return booksByIsbn.values().stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("No book found with title '" + title + "'"));
    }

    public Book getBookByIsbn(int ibsn) {
        Book book = booksByIsbn.get(ibsn);
        if (book == null) {
            throw new BookNotFoundException("No book found with ISBN " + ibsn);
        }
        return book;
    }

    // Méthodes pour récupérer les livres triés par titre ou par auteur
    public List<Book> getBooksSortedByTitle() {
        return booksByIsbn.values().stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksSortedByAuthor() {
        return booksByIsbn.values().stream()
                .sorted(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksSortedByIsbn() {
        return booksByIsbn.values().stream()
                .sorted(Comparator.comparingInt(Book::getIbsn))
                .collect(Collectors.toList());
    }

    public void removeBookByIsbn(int isbn) {
        if (booksByIsbn.remove(isbn) == null) {
            throw new BookNotFoundException("No book found with ISBN " + isbn);
        }
    }

    private static <T> T requireArgument(T value, String argumentName) {
        if (value == null) {
            throw new IllegalArgumentException(argumentName + " must not be null");
        }
        return value;
    }
}
