package com.demos.demowebflux.service;

import com.demos.demowebflux.domain.Activity;
import com.demos.demowebflux.domain.ActivityEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActivityService {

    Flux<ActivityEvent> events(String activityId);
    Mono<Activity> getActivityById(String id);
    Flux<Activity> getAllActivities();
}
