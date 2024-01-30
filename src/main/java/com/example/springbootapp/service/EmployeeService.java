package com.example.springbootapp.service;

import com.example.springbootapp.entity.Employee;
import com.example.springbootapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> showAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee showEmployeeById(long id) {
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        return foundEmployee.orElse(null);
    }
    @Transactional
    public Employee saveNewEmployee(Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(long employeeId, Employee updatedEmployee) {
        updatedEmployee.setEmployeeId(employeeId);
        return employeeRepository.save(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
