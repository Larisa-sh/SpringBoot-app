package com.example.springbootapp.dto;

import com.example.springbootapp.entity.Gender;
import com.example.springbootapp.entity.JobTitle;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "Employee Data Transfer Object")
public record EmployeeDTO(@Schema(accessMode = Schema.AccessMode.READ_ONLY,
                                  description = "Employee ID")
                          Long employeeId,

                          @Schema(description = "First name",
                                  example = "Mark",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          String firstName,

                          @Schema(description = "Last name",
                                  example = "Stone",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          String lastName,

                          @Schema(description = "ID of employee's department",
                                  example = "2",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          int departmentId,

                          @Schema(description = "Job title",
                                  example = "PROGRAMMER",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          JobTitle jobTitle,

                          @Schema(description = "Gender",
                                  example = "MALE",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          Gender gender,

                          @Schema(description = "Date of birth. Employee must be elder than 18 years.",
                                  example = "2024-03-06",
                                  format = "date",
                                  type = "string",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
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

