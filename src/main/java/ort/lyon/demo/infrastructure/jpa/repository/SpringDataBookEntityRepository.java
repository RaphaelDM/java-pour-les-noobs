package ort.lyon.demo.infrastructure.jpa.repository;

import java.util.Optional;
import ort.lyon.demo.infrastructure.jpa.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBookEntityRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIbsn(int isbn);

    boolean existsByIbsn(int isbn);

    int deleteByIbsn(int isbn);
}
