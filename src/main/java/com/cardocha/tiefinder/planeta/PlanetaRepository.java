package com.cardocha.tiefinder.planeta;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface PlanetaRepository extends ReactiveCassandraRepository<Planeta, UUID>, PlanetaRepositoryCustom {

    Flux<Planeta> findAllByName(String nome);
}
