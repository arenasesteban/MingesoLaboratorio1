package com.proyectosw.autofix.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.proyectosw.autofix.dtos.Detalle;
import com.proyectosw.autofix.entities.RegistroEntity;
import com.proyectosw.autofix.services.RegistroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(RegistroController.class)
@AutoConfigureMockMvc
public class RegistroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistroService registroService;

    @Test
    void testCrearRegistro() throws Exception {
        RegistroEntity registro = new RegistroEntity();
        registro.setFechaIngreso(LocalDate.parse("2023-05-10"));
        registro.setHoraIngreso(LocalTime.parse("15:00"));
        registro.setFechaSalida(LocalDate.parse("2023-05-11"));
        registro.setHoraSalida(LocalTime.parse("12:30"));
        registro.setFechaRetiro(LocalDate.parse("2023-05-12"));
        registro.setHoraRetiro(LocalTime.parse("16:15"));
        registro.setPatente("LJSY77");
        registro.setIdRegistro(1L);

        when(registroService.crearRegistro(any(RegistroEntity.class))).thenReturn(registro);

        mockMvc.perform(post("/registro/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"patente\": \"LJSY77\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRegistro").value(1L))
                .andExpect(jsonPath("$.patente").value("LJSY77"));
    }

    @Test
    void testObtenerRegistros() throws Exception {
        RegistroEntity registroA = new RegistroEntity();
        registroA.setFechaIngreso(LocalDate.parse("2023-05-10"));
        registroA.setHoraIngreso(LocalTime.parse("15:00"));
        registroA.setFechaSalida(LocalDate.parse("2023-05-11"));
        registroA.setHoraSalida(LocalTime.parse("12:30"));
        registroA.setFechaRetiro(LocalDate.parse("2023-05-12"));
        registroA.setHoraRetiro(LocalTime.parse("16:15"));
        registroA.setPatente("LJSY77");
        registroA.setIdRegistro(1L);

        RegistroEntity registroB = new RegistroEntity();
        registroB.setFechaIngreso(LocalDate.parse("2023-07-06"));
        registroB.setHoraIngreso(LocalTime.parse("11:45"));
        registroB.setFechaSalida(LocalDate.parse("2023-07-07"));
        registroB.setHoraSalida(LocalTime.parse("13:00"));
        registroB.setFechaRetiro(LocalDate.parse("2023-07-08"));
        registroB.setHoraRetiro(LocalTime.parse("18:00"));
        registroB.setPatente("LJSY77");
        registroB.setIdRegistro(2L);

        List<RegistroEntity> registros = new ArrayList<>();
        registros.add(registroA);
        registros.add(registroB);

        when(registroService.obtenerRegistros()).thenReturn(registros);

        mockMvc.perform(get("/registro/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idRegistro").value(1L))
                .andExpect(jsonPath("$[0].patente").value("LJSY77"))
                .andExpect(jsonPath("$[1].idRegistro").value(2L))
                .andExpect(jsonPath("$[1].patente").value("LJSY77"));
    }

    @Test
    void testCalcularTotal() throws Exception {
        Long idRegistro = 1L;
        int descuentoPorBono = 10;

        Detalle detalle = new Detalle(120000, 0, 0, 22800, 142800);

        when(registroService.calcularTotal(idRegistro, descuentoPorBono)).thenReturn(detalle);

        mockMvc.perform(get("/registro/calcular-total")
                        .param("idRegistro", String.valueOf(idRegistro))
                        .param("descuentoPorBono", String.valueOf(descuentoPorBono))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reparaciones").value(detalle.getReparaciones()))
                .andExpect(jsonPath("$.montoTotal").value(detalle.getMontoTotal()));
    }
}