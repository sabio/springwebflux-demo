package com.demos.demowebflux.repositories;

import com.demos.demowebflux.domain.Quote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String>{
}
