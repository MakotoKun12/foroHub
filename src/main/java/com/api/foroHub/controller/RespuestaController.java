package com.api.foroHub.controller;

import com.api.foroHub.domain.respuesta.DatosDetalleRespuesta;
import com.api.foroHub.domain.respuesta.DatosRegistroRespuesta;
import com.api.foroHub.domain.respuesta.RespuestaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra una respuesta en un topico determinado",
            tags = { "respuesta", "post" })
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroRespuesta datos){
        var response = service.registrar(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "obtiene el listado de respuestas de un usuario determinado",
            description = "requiere su id",
            tags = { "respuesta", "get" })
    public ResponseEntity respuestasPorUsuario(@PathVariable Long id){
        List<DatosDetalleRespuesta> respuestas = service.obtenerRespuestasPorUsuario(id);

        if (respuestas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen respuestas para este usuario.");
        } else {
            return ResponseEntity.ok(respuestas);
        }
    }

}
