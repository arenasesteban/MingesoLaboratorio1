package com.proyectosw.autofix.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionPorTipoAuto {
    private String tipoReparacion;
    private Integer cantidadSedan;
    private Integer cantidadHatchback;
    private Integer cantidadSUV;
    private Integer cantidadPickup;
    private Integer cantidadFurgoneta;
    private Integer montoTotal;
}
