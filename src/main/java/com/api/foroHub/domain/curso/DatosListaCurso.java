package com.api.foroHub.domain.curso;

public record DatosListaCurso(
    Long id,
    String nombreCurso,
    String descripcion
) {
    public DatosListaCurso(Curso curso){
        this(curso.getId(),curso.getNombreCurso(),curso.getDescripcion());
    }
}
