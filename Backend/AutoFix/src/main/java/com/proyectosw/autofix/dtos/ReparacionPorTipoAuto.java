package com.proyectosw.autofix.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReparacionPorTipoAuto {
    private String tipoReparacion;
    private Integer numeroTiposAutos;
    private Integer montoTotal;
}
