package com.example.springbootapp.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseError(HttpStatus status, String message, List<Violation> violations) {

    public ResponseError(HttpStatus status, String message) {
        this(status, message, null);
    }

    public ResponseError(HttpStatus status, List<Violation> violations) {
        this(status, "Validation error", violations);
    }
}
