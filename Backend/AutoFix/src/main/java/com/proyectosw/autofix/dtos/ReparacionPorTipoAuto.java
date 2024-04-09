package com.proyectosw.autofix.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionPorTipoAuto {
    private String tipoReparacion;
    private Integer numeroTiposAutos;
    private Integer montoTotal;
}
