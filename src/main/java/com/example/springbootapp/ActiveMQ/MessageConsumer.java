package com.example.springbootapp.ActiveMQ;

import com.example.springbootapp.dto.EmployeeDTO;
import com.example.springbootapp.service.EmployeeService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final EmployeeService employeeService;
    private final ObjectMapper jacksonObjectMapper;

    public MessageConsumer(EmployeeService employeeService, ObjectMapper jacksonObjectMapper) {
        this.employeeService = employeeService;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    @JmsListener(destination = "Employee.queue")
    public void receiveMessage(Message message) throws JsonProcessingException {
        String s = message.text();
        EmployeeDTO employeeDTO = jacksonObjectMapper.readValue(s, EmployeeDTO.class);
        employeeService.updateEmployee(employeeDTO.employeeId(), employeeDTO);
    }
}
