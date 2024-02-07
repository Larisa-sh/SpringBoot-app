package com.example.springbootapp.dto;

import com.example.springbootapp.entity.Gender;
import com.example.springbootapp.entity.JobTitle;

import java.util.Date;

public record EmployeeDTO (Long employeeId,
                           String firstName,
                           String lastName,
                           int departmentId,
                           JobTitle jobTitle,
                           Gender gender,
                           Date dateOfBirth){}

