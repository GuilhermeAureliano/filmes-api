package com.ubots.filmes.service;

import com.ubots.filmes.model.Filme;
import com.ubots.filmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeServiceImpl implements FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Override
    public Filme create(Filme filme) {
        return this.filmeRepository.save(filme);
    }
}
