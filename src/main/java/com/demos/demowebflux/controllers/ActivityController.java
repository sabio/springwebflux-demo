package com.demos.demowebflux.controllers;

import com.demos.demowebflux.domain.Activity;
import com.demos.demowebflux.domain.ActivityEvent;
import com.demos.demowebflux.service.ActivityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }


    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ActivityEvent> streamActivityEvents(@PathVariable String id){
       return activityService.events(id);
    }

    @GetMapping(value = "/{id}")
    Mono<Activity> getActivityById(@PathVariable String id){
        return activityService.getActivityById(id);
    }

    @GetMapping
    Flux<Activity> getAllActivities(){
        return activityService.getAllActivities();
    }
}
