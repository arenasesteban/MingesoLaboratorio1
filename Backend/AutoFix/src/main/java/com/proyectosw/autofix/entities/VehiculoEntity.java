package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoEntity {
    @Id
    @Column(unique = true, nullable = false)
    private String patente;

    private String marca;
    private String modelo;
    private String tipoAuto;
    private LocalDate anoFabricacion;
    private String tipoMotor;
    private Integer numeroAsientos;
}
