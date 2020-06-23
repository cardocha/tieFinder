package com.cardocha.tiefinder;

import org.springframework.web.reactive.function.client.WebClient;

public class BaseStarWarsWebClient {

    WebClient webClient;

    public BaseStarWarsWebClient() {
        webClient = WebClient.create("https://swapi.dev/api/planets/");
    }

    protected WebClient getWebClient() {
        return webClient;
    }
}
