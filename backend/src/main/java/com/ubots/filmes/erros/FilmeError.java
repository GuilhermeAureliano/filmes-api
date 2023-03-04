package com.ubots.filmes.erros;

import com.ubots.filmes.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class FilmeError {

    static final String FILM_NOT_EXIST = "Filme não encontrado!";
    static final String INVALID_DATE_FORMAT = "Data de lançamento inválida. Tente assim: dd/MM/yyyy";

    public static ApiException errorFilmNotExist() {
        return new ApiException(FilmeError.FILM_NOT_EXIST, HttpStatus.NOT_FOUND);
    }

    public static ApiException errorInvalidDateFormat() {
        return new ApiException(FilmeError.INVALID_DATE_FORMAT, HttpStatus.BAD_REQUEST);
    }

}
