package io.bootify.proyecto_biblioteca.domain;

import io.bootify.proyecto_biblioteca.model.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "Libros")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Libro {

    @Id
    @Column(name ="id_Libro",nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name ="Titulo",nullable = false)
    private String titulo;

    @Column(name ="Autor",nullable = false)
    private String autor;

    @Column(name ="Estado",nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "libro")
    private Set<Prestamo> libroPrestamos;

    @CreatedDate
    @Column(name ="dateCreated",nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(name ="lastUpdated",nullable = false)
    private OffsetDateTime lastUpdated;

}
