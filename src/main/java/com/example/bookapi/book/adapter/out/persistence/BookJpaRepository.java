package com.example.bookapi.book.adapter.out.persistence;

import com.example.bookapi.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {
            "publisher",
            "authors",
            "authors.author",
            "categories",
            "categories.category",
            "prices",
            "availabilities",
            "assets",
            "ratingSummary"
    })
    Optional<Book> findById(Long id);
}
