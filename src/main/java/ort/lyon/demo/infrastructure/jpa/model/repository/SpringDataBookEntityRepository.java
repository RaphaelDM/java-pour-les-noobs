package ort.lyon.demo.infrastructure.jpa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ort.lyon.demo.infrastructure.jpa.model.entity.BookEntity;

public interface SpringDataBookEntityRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIbsn(int isbn);

    boolean existsByIbsn(int isbn);

    int deleteByIbsn(int isbn);
}
