package com.ubots.filmes.service;

import com.ubots.filmes.dto.FilmeEditDTO;
import com.ubots.filmes.exceptions.ApiException;
import com.ubots.filmes.model.Filme;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface FilmeService {

    Filme create(Filme filme);

    Filme update(UUID id, FilmeEditDTO filmeEditDTO) throws ApiException;

    Filme getById(UUID id) throws ApiException;

    Page<Filme> findAll(Pageable pageable);

    void delete(UUID id) throws ApiException;
}
