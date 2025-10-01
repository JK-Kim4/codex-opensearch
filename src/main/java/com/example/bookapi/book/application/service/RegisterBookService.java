package com.example.bookapi.book.application.service;

import com.example.bookapi.book.application.event.BookRegisteredEvent;
import com.example.bookapi.book.application.port.in.BookRegistrationResult;
import com.example.bookapi.book.application.port.in.RegisterBookCommand;
import com.example.bookapi.book.application.port.in.RegisterBookUseCase;
import com.example.bookapi.book.application.port.out.SaveBookPort;
import com.example.bookapi.book.domain.BookRegistration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterBookService implements RegisterBookUseCase {

    private final SaveBookPort saveBookPort;
    private final ApplicationEventPublisher eventPublisher;

    public RegisterBookService(SaveBookPort saveBookPort, ApplicationEventPublisher eventPublisher) {
        this.saveBookPort = saveBookPort;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public BookRegistrationResult register(RegisterBookCommand command) {
        BookRegistration registration = command.toBookRegistration();
        Long bookId = saveBookPort.save(registration);
        eventPublisher.publishEvent(new BookRegisteredEvent(bookId));
        return new BookRegistrationResult(bookId);
    }
}
