package com.example.bookapi.book.adapter.in.web;

import com.example.bookapi.book.adapter.in.web.dto.RegisterBookRequest;
import com.example.bookapi.book.adapter.in.web.dto.RegisterBookResponse;
import com.example.bookapi.book.application.port.in.BookRegistrationResult;
import com.example.bookapi.book.application.port.in.RegisterBookCommand;
import com.example.bookapi.book.application.port.in.RegisterBookUseCase;
import com.example.bookapi.entity.enums.BookFormat;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Locale;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final RegisterBookUseCase registerBookUseCase;

    public BookController(RegisterBookUseCase registerBookUseCase) {
        this.registerBookUseCase = registerBookUseCase;
    }

    @PostMapping
    public ResponseEntity<RegisterBookResponse> register(@Valid @RequestBody RegisterBookRequest request) {
        RegisterBookCommand command = toCommand(request);
        BookRegistrationResult result = registerBookUseCase.register(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.bookId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new RegisterBookResponse(result.bookId()));
    }

    private RegisterBookCommand toCommand(RegisterBookRequest request) {
        return new RegisterBookCommand(
                request.title(),
                request.subtitle(),
                request.description(),
                request.tableOfContents(),
                request.isbn13(),
                request.isbn10(),
                request.otherIdentifiers(),
                request.edition(),
                request.publicationDate(),
                parseFormat(request.format()),
                request.language(),
                request.dimensions(),
                request.weight(),
                request.pageCount(),
                request.publisherNote(),
                request.inventoryNote()
        );
    }

    private BookFormat parseFormat(String format) {
        return Optional.ofNullable(format)
                .map(value -> {
                    try {
                        return BookFormat.valueOf(value.toUpperCase(Locale.ROOT));
                    } catch (IllegalArgumentException ex) {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Invalid book format: " + value
                        );
                    }
                })
                .orElse(null);
    }
}
