package com.api.foroHub.domain.respuesta;

import com.api.foroHub.domain.topico.Topico;
import com.api.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private Long id;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "id_topico")
    private Topico topico;

    @Column(name = "activo")
    private Boolean activo;

    public Respuesta(Usuario autor, Topico topico, String mensaje) {
        this.autor = autor;
        this.topico = topico;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }
}
