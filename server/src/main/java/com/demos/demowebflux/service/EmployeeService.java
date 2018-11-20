package com.demos.demowebflux.service;

import com.demos.demowebflux.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Flux<Employee> getAllEmployees();
    Mono<Employee> findById(String id);
}
