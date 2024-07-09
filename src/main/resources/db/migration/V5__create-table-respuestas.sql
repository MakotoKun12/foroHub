CREATE TABLE respuestas (
    id_respuesta INT PRIMARY KEY AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_autor INT,
    id_topico INT,
    activo tinyint(1) NOT NULL DEFAULT 1,
    FOREIGN KEY (id_autor) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_topico) REFERENCES topicos(id_topico)
);
