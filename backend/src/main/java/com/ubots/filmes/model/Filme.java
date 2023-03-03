package com.ubots.filmes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_FILME")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String director;
    @Column(nullable = false)
    private String synopsis;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private LocalDate releaseYear;
}
