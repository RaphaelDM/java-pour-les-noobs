package ort.lyon.demo.service;

import ort.lyon.demo.domain.Book;
import ort.lyon.demo.domain.exception.BookNotFoundException;
import ort.lyon.demo.domain.exception.DuplicateBookException;

import ort.lyon.demo.domain.port.in.BookUseCase;
import ort.lyon.demo.domain.port.out.BookRepositoryPort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService implements BookUseCase {

    private final BookRepositoryPort repository;

    public BookService(BookRepositoryPort repository) {
        this.repository = repository;
    }
    public void addBook(Book book) {
        requireArgument(book, "book");

        if (repository.existsByIbsn(book.getIbsn())) {
            throw new DuplicateBookException("A book with ISBN " + book.getIbsn() + " already exists");
        }

        repository.save(book);
    }
    
    public void removeBookByIsbn(int isbn) {
        if (!repository.deleteByIbsn(isbn)) {
            throw new BookNotFoundException("No book found with ISBN " + isbn);
        }
    }

    public List<Book> getBooksSortedByTitle() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksSortedByAuthor() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksSortedByIsbn() {
        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(Book::getIbsn))
                .collect(Collectors.toList());
    }

    public Book getBookByIsbn(int isbn) {
        return repository.findByIbsn(isbn)
                .orElseThrow(() -> new BookNotFoundException("No book found with ISBN " + isbn));
    }

    public Book findBookByTitle(String title) {
        requireArgument(title, "title");

        return repository.findAll().stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("No book found with title '" + title + "'"));
    }

    public Set<Book> getBooksFromAuthor(String author) {
        requireArgument(author, "author");

        return repository.findAll().stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toCollection(java.util.LinkedHashSet::new));
    }

    public Set<Book> getBooksByTitle(String titleFragment) {
        requireArgument(titleFragment, "title");

        String needle = titleFragment.toLowerCase();
        return repository.findAll().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(needle))
                .collect(Collectors.toCollection(java.util.LinkedHashSet::new));
    }
    public void removeBook(Book book) {
        requireArgument(book, "book");
        removeBookByIsbn(book.getIbsn());
    }

    public void clearAll() {
        repository.deleteAll();
    }

    private static <T> T requireArgument(T value, String argumentName) {
        if (value == null) {
            throw new IllegalArgumentException(argumentName + " must not be null");
        }

        return value;
    }
}
