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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idReparacion;

    private String tipoReparacion;
    private String tipoMotor;
    private Double monto;

    // Relacion many-to-one: Reparacion - Registro
    private Long idRegistro;
}