package com.example.springbootapp.rest;


import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.entity.Employee;
import com.example.springbootapp.mapper.EmployeeMapper;
import com.example.springbootapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.showAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getOneEmployee(@PathVariable("employeeId") long employeeId){
        return employeeService.showEmployeeById(employeeId);
    }

    @PostMapping
    public Employee saveNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.saveNewEmployee(employeeDTO);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId") long employeeId, @RequestBody EmployeeDTO employeeDTO){;
        return employeeService.updateEmployee(employeeId, employeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") long employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
