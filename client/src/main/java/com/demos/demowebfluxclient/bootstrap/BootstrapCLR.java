package com.demos.demowebfluxclient.bootstrap;

import com.demos.demowebfluxclient.client.StockQuoteClient;
import com.demos.demowebfluxclient.domain.Quote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class BootstrapCLR implements CommandLineRunner {

    @Autowired
    StockQuoteClient stockQuoteClient;

    @Override
    public void run(String... args) throws Exception {
        log.debug("===========Bootstraping===========");

        try {
            Flux<Quote> quoteFlux = stockQuoteClient.getQuoteStream();
            quoteFlux
                    .take(20L)
                    .subscribe(quote -> {
                        System.out.println("Quote = " + quote);
                    });


            Flux
        }catch(Exception e){
            log.debug("=======================ERROR AL INVOCAR SERVIDOR");
        }
    }
}
