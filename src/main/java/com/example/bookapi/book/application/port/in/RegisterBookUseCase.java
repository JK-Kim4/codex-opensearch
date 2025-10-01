package com.example.bookapi.book.application.port.in;

public interface RegisterBookUseCase {

    BookRegistrationResult register(RegisterBookCommand command);
}
