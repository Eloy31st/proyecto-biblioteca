package io.bootify.proyecto_biblioteca.repos;

import io.bootify.proyecto_biblioteca.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibroRepository extends JpaRepository<Libro, Long> {
}
