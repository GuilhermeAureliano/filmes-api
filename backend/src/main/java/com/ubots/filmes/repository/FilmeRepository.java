package com.ubots.filmes.repository;

import com.ubots.filmes.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmeRepository extends JpaRepository<Filme, UUID> {
}
