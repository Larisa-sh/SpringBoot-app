package com.example.springbootapp.exception;

public class EmployeeNotSavedException extends RuntimeException{
    public EmployeeNotSavedException(String message) {
        super(message);
    }
}
