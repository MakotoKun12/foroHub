package com.api.foroHub.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @Future
        LocalDateTime fechaCreacion,
        Status status,
        @NotNull
        Long idAutor,
        @NotNull
        Long idCurso
) {}
