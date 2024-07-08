package com.api.foroHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        Long idAutor,
        Long idCurso,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status
) {
    public DatosDetalleTopico(Topico topico){
        this(topico.getId(), topico.getAutor().getId(), topico.getCurso().getId(),topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus());
    }
}
