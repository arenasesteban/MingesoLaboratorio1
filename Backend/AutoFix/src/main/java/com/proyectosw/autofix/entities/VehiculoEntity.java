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
    private String numero_patente;

    private String marca;
    private String modelo;
    private String tipo_auto;
    private LocalDate ano_fabricacion;
    private String tipo_motor;
    private Integer numero_asientos;
}
