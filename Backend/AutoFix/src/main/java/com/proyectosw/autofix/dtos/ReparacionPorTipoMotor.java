package com.proyectosw.autofix.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReparacionPorTipoMotor {
    private String tipoReparacion;
    private Integer cantidadGasolina;
    private Integer cantidadDiesel;
    private Integer cantidadHibrido;
    private Integer cantidadElectrico;
    private Integer montoTotal;
}
