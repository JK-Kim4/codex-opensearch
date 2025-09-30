package com.example.bookapi.entity;

import com.example.bookapi.common.AuditableEntity;
import com.example.bookapi.entity.enums.BookRelationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "related_books")
public class RelatedBook extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_book_id", nullable = false)
    private Book relatedBook;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation_type", length = 30, nullable = false)
    private BookRelationType relationType;

    @Column(name = "sequence_no")
    private Integer sequence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getRelatedBook() {
        return relatedBook;
    }

    public void setRelatedBook(Book relatedBook) {
        this.relatedBook = relatedBook;
    }

    public BookRelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(BookRelationType relationType) {
        this.relationType = relationType;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
