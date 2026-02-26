package org.example.service;

import org.example.domain.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Library {

    private final Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void removeBook(Book cleanCodeBook) {
        books.remove(cleanCodeBook);
    }

    public List<Book> getBooksFromAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        String needle = title.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(needle)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Book getBookByIsbn(int ibsn) {
        for (Book book : books) {
            if (book.getIbsn() == ibsn) {
                return book; // on s'arrête dès qu'on a trouvé
            }
        }
        return null;
    }
}
