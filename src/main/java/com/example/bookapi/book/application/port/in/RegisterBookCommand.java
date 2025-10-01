package com.example.bookapi.book.application.port.in;

import com.example.bookapi.book.domain.BookRegistration;
import com.example.bookapi.entity.enums.BookFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public record RegisterBookCommand(
        String title,
        String subtitle,
        String description,
        String tableOfContents,
        String isbn13,
        String isbn10,
        String otherIdentifiers,
        String edition,
        LocalDate publicationDate,
        BookFormat format,
        String language,
        String dimensions,
        BigDecimal weight,
        Integer pageCount,
        String publisherNote,
        String inventoryNote
) {

    public RegisterBookCommand {
        Objects.requireNonNull(title, "title must not be null");
    }

    public BookRegistration toBookRegistration() {
        return new BookRegistration(
                title,
                subtitle,
                description,
                tableOfContents,
                isbn13,
                isbn10,
                otherIdentifiers,
                edition,
                publicationDate,
                format,
                language,
                dimensions,
                weight,
                pageCount,
                publisherNote,
                inventoryNote
        );
    }
}
