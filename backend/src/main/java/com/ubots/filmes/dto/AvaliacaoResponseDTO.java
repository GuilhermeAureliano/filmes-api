package com.ubots.filmes.dto;

import com.ubots.filmes.model.Avaliacao;
import com.ubots.filmes.model.Filme;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AvaliacaoResponseDTO {

    private final UUID id;

    private final Integer grade;

    private final String comment;

    private final String nameFilm;

    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.grade = avaliacao.getGrade();
        this.comment = avaliacao.getComment();
        this.nameFilm = avaliacao.getFilme().getName();
    }
}
