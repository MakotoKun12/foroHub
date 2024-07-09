package com.api.foroHub.infra.excepciones;

public class ValidacionIntegridad extends RuntimeException{
    public ValidacionIntegridad(String mensaje){
        super(mensaje);
    }
}
