package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalTime horaIngreso;
    private Integer montoTotal;
    private LocalDate fechaSalida;
    private LocalTime  horaSalida;
    private LocalDate fechaRetiro;
    private LocalTime  horaRetiro;

    // Relacion many-to-one: Reparacion - Vehiculo
    private String patente;
}
