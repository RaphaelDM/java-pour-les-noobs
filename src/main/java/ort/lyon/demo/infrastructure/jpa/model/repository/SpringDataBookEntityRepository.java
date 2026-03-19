package ort.lyon.demo.infrastructure.jpa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ort.lyon.demo.infrastructure.jpa.model.entity.BookEntity;

public interface SpringDataBookEntityRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIbsn(int isbn);

    boolean existsByIbsn(int isbn);

    @Transactional
    int deleteByIbsn(int isbn);

    @Transactional
    @Modifying
    @Query("UPDATE BookEntity b SET b.title = :title, b.author = :author, b.year = :year WHERE b.ibsn = :ibsn")
    int updateByIbsn(@Param("ibsn") int ibsn,
                     @Param("title") String title,
                     @Param("author") String author,
                     @Param("year") int year);
}
