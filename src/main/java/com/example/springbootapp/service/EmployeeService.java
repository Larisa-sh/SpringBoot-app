package com.example.springbootapp.service;

import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.entity.Employee;
import com.example.springbootapp.exception.EmployeeNotFoundException;
import com.example.springbootapp.exception.EmployeeNotSavedException;
import com.example.springbootapp.exception.EmployeeNotUniqueException;
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

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::employeeToEmployeeDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " doesn't exist"));
        return employeeMapper.employeeToEmployeeDTO(employee);
    }

    @Transactional
    public EmployeeDTO saveNewEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.isAtLeastOneParamNull(employeeDTO)) {
            throw new EmployeeNotSavedException("Employee not saved because at least one field is null");
        }
        Optional<Employee> employee = employeeRepository
                .findByLastNameAndFirstName(employeeDTO.lastName(), employeeDTO.firstName());
        if (employee.isPresent()) {
            throw new EmployeeNotUniqueException("Employee already exists");
        }
        employeeRepository.save(employeeMapper.employeeDTOtoEmployee(employeeDTO));
        return employeeDTO;
    }

    @Transactional
    public EmployeeDTO updateEmployee(long employeeId, EmployeeDTO updatedEmployeeDTO) {
        if (updatedEmployeeDTO.isAtLeastOneParamNull(updatedEmployeeDTO)) {
            throw new EmployeeNotSavedException("Employee not saved because at least one field is null");
        }
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " doesn't exist"));

        if (employee.getFirstName().equals(updatedEmployeeDTO.firstName())
                & employee.getLastName().equals(updatedEmployeeDTO.lastName())) {
            employeeMapper.updateEmployeeFromDto(updatedEmployeeDTO, employee);
            employee.setEmployeeId(employeeId);
        } else {
            throw new EmployeeNotSavedException("To update employee with id: "
                    + employeeId + " you must provide the same first and last name as this employee has");
        }
        return updatedEmployeeDTO;
    }

    @Transactional
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
