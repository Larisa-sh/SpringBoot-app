package com.example.springbootapp.exception;

public record Violation(String fieldName, String message) {

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Violation{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
