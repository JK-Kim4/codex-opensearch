package com.example.bookapi.book.adapter.out.index;

import com.example.bookapi.config.OpenSearchProperties;
import java.io.IOException;
import java.util.Objects;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OpenSearchBookIndexer implements BookSearchIndexer {

    private static final Logger log = LoggerFactory.getLogger(OpenSearchBookIndexer.class);

    private final OpenSearchClient client;
    private final OpenSearchProperties properties;

    public OpenSearchBookIndexer(OpenSearchClient client, OpenSearchProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    @Override
    public void index(BookSearchDocument document) {
        try {
            String documentId = Objects.requireNonNull(document.id(), "Book id must not be null when indexing").toString();
            IndexRequest<BookSearchDocument> request = IndexRequest.of(builder -> builder
                    .index(properties.getIndex().getBook())
                    .id(documentId)
                    .document(document));
            client.index(request);
            log.debug("Indexed book [{}] into OpenSearch", document.id());
        } catch (IOException e) {
            throw new BookIndexingException("Failed to index book %s".formatted(document.id()), e);
        }
    }
}
