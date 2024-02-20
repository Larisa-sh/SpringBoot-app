package com.example.springbootapp.exception;

public class EmployeeNotUniqueException extends RuntimeException{
    public EmployeeNotUniqueException(String message) {
        super(message);
    }
}
