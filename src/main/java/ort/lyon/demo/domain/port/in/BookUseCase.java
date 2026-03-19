package ort.lyon.demo.domain.port.in;

import ort.lyon.demo.domain.Book;

import java.util.List;
import java.util.Set;

public interface BookUseCase {

    void addBook(Book book);

    void removeBookByIsbn(int isbn);

    List<Book> getBooksSortedByTitle();

    List<Book> getBooksSortedByAuthor();

    List<Book> getBooksSortedByIsbn();

    Book getBookByIsbn(int isbn);

    Book updateBook(int isbn, Book updatedBook);

    Book findBookByTitle(String title);

    Set<Book> getBooksFromAuthor(String author);

    Set<Book> getBooksByTitle(String titleFragment);

    void clearAll();
}
