package com.ubots.filmes.controller;

import com.ubots.filmes.dto.AvaliacaoResponseDTO;
import com.ubots.filmes.dto.FilmeCreateDTO;
import com.ubots.filmes.dto.FilmeEditDTO;
import com.ubots.filmes.dto.FilmeResponseDTO;
import com.ubots.filmes.erros.FilmeError;
import com.ubots.filmes.exceptions.ApiException;
import com.ubots.filmes.model.Avaliacao;
import com.ubots.filmes.model.Filme;
import com.ubots.filmes.service.AvaliacaoService;
import com.ubots.filmes.service.FilmeService;
import com.ubots.filmes.utils.NotaEnum;
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

    @Autowired
    private AvaliacaoService avaliacaoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid FilmeCreateDTO filmeCreateDTO) throws ApiException {
        Filme createdFilme = new Filme();
        BeanUtils.copyProperties(filmeCreateDTO, createdFilme);

        try {
            createdFilme.setReleaseYear(LocalDate.parse(filmeCreateDTO.getReleaseYear(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (Exception e) {
            throw FilmeError.errorInvalidDateFormat();
        }

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
        Page<FilmeResponseDTO> response = filmePage.map(FilmeResponseDTO::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) throws ApiException {
        this.filmeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}/evaluation", method = RequestMethod.POST)
    public ResponseEntity<?> addEvaluation(@PathVariable(value = "id") UUID id, @RequestParam(value = "Nota do filme") NotaEnum notaEnum, @RequestParam String comment) throws ApiException {
        Filme filme = this.filmeService.getById(id);

        Avaliacao createdAvaliacao = new Avaliacao();
        createdAvaliacao.setGrade(notaEnum.getValor());
        createdAvaliacao.setComment(comment);
        createdAvaliacao.setFilme(filme);
        this.avaliacaoService.addEvaluation(createdAvaliacao);

        AvaliacaoResponseDTO response = new AvaliacaoResponseDTO(createdAvaliacao);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/not-evaluation", method = RequestMethod.GET)
    public ResponseEntity<?> findAllNotEvaluation(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<FilmeResponseDTO> response = this.filmeService.findAllNotEvaluation(pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable(value = "id") UUID id) throws ApiException {
        Filme filme = this.filmeService.getById(id);
        FilmeResponseDTO response = new FilmeResponseDTO(filme);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
