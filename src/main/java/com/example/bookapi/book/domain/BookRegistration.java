package com.example.bookapi.book.domain;

import com.example.bookapi.entity.enums.BookFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public record BookRegistration(
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

    public BookRegistration {
        Objects.requireNonNull(title, "title must not be null");
    }
}
