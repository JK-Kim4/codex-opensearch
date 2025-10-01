package com.example.bookapi.book.adapter.out.index;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookSearchDocument(
        Long id,
        String title,
        String subtitle,
        String description,
        String publisher,
        List<String> authors,
        LocalDate publicationDate,
        String language,
        List<String> categoryCodes,
        List<String> categoryNames,
        BigDecimal listPrice,
        BigDecimal salePrice,
        BigDecimal discountRate,
        String availabilityStatus,
        Integer stockQuantity,
        Double averageRating,
        Integer reviewCount
) {
}
