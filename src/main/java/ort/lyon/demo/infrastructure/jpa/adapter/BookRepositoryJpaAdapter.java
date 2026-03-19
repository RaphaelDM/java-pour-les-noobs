package ort.lyon.demo.infrastructure.jpa.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ort.lyon.demo.domain.Book;
import ort.lyon.demo.domain.port.out.BookRepositoryPort;
import ort.lyon.demo.infrastructure.jpa.mapper.BookEntityMapper;
import ort.lyon.demo.infrastructure.jpa.model.repository.SpringDataBookEntityRepository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryJpaAdapter implements BookRepositoryPort {

    private final SpringDataBookEntityRepository repository;
    private final BookEntityMapper mapper;

    public BookRepositoryJpaAdapter(SpringDataBookEntityRepository repository, BookEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Book> findByIbsn(int isbn) {
        return repository.findByIbsn(isbn).map(mapper::toDomain);
    }

    @Override
    public boolean existsByIbsn(int isbn) {
        return repository.existsByIbsn(isbn);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Book book) {
        if (repository.existsByIbsn(book.getIbsn())) {
            repository.updateByIbsn(book.getIbsn(), book.getTitle(), book.getAuthor(), book.getYear());
        } else {
            repository.save(mapper.toEntity(book));
        }
    }

    @Override
    public boolean deleteByIbsn(int isbn) {
        return repository.deleteByIbsn(isbn) > 0;
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
