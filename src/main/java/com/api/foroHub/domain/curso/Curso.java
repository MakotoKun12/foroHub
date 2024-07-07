package com.api.foroHub.domain.curso;

import com.api.foroHub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;
    @Column(name = "nombre_curso")
    private String nombreCurso;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos;
    @Column(name = "activo")
    private Boolean activo;

    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombreCurso = datosRegistroCurso.nombreCurso();
        this.descripcion = datosRegistroCurso.descripcion();
        this.activo = true;
    }
}
