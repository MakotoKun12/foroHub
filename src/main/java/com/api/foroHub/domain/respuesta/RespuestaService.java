package com.api.foroHub.domain.respuesta;

import com.api.foroHub.domain.topico.TopicoRepository;
import com.api.foroHub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public DatosDetalleRespuesta registrar(DatosRegistroRespuesta datos){
        if(!usuarioRepository.findById(datos.idAutor()).isPresent()){
            throw new ValidationException("Este id para el autor no fue encontrado");
        }

        if(!topicoRepository.findById(datos.idTopico()).isPresent()){
            throw new ValidationException("Este id para el topico no fue encontrado");
        }

        var autor = usuarioRepository.findById(datos.idAutor()).get();
        var topico = topicoRepository.findById(datos.idTopico()).get();

        var respuesta = new Respuesta(autor,topico, datos.mensaje());

        respuestaRepository.save(respuesta);

        return new DatosDetalleRespuesta(respuesta);
    }

    public List<DatosDetalleRespuesta> obtenerRespuestasPorUsuario(Long id){
        List<Respuesta> respuestas = respuestaRepository.findByAutorId(id);
        return respuestas.stream().map(DatosDetalleRespuesta::new)
                .collect(Collectors.toList());
    }

}
