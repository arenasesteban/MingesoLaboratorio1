package com.proyectosw.autofix.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatenteMotor {
    private String patente;
    private String tipoMotor;

    public void PatenteMotorDTO(String patente, String tipoMotor) {
        this.patente = patente;
        this.tipoMotor = tipoMotor;
    }
}