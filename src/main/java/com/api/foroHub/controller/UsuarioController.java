package com.api.foroHub.controller;

import com.api.foroHub.domain.usuario.*;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                                  UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getEmail(), usuario.getPassword());
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaUsuario>> listadoUsuarios(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListaUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> retornaDatosUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword());
        return ResponseEntity.ok(datosUsuario);
    }

}
