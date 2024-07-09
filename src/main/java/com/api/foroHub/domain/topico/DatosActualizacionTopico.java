package com.api.foroHub.domain.topico;

public record DatosActualizacionTopico(
        String titulo,
        String mensaje,
        Long idAutor,
        Long idCurso
) {}
