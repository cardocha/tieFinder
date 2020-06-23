package com.cardocha.tiefinder.planeta;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class PlanetaRepositoryImpl implements PlanetaRepositoryCustom {

    private final CassandraTemplate cassandraTemplate;

    @Autowired
    public PlanetaRepositoryImpl(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public Flux<Planeta> findAllByName(String nome) {
        Select select = QueryBuilder.select().from("planeta");
        select.where(QueryBuilder.like("name", "%" + nome + "%"));
        return Flux.fromIterable(cassandraTemplate.select(select, Planeta.class));
    }

}
