package com.example.springbootapp.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ValidationResponseError(HttpStatus status, List<Violation> violations) {

    public List<Violation> getViolations() {
        return violations;
    }
}
