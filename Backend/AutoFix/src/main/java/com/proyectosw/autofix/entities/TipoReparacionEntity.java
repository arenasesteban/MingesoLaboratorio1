package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_reparacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoReparacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idTipoReparacion;

    private String reparacion;
    private Integer precio;

    // Relacion many-to-one: TipoReparacion - Reparacion
    private Long idReparacion;
}
