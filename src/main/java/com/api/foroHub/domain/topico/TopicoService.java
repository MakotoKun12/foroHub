package com.api.foroHub.domain.topico;

import com.api.foroHub.domain.curso.CursoRepository;
import com.api.foroHub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosDetalleTopico registrar(DatosRegistroTopico datos){
        if(!usuarioRepository.findById(datos.idAutor()).isPresent()){
            throw new ValidationException("Este id para el autor no fue encontrado");
        }

        if(!cursoRepository.findById(datos.idCurso()).isPresent()){
            throw new ValidationException("Este id para el curso no fue encontrado");
        }

        var autor = usuarioRepository.findById(datos.idAutor()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();

        var topico = new Topico(autor,curso,datos.titulo(), datos.mensaje(), datos.fechaCreacion(), datos.status());
        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);
    }
}
