package com.demos.demowebflux.controllers;

import com.demos.demowebflux.domain.Employee;
import com.demos.demowebflux.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    final private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Flux<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }


}
