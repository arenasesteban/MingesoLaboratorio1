package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.dtos.TiempoReparacionPorMarca;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.services.VehiculoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehiculoController.class)
@AutoConfigureMockMvc
public class VehiculoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehiculoService vehiculoService;

    @Test
    void testRegistarVehiculo() throws Exception {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente("LJSY77");
        vehiculo.setMarca("Hyundai");
        vehiculo.setModelo("Accent");
        vehiculo.setTipoAuto("Sedan");
        vehiculo.setAnoFabricacion(2019);
        vehiculo.setTipoMotor("Gasolina");
        vehiculo.setNumeroAsientos(5);
        vehiculo.setKilometraje(42000);

        when(vehiculoService.registrarVehiculo(any(VehiculoEntity.class))).thenReturn(vehiculo);

        mockMvc.perform(post("/vehiculo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"patente\": \"LJSY77\", \"marca\": \"Hyundai\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patente").value("LJSY77"))
                .andExpect(jsonPath("$.marca").value("Hyundai"));
    }

    @Test
    void testObtenerVehiculos() throws Exception {
        List<VehiculoEntity> vehiculos = new ArrayList<>();
        vehiculos.add(new VehiculoEntity("LJSY77", "Hyundai", "Accent", "Sedan", 2019, "Gasolina", 5, 42000));
        vehiculos.add(new VehiculoEntity("BKLF54", "Chevrolet", "Corsa", "Sedan", 2010, "Gasolina", 5, 63000));

        when(vehiculoService.obtenerVehiculos()).thenReturn(vehiculos);

        mockMvc.perform(get("/vehiculo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].patente").value("LJSY77"))
                .andExpect(jsonPath("$[0].marca").value("Hyundai"))
                .andExpect(jsonPath("$[1].patente").value("BKLF54"))
                .andExpect(jsonPath("$[1].marca").value("Chevrolet"));
    }

    @Test
    void testActualizarVehiculo() throws Exception {
        String patente = "LJSY77";
        Integer nuevoKilometraje = 50000;

        VehiculoEntity vehiculoActualizado = new VehiculoEntity();
        vehiculoActualizado.setPatente(patente);
        vehiculoActualizado.setKilometraje(nuevoKilometraje);

        when(vehiculoService.actualizarVehiculo(patente, nuevoKilometraje)).thenReturn(vehiculoActualizado);

        mockMvc.perform(put("/vehiculo/")
                        .param("patente", patente)
                        .param("kilometraje", String.valueOf(nuevoKilometraje))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patente").value(patente))
                .andExpect(jsonPath("$.kilometraje").value(nuevoKilometraje));
    }

    @Test
    void testReporteTiempoReparacionPorMarca() throws Exception {
        List<TiempoReparacionPorMarca> tiempoReparacionesPorMarca = new ArrayList<>();
        tiempoReparacionesPorMarca.add(new TiempoReparacionPorMarca("Hyundai", 10L));
        tiempoReparacionesPorMarca.add(new TiempoReparacionPorMarca("Chevrolet", 8L));

        when(vehiculoService.reporteTiempoReparacionPorMarca()).thenReturn(tiempoReparacionesPorMarca);

        mockMvc.perform(get("/vehiculo/tiempo-reparacion-por-marca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))  // Verificar que hay dos elementos en la lista
                .andExpect(jsonPath("$[0].marca").value("Hyundai"))  // Verificar la primera marca
                .andExpect(jsonPath("$[0].tiempoPromedioReparacion").value(10L))  // Verificar el primer tiempo de reparación
                .andExpect(jsonPath("$[1].marca").value("Chevrolet"))  // Verificar la segunda marca
                .andExpect(jsonPath("$[1].tiempoPromedioReparacion").value(8L));  // Verificar el segundo tiempo de reparación
    }
}