package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idDetalle;

    private Integer reparaciones;
    private Integer recargos;
    private Integer descuentos;
    private Integer iva;

    // Relacion one-to-one: Detalle - Reparacion
    private Long idRegistro;

    public DetalleEntity(Integer reparaciones, Integer recargos, Integer descuentos, Integer iva, Long idRegistro) {
        this.reparaciones = reparaciones;
        this.recargos = recargos;
        this.descuentos = descuentos;
        this.iva = iva;
        this.idRegistro = idRegistro;
    }
}