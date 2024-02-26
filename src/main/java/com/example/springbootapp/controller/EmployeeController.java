package com.example.springbootapp.controller;


import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.entity.Employee;
import com.example.springbootapp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        LOGGER.info(employeeService.getAllEmployees().toString());
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getById(@PathVariable("employeeId") long employeeId) {
        LOGGER.info(employeeService.getEmployeeById(employeeId).toString());
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public Employee saveNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        LOGGER.info(employeeDTO.toString());
        return employeeService.saveNewEmployee(employeeDTO);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId") long employeeId, @RequestBody EmployeeDTO employeeDTO) {
        LOGGER.info(employeeDTO.toString());
        return employeeService.updateEmployee(employeeId, employeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") long employeeId) {
        LOGGER.info(employeeService.getEmployeeById(employeeId).toString());
        employeeService.deleteEmployee(employeeId);
    }
}
