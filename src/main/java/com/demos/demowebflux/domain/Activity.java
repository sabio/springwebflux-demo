package com.demos.demowebflux.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Activity {
    private String id;

    @NonNull
    private String name;

    @NonNull
    private LocalDateTime date;
}
