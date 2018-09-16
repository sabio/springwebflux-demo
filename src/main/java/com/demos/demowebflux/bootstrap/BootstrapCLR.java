package com.demos.demowebflux.bootstrap;

import com.demos.demowebflux.domain.Activity;
import com.demos.demowebflux.repositories.ActivityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final ActivityRepository activityRepository;

    public BootstrapCLR(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        activityRepository.save(new Activity("My activity 1",  LocalDateTime.now())).subscribe();
        activityRepository.save(new Activity("My activity 2",  LocalDateTime.now())).subscribe();
        activityRepository.save(new Activity("My activity 3",  LocalDateTime.now())).subscribe();
        */
        activityRepository.deleteAll()
            .thenMany(
                Flux.just("Shopping", "Go to the gym", "Feed the kids", "Study Java")
                    .map(activityName -> new Activity(activityName, LocalDateTime.now()))
                    .flatMap(activityRepository::save))
            .subscribe(null, null, () -> {
                activityRepository.findAll().subscribe(System.out::println);
            });
    }
}
