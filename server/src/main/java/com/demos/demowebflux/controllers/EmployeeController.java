package com.demos.demowebflux.controllers;

import com.demos.demowebflux.domain.Employee;
import com.demos.demowebflux.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    final private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Employee> save(@RequestBody Employee employee){
        return this.employeeService.save(employee);
    }

}
