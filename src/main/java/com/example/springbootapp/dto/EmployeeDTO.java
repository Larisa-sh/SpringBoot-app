package com.example.springbootapp.dto;

import com.example.springbootapp.entity.Gender;
import com.example.springbootapp.entity.JobTitle;
import com.example.springbootapp.validation.MinAge;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;


import java.util.Date;

public record EmployeeDTO(Long employeeId,
                          @NotBlank(message = "Name may not be blank")
                          String firstName,
                          @NotBlank(message = "Name may not be blank")
                          String lastName,
                          @NotNull
                          @Min(value = 1, message = "Department ID may not be less than 1")
                          int departmentId,
                          @NotNull(message = "Job title may not be null")
                          @Enumerated(EnumType.STRING)
                          JobTitle jobTitle,
                          @NotNull(message = "Gender may not be null")
                          @Enumerated(EnumType.STRING)
                          Gender gender,
                          @NotNull
                          @MinAge(18)
                          @Past
                          Date dateOfBirth) {

    public boolean isAtLeastOneParamNull(EmployeeDTO employeeDTO) {
        return employeeDTO.firstName == null |
                employeeDTO.lastName == null |
                employeeDTO.departmentId == 0 |
                employeeDTO.jobTitle == null |
                employeeDTO.gender == null |
                employeeDTO.dateOfBirth == null;
    }
}

