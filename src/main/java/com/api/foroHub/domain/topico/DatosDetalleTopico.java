package com.api.foroHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        Long idAutor,
        String nombreAutor,
        Long idCurso,
        String nombreCurso,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status
) {
    public DatosDetalleTopico(Topico topico){
        this(topico.getId(),
                topico.getAutor().getId(), topico.getAutor().getNombre(),
                topico.getCurso().getId(), topico.getCurso().getNombreCurso(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus()
        );
    }
}
