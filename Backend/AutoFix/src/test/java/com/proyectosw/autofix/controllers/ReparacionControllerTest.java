package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.dtos.ReparacionPorTipoAuto;
import com.proyectosw.autofix.dtos.ReparacionPorTipoMotor;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.services.ReparacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ReparacionController.class)
@AutoConfigureMockMvc
public class ReparacionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReparacionService reparacionService;

    @Test
    void testCrearReparacion() throws Exception {
        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setNumeroReparacion(1);
        reparacionA.setTipoReparacion("Reparaciones del Sistema de Frenos");
        reparacionA.setPrecio(120000);
        reparacionA.setIdRegistro(1L);
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setNumeroReparacion(1);
        reparacionB.setTipoReparacion("Reparaciones del Sistema de Frenos");
        reparacionB.setPrecio(120000);
        reparacionB.setIdRegistro(1L);
        reparacionB.setIdReparacion(2L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);

        when(reparacionService.crearReparacion(any(List.class), any(Long.class))).thenReturn(reparaciones);

        mockMvc.perform(post("/reparacion/")
                        .param("idRegistro", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"numeroReparacion\": 1}, {\"numeroReparacion\": 2}]")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idReparacion").value(reparacionA.getIdReparacion()))
                .andExpect(jsonPath("$[0].numeroReparacion").value(reparacionA.getNumeroReparacion()))
                .andExpect(jsonPath("$[1].idReparacion").value(reparacionB.getIdReparacion()))
                .andExpect(jsonPath("$[1].numeroReparacion").value(reparacionB.getNumeroReparacion()));
    }

    @Test
    void testReporteReparacionPorTipoAuto() throws Exception {
        ReparacionPorTipoAuto reparacionPorTipoAutoA = new ReparacionPorTipoAuto();
        ReparacionPorTipoAuto reparacionPorTipoAutoB = new ReparacionPorTipoAuto();
        ReparacionPorTipoAuto reparacionPorTipoAutoC = new ReparacionPorTipoAuto();

        List<ReparacionPorTipoAuto> reparacionesPorTipoAuto = new ArrayList<>();
        reparacionesPorTipoAuto.add(reparacionPorTipoAutoA);
        reparacionesPorTipoAuto.add(reparacionPorTipoAutoB);
        reparacionesPorTipoAuto.add(reparacionPorTipoAutoC);

        when(reparacionService.reporteReparacionPorTipoAuto()).thenReturn(reparacionesPorTipoAuto);

        mockMvc.perform(get("/reparacion/reparaciones-por-tipo-auto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testReporteReparacionPorTipoMotor() throws Exception {
        ReparacionPorTipoMotor reparacionPorTipoMotorA = new ReparacionPorTipoMotor();
        ReparacionPorTipoMotor reparacionPorTipoMotorB = new ReparacionPorTipoMotor();
        ReparacionPorTipoMotor reparacionPorTipoMotorC = new ReparacionPorTipoMotor();

        List<ReparacionPorTipoMotor> reparacionesPorTipoMotor = new ArrayList<>();
        reparacionesPorTipoMotor.add(reparacionPorTipoMotorA);
        reparacionesPorTipoMotor.add(reparacionPorTipoMotorB);
        reparacionesPorTipoMotor.add(reparacionPorTipoMotorC);

        when(reparacionService.reporteReparacionPorTipoMotor()).thenReturn(reparacionesPorTipoMotor);

        mockMvc.perform(get("/reparacion/reparaciones-por-tipo-motor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}