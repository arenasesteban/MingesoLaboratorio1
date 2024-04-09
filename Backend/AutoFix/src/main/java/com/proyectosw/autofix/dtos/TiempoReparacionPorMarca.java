package com.proyectosw.autofix.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiempoReparacionPorMarca {
    private String marca;
    private long tiempoPromedioReparacion;
}
