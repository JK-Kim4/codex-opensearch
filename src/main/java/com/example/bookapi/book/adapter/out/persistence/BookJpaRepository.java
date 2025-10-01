package com.example.bookapi.book.adapter.out.persistence;

import com.example.bookapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
}
