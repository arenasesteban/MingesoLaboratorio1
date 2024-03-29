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
    private Long idRegistro;

    private String patente; // FK - vehiculo
    private Integer idReparacion; // FK - lista reparaciones
    private LocalDate fechaIngreso;
    private LocalDate horaIngreso;
    private Integer montoTotal;
    private LocalDate fechaSalida;
    private LocalDate horaSalida;
    private LocalDate fechaRetiro;
    private LocalDate horaRetiro;
}
