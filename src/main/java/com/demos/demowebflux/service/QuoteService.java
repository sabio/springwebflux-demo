package com.demos.demowebflux.service;

import com.demos.demowebflux.domain.Quote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuoteService {
    Flux<Quote> getAllQuotes();
    Mono<Quote> save(Quote quote);
}
