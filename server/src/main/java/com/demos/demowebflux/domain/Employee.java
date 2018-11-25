package com.demos.demowebflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {
    private String id;

    @NonNull
    private String name;

}
