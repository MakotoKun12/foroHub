package com.api.foroHub.controller;

import com.api.foroHub.domain.curso.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                                              UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombreCurso(), curso.getDescripcion());
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaCurso>> listadoCursos(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion).map(DatosListaCurso::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> retornaDatosUsuario(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        var datosCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombreCurso(), curso.getDescripcion());
        return ResponseEntity.ok(datosCurso);
    }


}
