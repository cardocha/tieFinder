package com.cardocha.tiefinder.planeta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PlanetaRouter {

    @Bean
    public RouterFunction<ServerResponse> route(PlanetaHandler planetaHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/planetas")
                                .and(RequestPredicates.accept(MediaType.ALL))
                                .and(RequestPredicates.queryParam("nome", StringUtils::hasText))
                        , planetaHandler::findByName)
                .andRoute(RequestPredicates.GET("/planetas")
                        .and(RequestPredicates.accept(MediaType.ALL)), planetaHandler::getTodos)
                .andRoute(RequestPredicates.POST("/planetas")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), planetaHandler::salvar)
                .andRoute(RequestPredicates.DELETE("/planetas/{id}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), planetaHandler::remover)
                .andRoute(RequestPredicates.GET("/planetas/{id}")
                        .and(RequestPredicates.accept(MediaType.ALL)), planetaHandler::getPlaneta);
    }
}