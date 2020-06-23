package com.cardocha.tiefinder.planeta;

import com.cardocha.tiefinder.BaseStarWarsWebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class PlanetaWebClient extends BaseStarWarsWebClient {

    public Mono<String> getFilmCount(Planeta planeta) {
        log.info("Consultando API StarWars.");

        return getWebClient()
                .get()
                .uri("?search=" + planeta.getName().toLowerCase())
                .retrieve()
                .bodyToMono(String.class);
    }

    public static Integer parse(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode consultaApi = mapper.readTree(response);
        int resultCount = consultaApi.get("count").asInt();
        if (resultCount > 0) {
            List<JsonNode> resultados = consultaApi.findValues("results");
            JsonNode planetaCadastrado = Iterables.getFirst(resultados, null);
            int filmCount = planetaCadastrado.findValue("films").size();
            log.info("Planeta encontrado, Aparições em filmes: " + filmCount);
            return filmCount;
        } else {
            log.info("Planeta não encontrado");
        }

        return 0;
    }

}
