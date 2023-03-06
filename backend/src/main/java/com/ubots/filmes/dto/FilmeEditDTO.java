package com.ubots.filmes.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Getter
public class FilmeEditDTO {

    private String name;
    private String director;
    private String synopsis;
}
