package com.ubots.filmes.service;

import com.ubots.filmes.dto.FilmeEditDTO;
import com.ubots.filmes.exceptions.ApiException;
import com.ubots.filmes.model.Filme;

import java.util.UUID;

public interface FilmeService {

    Filme create(Filme filme);

    Filme update(UUID id, FilmeEditDTO filmeEditDTO) throws ApiException;

    Filme getById(UUID id) throws ApiException;
}
