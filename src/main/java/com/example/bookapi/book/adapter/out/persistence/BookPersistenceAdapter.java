package com.example.bookapi.book.adapter.out.persistence;

import com.example.bookapi.book.application.port.out.SaveBookPort;
import com.example.bookapi.book.domain.BookRegistration;
import com.example.bookapi.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookPersistenceAdapter implements SaveBookPort {

    private final BookJpaRepository bookJpaRepository;

    public BookPersistenceAdapter(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public Long save(BookRegistration registration) {
        Book entity = mapToEntity(registration);
        return bookJpaRepository.save(entity).getId();
    }

    private Book mapToEntity(BookRegistration registration) {
        Book book = new Book();
        book.setTitle(registration.title());
        book.setSubtitle(registration.subtitle());
        book.setDescription(registration.description());
        book.setTableOfContents(registration.tableOfContents());
        book.setIsbn13(registration.isbn13());
        book.setIsbn10(registration.isbn10());
        book.setOtherIdentifiers(registration.otherIdentifiers());
        book.setEdition(registration.edition());
        book.setPublicationDate(registration.publicationDate());
        book.setFormat(registration.format());
        book.setLanguage(registration.language());
        book.setDimensions(registration.dimensions());
        book.setWeight(registration.weight());
        book.setPageCount(registration.pageCount());
        book.setPublisherNote(registration.publisherNote());
        book.setInventoryNote(registration.inventoryNote());
        return book;
    }
}
