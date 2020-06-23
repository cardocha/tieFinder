package com.cardocha.tiefinder.planeta;

import com.datastax.driver.core.utils.UUIDs;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Getter
@Setter
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

    public Planeta() {
    }

    public Planeta(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.filmCount = 0;
    }

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
