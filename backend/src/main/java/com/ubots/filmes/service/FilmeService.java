package com.ubots.filmes.service;

import com.ubots.filmes.dto.FilmeEditDTO;
import com.ubots.filmes.model.Filme;

import java.util.UUID;

public interface FilmeService {

    Filme create(Filme filme);

    Filme update(UUID id, FilmeEditDTO filmeEditDTO);

    Filme getById(UUID id);
}
