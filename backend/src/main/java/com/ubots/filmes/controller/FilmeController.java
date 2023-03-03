package com.ubots.filmes.controller;

import com.ubots.filmes.dto.FilmeCreateDTO;
import com.ubots.filmes.dto.FilmeEditDTO;
import com.ubots.filmes.dto.FilmeResponseDTO;
import com.ubots.filmes.exceptions.ApiException;
import com.ubots.filmes.model.Filme;
import com.ubots.filmes.service.FilmeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid FilmeCreateDTO filmeCreateDTO) {
        Filme createdFilme = new Filme();
        BeanUtils.copyProperties(filmeCreateDTO, createdFilme);
        createdFilme.setReleaseYear(LocalDate.parse(filmeCreateDTO.getReleaseYear(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.filmeService.create(createdFilme);
        FilmeResponseDTO response = new FilmeResponseDTO(createdFilme);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(value = "id") UUID id, @RequestBody FilmeEditDTO filmeEditDTO) throws ApiException {
        Filme filme = this.filmeService.update(id, filmeEditDTO);
        FilmeResponseDTO  response = new FilmeResponseDTO(filme);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Filme> filmePage = this.filmeService.findAll(pageable);

        return new ResponseEntity<>(filmePage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) throws ApiException {
        this.filmeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
