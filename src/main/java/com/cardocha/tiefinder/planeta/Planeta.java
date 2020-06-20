package com.cardocha.tiefinder.planeta;

import com.datastax.driver.core.utils.UUIDs;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Table
public class Planeta {

    @PrimaryKey
    private UUID id;

    @Column
    private String nome;

    @Column
    private String clima;

    @Column
    private String terreno;

    public Planeta(String nome, String clima, String terreno) {
        this.id = UUIDs.timeBased();
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }
}
