package com.example.springbootapp.exception;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {

    public List<Violation> getViolations() {
        return violations;
    }
}
