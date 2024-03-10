package com.example.springbootapp.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.*;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError employeeDoesNotExist(EmployeeNotFoundException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError employeeAlreadyExists(EmployeeNotUniqueException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError employeeNotSaved(EmployeeNotSavedException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationResponseError onConstraintValidationException(
            ConstraintViolationException exception
    ) {
        LOGGER.error(exception.getMessage(), exception);
        final List<Violation> violations = exception.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationResponseError(HttpStatus.BAD_REQUEST,violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationResponseError onMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        LOGGER.error(exception.getMessage(), exception);
        final List<Violation> violations = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationResponseError(HttpStatus.BAD_REQUEST, violations);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleUnexpectedException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

}
