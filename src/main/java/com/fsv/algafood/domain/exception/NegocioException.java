package com.fsv.algafood.domain.exception;

public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String messagem, Throwable cause) {
        super(messagem, cause);
    }
}
