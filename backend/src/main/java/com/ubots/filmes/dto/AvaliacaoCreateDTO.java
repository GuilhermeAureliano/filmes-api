package com.ubots.filmes.dto;

import com.ubots.filmes.utils.NotaEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class AvaliacaoCreateDTO {


    private NotaEnum nota;

    @NotBlank
    private String comment;
}
