package com.demos.demowebflux.bootstrap;

import com.demos.demowebflux.client.StockQuoteClient;
import com.demos.demowebflux.domain.Activity;
import com.demos.demowebflux.domain.Employee;
import com.demos.demowebflux.domain.Quote;
import com.demos.demowebflux.repositories.ActivityRepository;
import com.demos.demowebflux.repositories.EmployeeRepository;
import com.demos.demowebflux.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final ActivityRepository activityRepository;
    private final EmployeeRepository employeeRepository;

    private final QuoteService quoteService;

    @Autowired
    StockQuoteClient stockQuoteClient;

    public BootstrapCLR(ActivityRepository activityRepository, EmployeeRepository employeeRepository, QuoteService quoteService){
        this.activityRepository = activityRepository;
        this.employeeRepository = employeeRepository;
        this.quoteService = quoteService;
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

        employeeRepository.deleteAll()
            .thenMany(
               Flux.just("Shopping", "Go to the gym", "Feed the kids", "Study Java")
                    .map(name -> new Employee(name))
                    .flatMap(employeeRepository::save)
            )
            .subscribe((s)->{
                System.out.println("BORRRADO == "+s);
            });


        System.out.println("=================ARRANCANDO========================");
        Flux<Quote> quoteFlux = stockQuoteClient.getQuoteStream();
        quoteFlux
            .take(50)
            .subscribe(quote -> {
                    System.out.println("INICIANDO. Quote = "+quote);
                    Mono<Quote> savedQuote = quoteService.save(quote);

                    savedQuote.subscribe(s -> System.out.println("!!!!Saved quote "+s.getId()));

                    //System.out.println("Mono obtenido = "+savedQuote);
                    //System.out.println("!!!!Saved quote "+savedQuote.block().getId());

                }
            );


    }
}
