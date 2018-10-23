package com.demos.demowebflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityEvent {
    @NonNull
    private String name;

    @NonNull
    private LocalDateTime date;
}
