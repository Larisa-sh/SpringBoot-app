package com.example.springbootapp.controller;


import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.entity.Employee;
import com.example.springbootapp.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getById(@PathVariable("employeeId")
                               @Min(value = 1,
                                       message = "Employee ID can't be less than 1")
                               long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public Employee saveNewEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.saveNewEmployee(employeeDTO);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId")
                                   @Min(value = 1,
                                           message = "Employee ID can't be less than 1")
                                   long employeeId,
                                   @Valid
                                   @RequestBody
                                   EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeId, employeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId")
                               @Min(value = 1,
                                       message = "Employee ID can't be less than 1")
                               long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
