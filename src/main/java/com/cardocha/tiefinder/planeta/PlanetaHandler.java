package com.cardocha.tiefinder.planeta;

import com.datastax.driver.core.utils.UUIDs;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.cardocha.tiefinder.planeta.PlanetaWebClient.parse;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
@Transactional
public class PlanetaHandler {

    private final PlanetaRepository repository;

    public PlanetaHandler(PlanetaRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getTodos(ServerRequest request) {
        Flux<Planeta> planetas = repository.findAll();
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(planetas, Planeta.class);
    }

    public Mono<ServerResponse> salvar(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(fetchFilmCountAntSave(request), Planeta.class);
    }

    public Mono<ServerResponse> remover(ServerRequest request) {
        UUID planetaId = UUID.fromString(request.pathVariable("id"));
        Mono<Planeta> planeta = repository.findById(planetaId);
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(repository.deleteAll(planeta), Planeta.class);
    }

    public Mono<ServerResponse> getPlaneta(ServerRequest request) {
        UUID planetaId = UUID.fromString(request.pathVariable("id"));
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(repository.findById(planetaId), Planeta.class);
    }

    public Mono<ServerResponse> findByName(ServerRequest request) {
        String nome = request.queryParam("nome").orElse("");
        Flux<Planeta> planetas = repository.findAllByName(nome);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(planetas, Planeta.class);
    }

    private Mono<Planeta> fetchFilmCountAntSave(ServerRequest request) {
        UUID id = UUIDs.timeBased();
        return request
                .bodyToMono(Planeta.class)
                .flatMap(planeta -> {
                    planeta.setId(id);
                    return repository.save(planeta);
                })
                .flatMap(planeta -> new PlanetaWebClient().getFilmCount(planeta))
                .flatMap(response -> {
                    try {
                        return Mono.just(parse(response));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return Mono.just(0);
                    }
                })
                .flatMap(filmCount -> repository.findById(id).map(p -> Planeta.of(filmCount, p)))
                .flatMap(repository::save);
    }

}
