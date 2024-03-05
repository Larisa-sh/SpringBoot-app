package com.example.springbootapp.dto;

import com.example.springbootapp.entity.Gender;
import com.example.springbootapp.entity.JobTitle;
import jakarta.validation.constraints.NotNull;


import java.util.Date;

public record EmployeeDTO(Long employeeId,
                          @NotNull
                          String firstName,
                          @NotNull
                          String lastName,
                          @NotNull
                          int departmentId,
                          @NotNull
                          JobTitle jobTitle,
                          @NotNull
                          Gender gender,
                          @NotNull
                          @MinAge(18)
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

