package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reparacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionEntity {
    @Id
    @Column(unique = true, nullable = false)
    private Long idReparacion;

    private String tipoReparacion;
    private Integer precio;

    // Relacion many-to-one: Reparacion - Registro
    private Integer idRegistro;
}
