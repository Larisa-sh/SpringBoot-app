package com.example.springbootapp.dto;

import com.example.springbootapp.entity.Gender;
import com.example.springbootapp.entity.JobTitle;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "Employee Data Transfer Object")
public record EmployeeDTO(@Schema(accessMode = Schema.AccessMode.READ_ONLY,
        description = "Employee ID")
                          Long employeeId,

                          @Schema(description = "First name", example = "Mark")
                          String firstName,

                          @Schema(description = "Last name", example = "Stone")
                          String lastName,

                          @Schema(description = "ID of employee's department", example = "2")
                          int departmentId,

                          @Schema(description = "Job title", example = "PROGRAMMER")
                          JobTitle jobTitle,

                          @Schema(description = "Gender", example = "MALE")
                          Gender gender,

                          @Schema(description = "Date of birth. Employee must be elder than 18 years.",
                                  example = "2024-03-06",
                                  format = "date",
                                  type = "string")
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

