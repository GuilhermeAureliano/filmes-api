package com.ubots.filmes.repository;

import com.ubots.filmes.model.Filme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FilmeRepository extends JpaRepository<Filme, UUID> {

    @Query("select f from Filme f left join f.avaliacoes a where a is null")
    Page<Filme> findAllNotEvaluation(Pageable pageable);
}
