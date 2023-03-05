package com.ubots.filmes.services;

import com.ubots.filmes.dto.FilmeEditDTO;
import com.ubots.filmes.exceptions.ApiException;
import com.ubots.filmes.model.Filme;
import com.ubots.filmes.repository.FilmeRepository;
import com.ubots.filmes.service.FilmeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class FilmeServiceTest {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private FilmeRepository filmeRepository;

    private Filme filme;

    private void criaFilme() {
        this.filme = new Filme();
        filme.setName("O Senhor dos Anéis");
        filme.setDirector("Peter Jackson");
        filme.setSynopsis("Um jovem querendo proteger um anel com poderes.");
        filme.setGenre("Aventura");
        filme.setReleaseYear(LocalDate.parse("2001-12-19"));
        filme.setAvaliacoes(null);
        this.filmeRepository.save(filme);
    }

    @Test
    public void create() throws ApiException {
        this.criaFilme();

        assertTrue(this.filmeRepository.findById(filme.getId()).isPresent());

        this.filmeRepository.delete(filme);
    }

    @Test
    public void getById() throws ApiException {
        this.criaFilme();

        assertEquals("Aventura", filme.getGenre());
        assertEquals("2001-12-19", String.valueOf(filme.getReleaseYear()));

        try {
            // Filme não cadastrado
            this.filmeService.getById(UUID.fromString("a39fed6f-55bd-412f-b84c-31c9623f6f49"));
        } catch (ApiException e) {
            assertEquals("Filme não encontrado!", e.getMessage());
        }

        this.filmeRepository.delete(filme);
    }

    @Test
    public void deleteById() throws ApiException {
        this.criaFilme();

        UUID idTest = filme.getId();
        this.filmeService.delete(filme.getId());
        Optional<Filme> filmeOpt = this.filmeRepository.findById(idTest);
        assertFalse(filmeOpt.isPresent());

        try {
            // Filme não cadastrado
            this.filmeService.delete(UUID.fromString("1f8a3082-7dd6-4d4b-9a9f-2e43e66f77d0"));
        } catch (ApiException e) {
            assertEquals("Filme não encontrado!", e.getMessage());
        }

        this.filmeRepository.delete(filme);
    }

    @Test
    public void update() throws ApiException {
        this.criaFilme();
        FilmeEditDTO filmeEditDTO = new FilmeEditDTO("Homem Aranha", "", "Primeiro filme da saga homem aranha!");

        Filme filme = this.filmeService.update(this.filme.getId(), filmeEditDTO);
        assertEquals("Homem Aranha", filme.getName());
        assertEquals("Peter Jackson", filme.getDirector());
        assertEquals("Primeiro filme da saga homem aranha!", filme.getSynopsis());

        this.filmeRepository.delete(filme);
    }
}
