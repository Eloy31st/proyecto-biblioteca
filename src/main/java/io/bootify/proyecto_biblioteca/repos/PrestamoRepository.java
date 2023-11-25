package io.bootify.proyecto_biblioteca.repos;

import io.bootify.proyecto_biblioteca.domain.Bibliotecario;
import io.bootify.proyecto_biblioteca.domain.Lector;
import io.bootify.proyecto_biblioteca.domain.Libro;
import io.bootify.proyecto_biblioteca.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    Prestamo findFirstByLibro(Libro libro);

    Prestamo findFirstByLector(Lector lector);

    Prestamo findFirstByBibliotecario(Bibliotecario bibliotecario);

}
