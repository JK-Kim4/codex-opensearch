package com.example.bookapi.entity;

import com.example.bookapi.common.AuditableEntity;
import com.example.bookapi.entity.enums.BookFormat;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(length = 300)
    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "table_of_contents", columnDefinition = "TEXT")
    private String tableOfContents;

    @Column(length = 20, unique = true)
    private String isbn13;

    @Column(length = 20, unique = true)
    private String isbn10;

    @Column(name = "other_identifiers", columnDefinition = "TEXT")
    private String otherIdentifiers;

    @Column(length = 100)
    private String edition;

    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private BookFormat format;

    @Column(length = 100)
    private String language;

    @Column(length = 200)
    private String dimensions;

    @Column(precision = 10, scale = 2)
    private BigDecimal weight;

    private Integer pageCount;

    @Column(name = "publisher_note", columnDefinition = "TEXT")
    private String publisherNote;

    @Column(name = "inventory_note", columnDefinition = "TEXT")
    private String inventoryNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthor> authors = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCategory> categories = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookPrice> prices = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAvailability> availabilities = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAsset> assets = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelatedBook> relatedBooks = new ArrayList<>();

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BookRatingSummary ratingSummary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTableOfContents() {
        return tableOfContents;
    }

    public void setTableOfContents(String tableOfContents) {
        this.tableOfContents = tableOfContents;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getOtherIdentifiers() {
        return otherIdentifiers;
    }

    public void setOtherIdentifiers(String otherIdentifiers) {
        this.otherIdentifiers = otherIdentifiers;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setFormat(BookFormat format) {
        this.format = format;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublisherNote() {
        return publisherNote;
    }

    public void setPublisherNote(String publisherNote) {
        this.publisherNote = publisherNote;
    }

    public String getInventoryNote() {
        return inventoryNote;
    }

    public void setInventoryNote(String inventoryNote) {
        this.inventoryNote = inventoryNote;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public List<BookAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<BookAuthor> authors) {
        this.authors = authors;
    }

    public List<BookCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<BookCategory> categories) {
        this.categories = categories;
    }

    public List<BookPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<BookPrice> prices) {
        this.prices = prices;
    }

    public List<BookAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<BookAvailability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<BookAsset> getAssets() {
        return assets;
    }

    public void setAssets(List<BookAsset> assets) {
        this.assets = assets;
    }

    public List<BookReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<BookReview> reviews) {
        this.reviews = reviews;
    }

    public List<RelatedBook> getRelatedBooks() {
        return relatedBooks;
    }

    public void setRelatedBooks(List<RelatedBook> relatedBooks) {
        this.relatedBooks = relatedBooks;
    }

    public BookRatingSummary getRatingSummary() {
        return ratingSummary;
    }

    public void setRatingSummary(BookRatingSummary ratingSummary) {
        this.ratingSummary = ratingSummary;
    }
}
