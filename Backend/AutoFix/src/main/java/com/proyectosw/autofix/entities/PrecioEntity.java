package com.proyectosw.autofix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "precio_reparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_precio;

    private int gasolina;
    private int diesel;
    private int hibrido;
    private int electrico;
}
