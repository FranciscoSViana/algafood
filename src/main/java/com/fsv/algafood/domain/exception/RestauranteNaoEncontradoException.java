package com.fsv.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de restaurante com código %d", estadoId));
    }
}
