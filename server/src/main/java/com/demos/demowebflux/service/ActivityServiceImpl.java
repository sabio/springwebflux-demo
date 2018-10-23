package com.demos.demowebflux.service;

import com.demos.demowebflux.domain.Activity;
import com.demos.demowebflux.domain.ActivityEvent;
import com.demos.demowebflux.repositories.ActivityRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Flux<ActivityEvent> events(String activityId) {
        return Flux.<ActivityEvent>generate(activityEventSynchronousSink -> {
            activityEventSynchronousSink.next(new ActivityEvent("Titulo del evento", LocalDateTime.now()));
        }).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Activity> getActivityById(String id) {
        return this.activityRepository.findById(id);
    }

    @Override
    public Flux<Activity> getAllActivities() {
        return this.activityRepository.findAll();
    }
}
