package com.ubots.filmes.controller;

import com.ubots.filmes.dto.FilmeCreateDTO;
import com.ubots.filmes.dto.FilmeResponseDTO;
import com.ubots.filmes.model.Filme;
import com.ubots.filmes.service.FilmeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody FilmeCreateDTO filmeCreateDTO) {
        Filme createdFilme = new Filme();
        BeanUtils.copyProperties(filmeCreateDTO, createdFilme);
        createdFilme.setReleaseYear(LocalDate.parse(filmeCreateDTO.getReleaseYear(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.filmeService.create(createdFilme);
        FilmeResponseDTO response = new FilmeResponseDTO(createdFilme);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
