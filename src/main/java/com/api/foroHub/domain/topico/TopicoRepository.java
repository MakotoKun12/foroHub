package com.api.foroHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Page<Topico> findByActivoTrue(Pageable pageable);
    Optional<Topico> findByIdAndActivoTrue(Long id);
    Optional<Topico> findByTitulo(String titulo);
    Optional<Topico> findByMensaje(String mensaje);
}
