package ort.lyon.demo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepository {

    private final Map<Integer, Book> booksByIsbn = new LinkedHashMap<>();

    public Optional<Book> findByIsbn(int isbn) {
        return Optional.ofNullable(booksByIsbn.get(isbn));
    }

    public List<Book> findAll() {
        return new ArrayList<>(booksByIsbn.values());
    }

    public void save(Book book) {
        booksByIsbn.put(book.getIbsn(), book);
    }

    public boolean deleteByIsbn(int isbn) {
        return booksByIsbn.remove(isbn) != null;
    }

    public void clear() {
        booksByIsbn.clear();
    }
}
