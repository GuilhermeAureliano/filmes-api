package com.ubots.filmes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ubots.filmes.model.Filme;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class FilmeResponseDTO {

    private final UUID id;

    private final String name;

    private final String director;

    private final String synopsis;

    private final String genre;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDate releaseYear;

    private final Double mediaAvaliacoes;

    public FilmeResponseDTO(Filme filme) {
        this.id = filme.getId();
        this.name = filme.getName();
        this.director = filme.getDirector();
        this.synopsis = filme.getSynopsis();
        this.genre = filme.getGenre();
        this.releaseYear = filme.getReleaseYear();
        this.mediaAvaliacoes = filme.getMediaAvaliacoes();
    }

}
