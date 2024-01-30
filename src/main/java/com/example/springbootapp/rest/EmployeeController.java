package com.example.springbootapp.rest;


import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.entity.Employee;
import com.example.springbootapp.mapper.EmployeeMapper;
import com.example.springbootapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.showAllEmployees().stream().map(employeeMapper::employeeToEmployeeDTO).collect(Collectors.toList());
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getOneEmployee(@PathVariable("employeeId") long employeeId){

        return EmployeeMapper.INSTANCE.employeeToEmployeeDTO(employeeService.showEmployeeById(employeeId));
    }

    @PostMapping
    public Employee saveNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.saveNewEmployee(EmployeeMapper.INSTANCE.employeeDTOtoEmployee(employeeDTO));
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId") long employeeId, @RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeService.showEmployeeById(employeeId);
        LocalDateTime updatedDateTime = employee.getCreatedAt();
        employee = employeeMapper.employeeDTOtoEmployee(employeeDTO);
        employee.setCreatedAt(updatedDateTime);
        return employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") long employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
