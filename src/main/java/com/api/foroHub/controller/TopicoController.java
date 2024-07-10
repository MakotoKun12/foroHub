package com.api.foroHub.controller;

import com.api.foroHub.domain.topico.DatosActualizacionTopico;
import com.api.foroHub.domain.topico.DatosDetalleTopico;
import com.api.foroHub.domain.topico.DatosRegistroTopico;
import com.api.foroHub.domain.topico.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra un topico en la base de datos",
            tags = { "topico", "post" })
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos){
        var response = service.registrar(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "obtiene el listado de topicos")
    public ResponseEntity<Page<DatosDetalleTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        var response = service.consultar(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "obtiene un topico en especifico",
            description = "requiere su id",
            tags = { "topico", "get" })
    public ResponseEntity<DatosDetalleTopico> obtenerPorId(@PathVariable Long id){
        var topico = service.obtenerPorId(id);
        return  ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(
            summary = "actualiza un topico en la base de datos",
            description = "requiere su id",
            tags = { "topico", "put" })
    public ResponseEntity<DatosDetalleTopico> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos){
        var response = service.actualizar(id,datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "elimina un topico en la base de datos",
            description = "requiere su id",
            tags = { "topico", "delete" })
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        service.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
