package io.bootify.proyecto_biblioteca.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "Bibliotecarios")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Bibliotecario {

    @Id
    @Column(name = "id_bibliotecario",nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "apellido",nullable = false)
    private String apellido;

    @Column(name = "zona",nullable = false, unique = true)
    private String zona;

    @Column(name = "dni",nullable = false)
    private String dni;

    @OneToMany(mappedBy = "bibliotecario")
    private Set<Prestamo> prestamos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "es_lector_id", unique = true)
    private Lector esLector;

    @CreatedDate
    @Column(name = "dateCreated",nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(name = "lastUpdated",nullable = false)
    private OffsetDateTime lastUpdated;

}
