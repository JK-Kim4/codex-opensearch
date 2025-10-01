package com.example.bookapi.book.adapter.out.index;

import com.example.bookapi.book.adapter.out.persistence.BookJpaRepository;
import com.example.bookapi.book.application.event.BookRegisteredEvent;
import com.example.bookapi.entity.Book;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Component
public class BookSearchIndexingEventListener {

    private static final Logger log = LoggerFactory.getLogger(BookSearchIndexingEventListener.class);

    private final BookJpaRepository bookJpaRepository;
    private final BookSearchDocumentMapper documentMapper;
    private final BookSearchIndexer bookSearchIndexer;

    public BookSearchIndexingEventListener(BookJpaRepository bookJpaRepository,
                                           BookSearchDocumentMapper documentMapper,
                                           BookSearchIndexer bookSearchIndexer) {
        this.bookJpaRepository = bookJpaRepository;
        this.documentMapper = documentMapper;
        this.bookSearchIndexer = bookSearchIndexer;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(BookRegisteredEvent event) {
        Optional<Book> book = bookJpaRepository.findById(event.bookId());
        if (book.isEmpty()) {
            log.warn("Book with id {} not found for indexing", event.bookId());
            return;
        }
        BookSearchDocument document = documentMapper.map(book.get());
        bookSearchIndexer.index(document);
    }
}
