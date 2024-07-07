package com.api.foroHub.domain.usuario;

public record DatosListaUsuario (
        Long id,
        String nombre,
        String email
){
    public DatosListaUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getNombre());
    }
}
