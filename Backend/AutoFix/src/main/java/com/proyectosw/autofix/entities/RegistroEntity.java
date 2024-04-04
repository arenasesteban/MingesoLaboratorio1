package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "registro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idRegistro;

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
