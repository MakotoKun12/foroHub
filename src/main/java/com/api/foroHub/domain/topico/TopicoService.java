package com.api.foroHub.domain.topico;

import com.api.foroHub.domain.curso.CursoRepository;
import com.api.foroHub.domain.usuario.UsuarioRepository;
import com.api.foroHub.infra.excepciones.DuplicadoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

        if (topicoRepository.findByTitulo(datos.titulo()).isPresent()){
            throw new DuplicadoException("El titulo ya esta en uso");
        }

        if (topicoRepository.findByMensaje(datos.mensaje()).isPresent()){
            throw new DuplicadoException("El mensaje ya esta en uso");
        }

        var autor = usuarioRepository.findById(datos.idAutor()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();

        var topico = new Topico(autor,curso,datos.titulo(), datos.mensaje(), datos.fechaCreacion(), datos.status());
        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);
    }

    public Page<DatosDetalleTopico> consultar(Pageable paginacion){
        return topicoRepository.findByActivoTrue(paginacion).map(DatosDetalleTopico::new);
    }

    public DatosDetalleTopico obtenerPorId(Long id) {
        var topico = topicoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Topico no encontrado"));
        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico actualizar(Long id, DatosActualizacionTopico datos) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topico no encontrado"));

        if (datos.titulo() != null && !datos.titulo().equals(topico.getTitulo())){
            topicoRepository.findByTitulo(datos.titulo()).ifPresent(t-> {
                throw new DuplicadoException("El titulo ya esta en uso");
            });
            topico.setTitulo(datos.titulo());
        }
        if (datos.mensaje() != null && !datos.mensaje().equals(topico.getMensaje())) {
            topicoRepository.findByMensaje(datos.mensaje()).ifPresent(t -> {
                throw new DuplicadoException("El mensaje ya está en uso");
            });
            topico.setMensaje(datos.mensaje());
        }
        if (datos.idAutor() != null) {
            var autor = usuarioRepository.findById(datos.idAutor())
                    .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
            topico.setAutor(autor);
        }
        if (datos.idCurso() != null) {
            var curso = cursoRepository.findById(datos.idCurso())
                    .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
            topico.setCurso(curso);
        }

        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }

    public void eliminarTopico(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topico no encontrado"));
        topico.desactivarTopico();
    }
}
