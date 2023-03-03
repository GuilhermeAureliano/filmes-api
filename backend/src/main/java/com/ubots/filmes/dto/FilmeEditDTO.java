package com.ubots.filmes.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class FilmeEditDTO {

    private String name;
    private String director;
    private String synopsis;
}
