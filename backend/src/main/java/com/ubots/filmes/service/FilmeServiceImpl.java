package com.ubots.filmes.service;

import com.ubots.filmes.dto.FilmeEditDTO;
import com.ubots.filmes.dto.FilmeResponseDTO;
import com.ubots.filmes.erros.FilmeError;
import com.ubots.filmes.exceptions.ApiException;
import com.ubots.filmes.model.Filme;
import com.ubots.filmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FilmeServiceImpl implements FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Override
    public Filme create(Filme filme) {
        return this.filmeRepository.save(filme);
    }

    @Override
    public Filme update(UUID id, FilmeEditDTO filmeEditDTO) throws ApiException {
        Filme filme = this.getById(id);

        if (filmeEditDTO.getName() != null && !filmeEditDTO.getName().isBlank()) {
            filme.setName(filmeEditDTO.getName());
        }
        if (filmeEditDTO.getDirector() != null && !filmeEditDTO.getDirector().isBlank()) {
            filme.setDirector(filmeEditDTO.getDirector());
        }
        if (filmeEditDTO.getSynopsis() != null && !filmeEditDTO.getSynopsis().isBlank()) {
            filme.setSynopsis(filmeEditDTO.getSynopsis());
        }

        return this.filmeRepository.save(filme);
    }

    @Override
    public Filme getById(UUID id) throws ApiException {
        Optional<Filme> filmeOpt = this.filmeRepository.findById(id);

        if (filmeOpt.isEmpty()) {
            throw FilmeError.errorFilmNotExist();
        }

        return filmeOpt.get();
    }

    @Override
    public Page<Filme> findAll(Pageable pageable) {
        return this.filmeRepository.findAll(pageable);
    }

    @Override
    public void delete(UUID id) throws ApiException {
        Filme filme = this.getById(id);

        this.filmeRepository.delete(filme);
    }

    @Override
    public Page<FilmeResponseDTO> findAllNotEvaluation(Pageable pageable) {
        Page<Filme> filmePage = this.filmeRepository.findAllNotEvaluation(pageable);
        Page<FilmeResponseDTO> response = filmePage.map(FilmeResponseDTO::new);

        return response;
    }
}
