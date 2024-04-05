package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bono")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idBono;

    private String marca;
    private Integer cantidadBonos;
    private Integer montoPorBonos;
}
