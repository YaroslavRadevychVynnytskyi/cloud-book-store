package com.application.bsbookservice.repo;

import com.application.bsbookservice.model.Book;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @EntityGraph(attributePaths = {"categoryIds"})
    Page<Book> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"categoryIds"})
    Page<Book> findAll(@Nullable Specification<Book> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"categoryIds"})
    Optional<Book> findById(Long bookId);
}
