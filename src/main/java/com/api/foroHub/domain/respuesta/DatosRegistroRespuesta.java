package com.api.foroHub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotNull
        Long idAutor,
        @NotNull
        Long idTopico,
        @NotBlank
        String mensaje
) {}
