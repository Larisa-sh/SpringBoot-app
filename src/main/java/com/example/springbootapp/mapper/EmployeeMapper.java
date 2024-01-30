package com.example.springbootapp.mapper;

import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.entity.Employee;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDTO employeeToEmployeeDTO(Employee employee);
    Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO);

}
