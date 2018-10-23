package com.demos.demowebflux.service;

import com.demos.demowebflux.domain.Quote;
import com.demos.demowebflux.repositories.QuoteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuoteServiceImpl implements QuoteService{

    private final QuoteRepository quoteRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Flux<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Mono<Quote> save(Quote quote) {
        return quoteRepository.save(quote);
    }
}
