package com.api.foroHub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank
        String nombreCurso,

        @NotBlank
        String descripcion
) {}
