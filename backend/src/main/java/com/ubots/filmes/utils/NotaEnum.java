package com.ubots.filmes.utils;

public enum NotaEnum {
    ZERO(0),
    UM(1),
    DOIS(2),
    TRES(3),
    QUATRO(4),
    CINCO(5);

    private final int valor;

    NotaEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
