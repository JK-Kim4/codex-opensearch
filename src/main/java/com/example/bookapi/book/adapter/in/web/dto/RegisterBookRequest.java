package com.example.bookapi.book.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterBookRequest(
        @NotBlank(message = "title is required")
        @Size(max = 300)
        String title,
        @Size(max = 300)
        String subtitle,
        String description,
        String tableOfContents,
        @Size(max = 20)
        String isbn13,
        @Size(max = 20)
        String isbn10,
        String otherIdentifiers,
        @Size(max = 100)
        String edition,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate publicationDate,
        String format,
        @Size(max = 100)
        String language,
        String dimensions,
        BigDecimal weight,
        Integer pageCount,
        String publisherNote,
        String inventoryNote
) {
}
