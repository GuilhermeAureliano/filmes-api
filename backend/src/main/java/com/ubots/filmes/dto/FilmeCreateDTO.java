package com.ubots.filmes.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Getter
public class FilmeCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String director;

    @NotBlank
    private String synopsis;

    @NotBlank
    private String genre;

    @NotBlank
    private String releaseYear;

}
