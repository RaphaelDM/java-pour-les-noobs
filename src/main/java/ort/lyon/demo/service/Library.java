package ort.lyon.demo.service;

import ort.lyon.demo.domain.Book;
import ort.lyon.demo.domain.BookRepository;

import java.util.List;
import java.util.Set;

public class Library {

    private final BookService bookService;

    public Library() {
        this(new BookService(new BookRepository()));
    }

    Library(BookService bookService) {
        this.bookService = bookService;
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void displayBooks() {
        for (Book book : bookService.getBooksSortedByIsbn()) {
            System.out.println(book);
        }
    }

    public void removeBook(Book book) {
        bookService.removeBook(book);
    }

    public Set<Book> getBooksFromAuthor(String author) { // Récupère les livres d'un auteur spécifique
        return bookService.getBooksFromAuthor(author);
    }

    public Set<Book> getBooksByTitle(String title) { // Récupère les livres dont le titre contient une chaîne spécifique
        return bookService.getBooksByTitle(title);
    }

    public Book findBookByTitle(String title) {
        return bookService.findBookByTitle(title);
    }

    public Book getBookByIsbn(int ibsn) {
        return bookService.getBookByIsbn(ibsn);
    }

    // Méthodes pour récupérer les livres triés par titre ou par auteur
    public List<Book> getBooksSortedByTitle() {
        return bookService.getBooksSortedByTitle();
    }

    public List<Book> getBooksSortedByAuthor() {
        return bookService.getBooksSortedByAuthor();
    }

    public List<Book> getBooksSortedByIsbn() {
        return bookService.getBooksSortedByIsbn();
    }

    public void removeBookByIsbn(int isbn) {
        bookService.removeBookByIsbn(isbn);
    }
}
