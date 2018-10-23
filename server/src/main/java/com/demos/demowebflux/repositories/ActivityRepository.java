package com.demos.demowebflux.repositories;

import com.demos.demowebflux.domain.Activity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ActivityRepository extends ReactiveMongoRepository<Activity, String> {
}
