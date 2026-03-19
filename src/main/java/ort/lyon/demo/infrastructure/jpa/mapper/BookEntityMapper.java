package ort.lyon.demo.infrastructure.jpa.mapper;

import ort.lyon.demo.domain.Book;
import ort.lyon.demo.infrastructure.jpa.model.entity.BookEntity;

import org.springframework.stereotype.Component;

@Component
public class BookEntityMapper {

    public Book toDomain(BookEntity entity) {
        return new Book(entity.getIbsn(), entity.getTitle(), entity.getAuthor(), entity.getYear());
    }

    public BookEntity toEntity(Book book) {
        return new BookEntity(book.getIbsn(), book.getTitle(), book.getAuthor(), book.getYear());
    }
}
