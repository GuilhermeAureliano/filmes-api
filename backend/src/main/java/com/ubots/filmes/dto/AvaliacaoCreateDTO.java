package com.ubots.filmes.dto;

import com.ubots.filmes.utils.NotaEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class AvaliacaoCreateDTO {
    private NotaEnum notaEnum;
    private String comment;

    public NotaEnum getNotaEnum() {
        return notaEnum;
    }

    public void setNotaEnum(NotaEnum notaEnum) {
        this.notaEnum = notaEnum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

