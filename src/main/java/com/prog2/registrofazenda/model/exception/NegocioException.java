package com.prog2.registrofazenda.model.exception;

public class NegocioException extends RuntimeException {
    private static final long serialVersion = 1L;

    public NegocioException(String message) {
        super(message);
    }
}
