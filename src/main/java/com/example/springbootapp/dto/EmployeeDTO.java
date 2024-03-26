package com.example.springbootapp.dto;

import com.example.springbootapp.entity.Gender;
import com.example.springbootapp.entity.JobTitle;
import io.swagger.v3.oas.annotations.media.Schema;
import com.example.springbootapp.validation.MinAge;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;


import java.util.Date;

@Schema(description = "Employee Data Transfer Object")
public record EmployeeDTO(@Schema(accessMode = Schema.AccessMode.READ_ONLY,
                                  description = "Employee ID")
                          Long employeeId,

                          @Schema(description = "First name",
                                  example = "Mark",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotBlank(message = "Name may not be blank")
                          String firstName,

                          @Schema(description = "Last name",
                                  example = "Stone",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotBlank(message = "Name may not be blank")
                          String lastName,

                          @Schema(description = "ID of employee's department",
                                  example = "2",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotNull
                          @Min(value = 1, message = "Department ID may not be less than 1")
                          int departmentId,

                          @Schema(description = "Job title",
                                  example = "PROGRAMMER",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotNull(message = "Job title may not be null")
                          @Enumerated(EnumType.STRING)
                          JobTitle jobTitle,

                          @Schema(description = "Gender",
                                  example = "MALE",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotNull(message = "Gender may not be null")
                          @Enumerated(EnumType.STRING)
                          Gender gender,

                          @Schema(description = "Date of birth. Employee must be elder than 18 years.",
                                  example = "2024-03-06",
                                  format = "date",
                                  type = "string",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
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

