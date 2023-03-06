package com.ubots.filmes.controllers;


import com.ubots.filmes.controller.FilmeController;
import com.ubots.filmes.dto.FilmeCreateDTO;
import com.ubots.filmes.exceptions.ApiException;
import com.ubots.filmes.model.Avaliacao;
import com.ubots.filmes.model.Filme;
import com.ubots.filmes.service.AvaliacaoService;
import com.ubots.filmes.service.FilmeService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@WebMvcTest(FilmeController.class)
public class FilmeControllerTest {

    @Autowired
    private FilmeController filmeController;
    @MockBean
    private FilmeService filmeService;
    @MockBean
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.filmeController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarFilme() throws ApiException {
        when(this.filmeService.getById(UUID.fromString("201ee5b0-0744-445c-b746-dee83c5d563c")))
                .thenReturn(new Filme(UUID.fromString("201ee5b0-0744-445c-b746-dee83c5d563c"), "John Wick", "Fulano de Sal", "Um homem solitário que é luta muito!e", "Ação", LocalDate.of(2014, 11, 27), new ArrayList<Avaliacao>(), 4.0));


        given()
                .accept(ContentType.JSON)
                .when()
                .get("/filmes/{id}", UUID.fromString("201ee5b0-0744-445c-b746-dee83c5d563c"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoCriarFilme() throws ApiException {
        FilmeCreateDTO createDTO = new FilmeCreateDTO("Matrix", "Irmãos Wachowski", "Um programador descobre a verdade sobre a realidade", "Ficção",
                "31/03/1999");

        Filme filmeCriado = new Filme(UUID.randomUUID(), "Matrix", "Irmãos Wachowski", "Um programador descobre a verdade sobre a realidade", "Ação", LocalDate.of(2014, 11, 27), new ArrayList<Avaliacao>(), 4.0);
        when(filmeService.create(any(Filme.class))).thenReturn(filmeCriado);

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .post("/filmes/create")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("Matrix"))
                .body("director", equalTo("Irmãos Wachowski"))
                .body("synopsis", equalTo("Um programador descobre a verdade sobre a realidade"))
                .body("genre", equalTo("Ficção"))
                .body("releaseYear", equalTo("31/03/1999"));
    }

    @Test
    public void deveRetornarNoContent_QuandoDeletarFilme() throws ApiException {
        UUID id = UUID.randomUUID();

        doNothing().when(filmeService).delete(id);

        given()
                .when()
                .delete("/filmes/{id}", id)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
