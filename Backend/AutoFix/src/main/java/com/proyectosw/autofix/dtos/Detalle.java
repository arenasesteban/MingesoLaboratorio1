package com.proyectosw.autofix.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detalle {
    private Integer reparaciones;
    private Integer recargos;
    private Integer descuentos;
    private Integer iva;
    private Integer montoTotal;
}