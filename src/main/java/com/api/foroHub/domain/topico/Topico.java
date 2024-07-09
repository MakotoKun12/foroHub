package com.api.foroHub.domain.topico;

import com.api.foroHub.domain.curso.Curso;
import com.api.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topico")
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    private Boolean activo;


    public Topico(Usuario autor, Curso curso, String titulo, String mensaje, LocalDateTime fechaCreacion, Status status) {
        this.autor = autor;
        this.curso = curso;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.activo = true;
    }

    public void desactivarTopico() {
        this.activo = false;
    }
}
