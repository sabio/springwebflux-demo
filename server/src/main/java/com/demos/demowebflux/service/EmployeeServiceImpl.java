package com.demos.demowebflux.service;

import com.demos.demowebflux.domain.Employee;
import com.demos.demowebflux.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Flux<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Mono<Employee> findById(String id) {
        return this.employeeRepository.findById(id);
    }
}
