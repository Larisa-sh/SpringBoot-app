package com.example.springbootapp.exception;

import org.springframework.http.HttpStatus;

public record ResponseError(HttpStatus status, String message) {}
