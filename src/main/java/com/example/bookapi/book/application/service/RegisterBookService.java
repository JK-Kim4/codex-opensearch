package com.example.bookapi.book.application.service;

import com.example.bookapi.book.application.port.in.BookRegistrationResult;
import com.example.bookapi.book.application.port.in.RegisterBookCommand;
import com.example.bookapi.book.application.port.in.RegisterBookUseCase;
import com.example.bookapi.book.application.port.out.SaveBookPort;
import com.example.bookapi.book.domain.BookRegistration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterBookService implements RegisterBookUseCase {

    private final SaveBookPort saveBookPort;

    public RegisterBookService(SaveBookPort saveBookPort) {
        this.saveBookPort = saveBookPort;
    }

    @Override
    public BookRegistrationResult register(RegisterBookCommand command) {
        BookRegistration registration = command.toBookRegistration();
        Long bookId = saveBookPort.save(registration);
        return new BookRegistrationResult(bookId);
    }
}
