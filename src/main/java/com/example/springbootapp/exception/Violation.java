package com.example.springbootapp.exception;

public record Violation(String fieldName, String message) {

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
