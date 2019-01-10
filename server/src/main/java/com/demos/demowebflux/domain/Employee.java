package com.demos.demowebflux.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Employee {
    private String id;

    @NonNull
    private String name;

}
