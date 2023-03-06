package com.ubots.filmes.service;

import com.ubots.filmes.model.Avaliacao;
import com.ubots.filmes.model.Filme;
import com.ubots.filmes.repository.AvaliacaoRepository;
import com.ubots.filmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoImpl implements AvaliacaoService{

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Override
    public Avaliacao addEvaluation(Avaliacao avaliacao) {
        this.avaliacaoRepository.save(avaliacao);

        Filme filme = avaliacao.getFilme();
        filme.updateMediaAvaliacoes();
        this.filmeRepository.save(filme);

        return avaliacao;
    }
}
