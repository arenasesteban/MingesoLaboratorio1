package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
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
    private Integer descuentos;
    private Integer recargos;
    private Integer iva;

    // Relacion one-to-one: Detalle - Reparacion
    private Integer idReparacion;
}