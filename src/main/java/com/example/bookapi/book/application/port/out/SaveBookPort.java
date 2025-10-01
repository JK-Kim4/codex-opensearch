package com.example.bookapi.book.application.port.out;

import com.example.bookapi.book.domain.BookRegistration;

public interface SaveBookPort {

    Long save(BookRegistration registration);
}
