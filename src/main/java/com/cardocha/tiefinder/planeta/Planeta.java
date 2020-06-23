package com.cardocha.tiefinder.planeta;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Planeta {

    @PrimaryKey
    private UUID id;

    @Column
    @Indexed
    private String name;

    @Column
    private String climate;

    @Column
    private String terrain;

    @Column
    private Integer filmCount;

    public static Planeta of(Integer filmCount, Planeta planeta) {
        Planeta novo = new Planeta();
        novo.id = planeta.getId();
        novo.name = planeta.getName();
        novo.climate = planeta.getClimate();
        novo.terrain = planeta.getTerrain();
        novo.filmCount = filmCount;
        return novo;
    }

}
