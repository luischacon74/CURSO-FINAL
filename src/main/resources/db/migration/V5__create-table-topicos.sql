CREATE TABLE topicos (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(500) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaDeCreacionTopico TIMESTAMP,
    fechaUltimaEdicionTopico TIMESTAMP,
    statusTopico VARCHAR(50) NOT NULL,
    id_autor BIGINT NOT NULL,
    id_curso BIGINT NOT NULL,
    CONSTRAINT fk_autorTopico FOREIGN KEY (id_autor) REFERENCES usuarios(id),
    CONSTRAINT fk_curso FOREIGN KEY (id_curso) REFERENCES cursos(id)
);
