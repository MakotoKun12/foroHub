package com.api.foroHub.controller;

import com.api.foroHub.domain.topico.DatosActualizacionTopico;
import com.api.foroHub.domain.topico.DatosDetalleTopico;
import com.api.foroHub.domain.topico.DatosRegistroTopico;
import com.api.foroHub.domain.topico.TopicoService;
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
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos){
        var response = service.registrar(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        var response = service.consultar(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerPorId(@PathVariable Long id){
        var topico = service.obtenerPorId(id);
        return  ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos){
        var response = service.actualizar(id,datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        service.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
