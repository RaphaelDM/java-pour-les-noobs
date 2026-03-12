package ort.lyon.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import ort.lyon.demo.domain.Book;
import ort.lyon.demo.service.Library;
import ort.lyon.demo.exceptions.BookNotFoundException;
import ort.lyon.demo.exceptions.DuplicateBookException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

class LibraryTest {

    @Test
    void shouldFindBookByExactTitle() {
        Library library = createLibrary();

        Book book = library.findBookByTitle("Clean Code");

        assertEquals(1, book.getIbsn());
        assertEquals("Clean Code", book.getTitle());
    }

    @Test
    void shouldThrowWhenBookTitleDoesNotExist() {
        Library library = createLibrary();

        assertThrows(BookNotFoundException.class, () -> library.findBookByTitle("Domain-Driven Design"));
    }

    @Test
    void shouldThrowWhenAddingDuplicateIsbn() {
        Library library = new Library();
        library.addBook(new Book(1, "Clean Code", "Robert Martin", 2008));

        assertThrows(DuplicateBookException.class,
                () -> library.addBook(new Book(1, "Clean Architecture", "Robert Martin", 2017)));
    }

    @Test
    void shouldGetBookByIsbn() {
        Library library = createLibrary();

        Book book = library.getBookByIsbn(2);

        assertEquals("Effective Java", book.getTitle());
    }

    @Test
    void shouldThrowWhenBookIsbnDoesNotExist() {
        Library library = createLibrary();

        assertThrows(BookNotFoundException.class, () -> library.getBookByIsbn(999));
    }

    @Test
    void shouldRemoveExistingBook() {
        Library library = createLibrary();

        library.removeBook(new Book(2, "Effective Java", "Joshua Bloch", 2018));

        assertThrows(BookNotFoundException.class, () -> library.getBookByIsbn(2));
    }

    @Test
    void shouldThrowWhenRemovingUnknownBook() {
        Library library = createLibrary();

        assertThrows(BookNotFoundException.class,
                () -> library.removeBook(new Book(999, "Unknown", "Nobody", 2024)));
    }

    @Test
    void shouldReturnBooksFromAuthor() {
        Library library = createLibrary();

        Set<Book> books = library.getBooksFromAuthor("Robert Martin");

        assertEquals(2, books.size());
        assertTrue(books.stream().allMatch(book -> book.getAuthor().equals("Robert Martin")));
    }

    @ParameterizedTest
    @CsvSource({
            "Clean, 2",
            "JAVA, 1",
            "architecture, 1",
            "'', 3"
    })
    void shouldSearchBooksByPartialTitleIgnoringCase(String fragment, int expectedCount) {
        Library library = createLibrary();

        Set<Book> books = library.getBooksByTitle(fragment);

        assertEquals(expectedCount, books.size());
    }

    @ParameterizedTest
    @NullSource
    void shouldRejectNullTitleSearch(String title) {
        Library library = createLibrary();

        assertThrows(IllegalArgumentException.class, () -> library.getBooksByTitle(title));
        assertThrows(IllegalArgumentException.class, () -> library.findBookByTitle(title));
    }

    @Test
    void shouldRejectNullAuthorSearch() {
        Library library = createLibrary();

        assertThrows(IllegalArgumentException.class, () -> library.getBooksFromAuthor(null));
    }

    @Test
    void shouldRejectNullBookOperations() {
        Library library = new Library();

        assertThrows(IllegalArgumentException.class, () -> library.addBook(null));
        assertThrows(IllegalArgumentException.class, () -> library.removeBook(null));
    }

    @Test
    void shouldReturnBooksSortedByTitleAlphabetically() {
        Library library = createLibrary();

        List<Book> books = library.getBooksSortedByTitle();

        assertEquals("Clean Architecture", books.get(0).getTitle());
        assertEquals("Clean Code", books.get(1).getTitle());
        assertEquals("Effective Java", books.get(2).getTitle());
    }

    @Test
    void shouldReturnBooksSortedByAuthorThenTitle() {
        Library library = createLibrary();

        List<Book> books = library.getBooksSortedByAuthor();

        // Joshua Bloch avant Robert Martin (alphabétique)
        assertEquals("Joshua Bloch", books.get(0).getAuthor());
        assertEquals("Robert Martin", books.get(1).getAuthor());
        // pour Robert Martin : Clean Architecture avant Clean Code
        assertEquals("Clean Architecture", books.get(1).getTitle());
        assertEquals("Clean Code", books.get(2).getTitle());
    }

    @Test
    void shouldReturnEmptyListWhenLibraryIsEmpty() {
        Library library = new Library();

        assertTrue(library.getBooksSortedByTitle().isEmpty());
        assertTrue(library.getBooksSortedByAuthor().isEmpty());
    }
    // Méthode utilitaire pour créer une bibliothèque pré-remplie de livres pour les tests
    private static Library createLibrary() {
        Library library = new Library();
        library.addBook(new Book(1, "Clean Code", "Robert Martin", 2008));
        library.addBook(new Book(2, "Effective Java", "Joshua Bloch", 2018));
        library.addBook(new Book(3, "Clean Architecture", "Robert Martin", 2017));
        return library;
    }
}
