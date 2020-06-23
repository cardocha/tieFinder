package com.cardocha.tiefinder;

import com.cardocha.tiefinder.planeta.Planeta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TieFinderApplicationTests {

    @Test
    void crud(@Autowired WebTestClient webClient) {
        Planeta planeta = Planeta.builder()
                .name("Tatooine")
                .climate("Árido")
                .terrain("Desértico")
                .build();

        //INSERT
        webClient
                .post()
                .uri("/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(planeta), Planeta.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Planeta.class)
                .value(response -> {
                            assertThat(response.getName()).isEqualTo("Tatooine");
                            assertThat(response.getClimate()).isEqualTo("Árido");
                            assertThat(response.getTerrain()).isEqualTo("Desértico");
                            assertThat(response.getFilmCount()).isEqualTo(5);

                            //READ
                            webClient
                                    .get()
                                    .uri("/planetas/" + response.getId())
                                    .exchange()
                                    .expectStatus().isOk()
                                    .expectBody(Planeta.class)
                                    .value(r -> assertThat(r.getFilmCount()).isEqualTo(5));

                            //READ BY NAME
                            webClient
                                    .get()
                                    .uri("/planetas?name=" + response.getName())
                                    .exchange()
                                    .expectStatus().isOk()
                                    .expectBody(String.class)
                                    .value(listagem -> assertThat(listagem.isEmpty()).isFalse());

                            //DELETE
                            webClient
                                    .delete()
                                    .uri("/planetas/" + response.getId())
                                    .exchange()
                                    .expectStatus()
                                    .value(status -> assertThat(status.intValue()).isEqualTo(200));

                            //READ AFTER DELETE
                            webClient
                                    .get()
                                    .uri("/planetas/" + response.getId())
                                    .exchange()
                                    .expectStatus().isOk()
                                    .expectBody(Planeta.class)
                                    .value(planetaRemovido -> assertThat(planetaRemovido).isNull());

                        }
                );
    }

    @Test
    void findAll(@Autowired WebTestClient webClient) {
        webClient
                .get()
                .uri("/planetas")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> assertThat(response.isEmpty()).isFalse());
    }


}
