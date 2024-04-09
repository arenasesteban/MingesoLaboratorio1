package com.proyectosw.autofix.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionPorTipoMotor {
    private String tipoReparacion;
    private Integer cantidadGasolina;
    private Integer cantidadDiesel;
    private Integer cantidadHibrido;
    private Integer cantidadElectrico;
    private Integer montoTotal;
}
