package com.example.bookapi.book.adapter.out.index;

import com.example.bookapi.entity.Book;
import com.example.bookapi.entity.BookAuthor;
import com.example.bookapi.entity.BookAvailability;
import com.example.bookapi.entity.BookCategory;
import com.example.bookapi.entity.BookPrice;
import com.example.bookapi.entity.BookReview;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookSearchDocumentMapper {

    public BookSearchDocument map(Book book) {
        BookPrice activePrice = selectActivePrice(book);
        BookAvailability activeAvailability = selectActiveAvailability(book);

        List<String> authors = book.getAuthors().stream()
                .sorted(Comparator.comparing(BookAuthor::getAuthorOrder, Comparator.nullsLast(Integer::compareTo)))
                .map(BookAuthor::getAuthor)
                .filter(Objects::nonNull)
                .map(author -> author.getName())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<String> categoryCodes = book.getCategories().stream()
                .map(BookCategory::getCategory)
                .filter(Objects::nonNull)
                .map(category -> category.getCode())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<String> categoryNames = book.getCategories().stream()
                .map(BookCategory::getCategory)
                .filter(Objects::nonNull)
                .map(category -> category.getName())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Double averageRating = book.getRatingSummary() != null && book.getRatingSummary().getAverageRating() != null
                ? book.getRatingSummary().getAverageRating().doubleValue()
                : null;
        Integer reviewCount = book.getRatingSummary() != null && book.getRatingSummary().getReviewCount() != null
                ? Math.toIntExact(book.getRatingSummary().getReviewCount())
                : calculateReviewCount(book);

        return new BookSearchDocument(
                book.getId(),
                book.getTitle(),
                book.getSubtitle(),
                book.getDescription(),
                book.getPublisher() != null ? book.getPublisher().getName() : null,
                authors,
                book.getPublicationDate(),
                book.getLanguage(),
                categoryCodes,
                categoryNames,
                activePrice != null ? activePrice.getListPrice() : null,
                activePrice != null ? activePrice.getSalePrice() : null,
                activePrice != null ? activePrice.getDiscountRate() : null,
                activeAvailability != null && activeAvailability.getStatus() != null ? activeAvailability.getStatus().name() : null,
                activeAvailability != null ? activeAvailability.getStockQuantity() : null,
                averageRating,
                reviewCount
        );
    }

    private BookPrice selectActivePrice(Book book) {
        OffsetDateTime now = OffsetDateTime.now();
        return book.getPrices().stream()
                .filter(price -> price.getEffectiveFrom() != null && !price.getEffectiveFrom().isAfter(now))
                .filter(price -> price.getEffectiveTo() == null || !price.getEffectiveTo().isBefore(now))
                .max(Comparator.comparing(BookPrice::getEffectiveFrom))
                .orElse(null);
    }

    private BookAvailability selectActiveAvailability(Book book) {
        OffsetDateTime now = OffsetDateTime.now();
        return book.getAvailabilities().stream()
                .filter(availability -> availability.getEffectiveFrom() != null && !availability.getEffectiveFrom().isAfter(now))
                .filter(availability -> availability.getEffectiveTo() == null || !availability.getEffectiveTo().isBefore(now))
                .max(Comparator.comparing(BookAvailability::getEffectiveFrom))
                .orElse(null);
    }

    private Integer calculateReviewCount(Book book) {
        long count = book.getReviews().stream()
                .filter(Objects::nonNull)
                .map(BookReview::getId)
                .filter(Objects::nonNull)
                .count();
        return (int) count;
    }
}
