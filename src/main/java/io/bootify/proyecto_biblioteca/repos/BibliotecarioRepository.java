package io.bootify.proyecto_biblioteca.repos;

import io.bootify.proyecto_biblioteca.domain.Bibliotecario;
import io.bootify.proyecto_biblioteca.domain.Lector;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Long> {

    Bibliotecario findFirstByEsLector(Lector lector);

    boolean existsByZonaIgnoreCase(String zona);

    boolean existsByEsLectorId(Long id);

}
