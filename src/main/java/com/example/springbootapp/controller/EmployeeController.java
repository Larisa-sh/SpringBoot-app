package com.example.springbootapp.controller;


import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "Interaction with employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(summary = "Shows a list of all employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    @Operation(summary = "Shows an employee by their ID")
    public EmployeeDTO getById(@PathVariable("employeeId")
                               @Parameter(description = "Employee's ID", required = true) long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    @Operation(summary = "Saves new employee",
            description = "To save new employee you must provide unique First and Last name")
    public EmployeeDTO saveNewEmployee(@RequestBody
                                       @Parameter(description = "Employee's DTO", required = true) EmployeeDTO employeeDTO) {
        return employeeService.saveNewEmployee(employeeDTO);
    }

    @PutMapping("/{employeeId}")
    @Operation(summary = "Updates an employee by their ID",
            description = "To update employee you must provide the same First and Last name as this employee has")
    public EmployeeDTO updateEmployee(@PathVariable("employeeId")
                                      @Parameter(description = "Employee's ID", required = true) long employeeId,
                                      @RequestBody @Parameter(description = "Employee's DTO") EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeId, employeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    @Operation(summary = "Deletes an employee by their ID")
    public void deleteEmployee(@PathVariable("employeeId")
                               @Parameter(description = "Employee's ID", required = true) long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
