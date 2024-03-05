package com.example.springbootapp.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinAgeValidator.class)
@Documented
public @interface MinAge {
    String message() default "Invalid age. Age must be at least {value}.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}
