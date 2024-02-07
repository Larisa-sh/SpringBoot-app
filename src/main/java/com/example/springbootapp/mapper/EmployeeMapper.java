package com.example.springbootapp.mapper;

import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;

@Mapper(componentModel = "spring",imports= {LocalDate.class})
public interface EmployeeMapper {

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateEmployeeFromDto(EmployeeDTO employeeDTO, @MappingTarget Employee employee);


}
