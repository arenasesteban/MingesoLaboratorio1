package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "patente")
    private VehiculoEntity vehiculo;

    @OneToMany(mappedBy = "registro")
    private List<ReparacionEntity> reparaciones;
}
