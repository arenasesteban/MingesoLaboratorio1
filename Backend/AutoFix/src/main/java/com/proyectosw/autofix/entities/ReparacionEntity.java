package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private LocalDate fechaIngreso;
    private LocalDate horaIngreso;
    private Integer montoTotal;
    private LocalDate fechaSalida;
    private LocalDate horaSalida;
    private LocalDate fechaRetiro;
    private LocalDate horaRetiro;

    // Relacion many-to-one: Reparacion - Vehiculo
    private String patente;

}
