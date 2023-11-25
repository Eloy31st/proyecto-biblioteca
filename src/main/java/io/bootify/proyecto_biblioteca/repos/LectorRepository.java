package io.bootify.proyecto_biblioteca.repos;

import io.bootify.proyecto_biblioteca.domain.Lector;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LectorRepository extends JpaRepository<Lector, Long> {
}
