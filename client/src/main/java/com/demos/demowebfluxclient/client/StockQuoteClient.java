package com.demos.demowebfluxclient.client;

import com.demos.demowebfluxclient.domain.Quote;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Setter
@Component
@ConfigurationProperties("servertoconnectinfo")
public class StockQuoteClient {
    private String host;
    private String port;
    private String quotespath;

    public Flux<Quote> getQuoteStream(){
        String url = "http://" + host + ":" + port;
        System.out.println("URL del server = "+url);
        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(quotespath)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class);
    }

}
