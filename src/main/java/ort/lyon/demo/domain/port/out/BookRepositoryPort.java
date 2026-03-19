package ort.lyon.demo.domain.port.out;

import java.util.List;
import java.util.Optional;
import ort.lyon.demo.domain.Book;

public interface BookRepositoryPort {

    Optional<Book> findByIbsn(int isbn);

    boolean existsByIbsn(int isbn);

    List<Book> findAll();

    void save(Book book);

    boolean deleteByIbsn(int isbn);

    void deleteAll();
}
