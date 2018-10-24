package com.demos.demowebfluxclient.repository;

import com.demos.demowebfluxclient.domain.Quote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {
}