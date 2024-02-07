package com.example.springbootapp.service;

import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.entity.Employee;
import com.example.springbootapp.mapper.EmployeeMapper;
import com.example.springbootapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDTO> showAllEmployees() {

        return employeeRepository.findAll().stream().map(employeeMapper::employeeToEmployeeDTO).collect(Collectors.toList());
    }

    public EmployeeDTO showEmployeeById(long id) {
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        return employeeMapper.employeeToEmployeeDTO(foundEmployee.get());
    }
    @Transactional
    public Employee saveNewEmployee(EmployeeDTO employeeDTO) {
        return employeeRepository.save(employeeMapper.employeeDTOtoEmployee(employeeDTO));
    }

    @Transactional
    public Employee updateEmployee(long employeeId, EmployeeDTO updatedEmployeeDTO) {
        Employee employee = employeeRepository.findById(employeeId).get();
        employeeMapper.updateEmployeeFromDto(updatedEmployeeDTO, employee);
        employee.setEmployeeId(employeeId);
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
