package com.ubots.filmes.erros;

import com.ubots.filmes.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class FilmeError {

    static final String FILM_NOT_EXIST = "Filme não encontrado!";

    public static ApiException errorFilmNotExist() {
        return new ApiException(FilmeError.FILM_NOT_EXIST, HttpStatus.NOT_FOUND);
    }
}
