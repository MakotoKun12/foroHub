package com.api.foroHub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long idRespuesta,
        String nombreAutor,
        String nombreTopico,
        String respuesta,
        LocalDateTime fechaCreacion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),respuesta.getAutor().getNombre(),
                respuesta.getTopico().getTitulo(),respuesta.getMensaje(),
                respuesta.getFechaCreacion());
    }
}
