package com.cardocha.tiefinder.planeta;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PlanetaRepositoryCustom {

    Flux<Planeta> findAllByName(String nome);
}
