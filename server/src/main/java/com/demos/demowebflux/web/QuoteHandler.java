package com.demos.demowebflux.web;

import com.demos.demowebflux.client.StockQuoteClient;
import com.demos.demowebflux.domain.Quote;
import com.demos.demowebflux.service.QuoteGeneratorService;
import com.demos.demowebflux.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class QuoteHandler {
    private final QuoteGeneratorService quoteGeneratorService;
    private final QuoteService quoteService;

    public QuoteHandler(QuoteGeneratorService quoteGeneratorService, QuoteService quoteService) {
        this.quoteGeneratorService = quoteGeneratorService;
        this.quoteService = quoteService;
    }

    public Mono<ServerResponse> fetchQuotes(ServerRequest request){
        int size = Integer.parseInt(request.queryParam("size").orElse("50"));

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100))
                        .take(size), Quote.class);
    }

    public Mono<ServerResponse> streamQuotes(ServerRequest request){
        return ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(this.quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100)), Quote.class);
    }



    public Mono<ServerResponse> getAllQuotes(ServerRequest request){
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.quoteService.getAllQuotes(), Quote.class);
    }

}
