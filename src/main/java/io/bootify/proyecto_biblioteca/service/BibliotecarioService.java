package io.bootify.proyecto_biblioteca.service;

import io.bootify.proyecto_biblioteca.domain.Bibliotecario;
import io.bootify.proyecto_biblioteca.domain.Lector;
import io.bootify.proyecto_biblioteca.domain.Prestamo;
import io.bootify.proyecto_biblioteca.model.BibliotecarioDTO;
import io.bootify.proyecto_biblioteca.repos.BibliotecarioRepository;
import io.bootify.proyecto_biblioteca.repos.LectorRepository;
import io.bootify.proyecto_biblioteca.repos.PrestamoRepository;
import io.bootify.proyecto_biblioteca.util.NotFoundException;
import io.bootify.proyecto_biblioteca.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BibliotecarioService {

    private final BibliotecarioRepository bibliotecarioRepository;
    private final LectorRepository lectorRepository;
    private final PrestamoRepository prestamoRepository;

    public BibliotecarioService(final BibliotecarioRepository bibliotecarioRepository,
            final LectorRepository lectorRepository, final PrestamoRepository prestamoRepository) {
        this.bibliotecarioRepository = bibliotecarioRepository;
        this.lectorRepository = lectorRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<BibliotecarioDTO> findAll() {
        final List<Bibliotecario> bibliotecarios = bibliotecarioRepository.findAll(Sort.by("id"));
        return bibliotecarios.stream()
                .map(bibliotecario -> mapToDTO(bibliotecario, new BibliotecarioDTO()))
                .toList();
    }

    public BibliotecarioDTO get(final Long id) {
        return bibliotecarioRepository.findById(id)
                .map(bibliotecario -> mapToDTO(bibliotecario, new BibliotecarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BibliotecarioDTO bibliotecarioDTO) {
        final Bibliotecario bibliotecario = new Bibliotecario();
        mapToEntity(bibliotecarioDTO, bibliotecario);
        return bibliotecarioRepository.save(bibliotecario).getId();
    }

    public void update(final Long id, final BibliotecarioDTO bibliotecarioDTO) {
        final Bibliotecario bibliotecario = bibliotecarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bibliotecarioDTO, bibliotecario);
        bibliotecarioRepository.save(bibliotecario);
    }

    public void delete(final Long id) {
        bibliotecarioRepository.deleteById(id);
    }

    private BibliotecarioDTO mapToDTO(final Bibliotecario bibliotecario,
            final BibliotecarioDTO bibliotecarioDTO) {
        bibliotecarioDTO.setId(bibliotecario.getId());
        bibliotecarioDTO.setNombre(bibliotecario.getNombre());
        bibliotecarioDTO.setApellido(bibliotecario.getApellido());
        bibliotecarioDTO.setZona(bibliotecario.getZona());
        bibliotecarioDTO.setDni(bibliotecario.getDni());
        bibliotecarioDTO.setEsLector(bibliotecario.getEsLector() == null ? null : bibliotecario.getEsLector().getId());
        return bibliotecarioDTO;
    }

    private Bibliotecario mapToEntity(final BibliotecarioDTO bibliotecarioDTO,
            final Bibliotecario bibliotecario) {
        bibliotecario.setNombre(bibliotecarioDTO.getNombre());
        bibliotecario.setApellido(bibliotecarioDTO.getApellido());
        bibliotecario.setZona(bibliotecarioDTO.getZona());
        bibliotecario.setDni(bibliotecarioDTO.getDni());
        final Lector esLector = bibliotecarioDTO.getEsLector() == null ? null : lectorRepository.findById(bibliotecarioDTO.getEsLector())
                .orElseThrow(() -> new NotFoundException("esLector not found"));
        bibliotecario.setEsLector(esLector);
        return bibliotecario;
    }

    public boolean zonaExists(final String zona) {
        return bibliotecarioRepository.existsByZonaIgnoreCase(zona);
    }

    public boolean esLectorExists(final Long id) {
        return bibliotecarioRepository.existsByEsLectorId(id);
    }

    public String getReferencedWarning(final Long id) {
        final Bibliotecario bibliotecario = bibliotecarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Prestamo bibliotecarioPrestamo = prestamoRepository.findFirstByBibliotecario(bibliotecario);
        if (bibliotecarioPrestamo != null) {
            return WebUtils.getMessage("bibliotecario.prestamo.bibliotecario.referenced", bibliotecarioPrestamo.getId());
        }
        return null;
    }

}
