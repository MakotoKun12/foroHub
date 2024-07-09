package com.api.foroHub.infra.excepciones;

public class DuplicadoException extends RuntimeException{
    public DuplicadoException(String mensaje){super(mensaje);}
}
