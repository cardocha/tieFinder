package com.cardocha.tiefinder.planeta;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class PlanetaHandler {

    private final PlanetaRepository repository;

    public PlanetaHandler(PlanetaRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getTodos(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(repository.findAll(), Planeta.class);
    }

    public Mono<ServerResponse> salvar(ServerRequest request) {
        Mono<Planeta> planeta = request.bodyToMono(Planeta.class);
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(repository.saveAll(planeta), Planeta.class);
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

    public Mono<ServerResponse> findByNome(ServerRequest request) {
        String nome = request.pathVariable("nome");
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(repository.findAllByNomeLike(nome), Planeta.class);
    }
}
