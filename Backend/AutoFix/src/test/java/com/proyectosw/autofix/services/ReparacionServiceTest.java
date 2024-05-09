package com.proyectosw.autofix.services;

import com.proyectosw.autofix.dtos.ReparacionPorTipoAuto;
import com.proyectosw.autofix.dtos.ReparacionPorTipoMotor;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.repositories.ReparacionRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class ReparacionServiceTest {
    @InjectMocks
    ReparacionService reparacionService;

    @Mock
    ReparacionRespository reparacionRespository;

    @Mock
    RegistroService registroService;

    @BeforeEach
    public void setUp() {
        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setNumeroReparacion(1);
        reparacionA.setTipoReparacion("Reparaciones del Sistema de Frenos");
        reparacionA.setPrecio(120000);
        reparacionA.setIdRegistro(1L);

        ReparacionEntity reparacionId = new ReparacionEntity();
        reparacionId.setNumeroReparacion(reparacionA.getNumeroReparacion());
        reparacionId.setTipoReparacion(reparacionA.getTipoReparacion());
        reparacionId.setPrecio(reparacionA.getPrecio());
        reparacionId.setIdRegistro(reparacionA.getIdRegistro());
        reparacionId.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setNumeroReparacion(1);
        reparacionB.setTipoReparacion("Reparaciones del Sistema de Frenos");
        reparacionB.setPrecio(120000);
        reparacionB.setIdRegistro(2L);
        reparacionB.setIdReparacion(2L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(reparacionId.getIdRegistro());
        idRegistros.add(reparacionB.getIdRegistro());

        List<String> tiposReparaciones = new ArrayList<>();
        tiposReparaciones.add("Reparaciones del Sistema de Frenos");

        Mockito.when(reparacionRespository.save(reparacionA)).thenReturn(reparacionId);
        Mockito.when(reparacionRespository.findDistinctTipoReparacion()).thenReturn(tiposReparaciones);
        Mockito.when(reparacionRespository.findIdRegistroByTipoReparacion("Reparaciones del Sistema de Frenos")).thenReturn(idRegistros);
        Mockito.when(reparacionRespository.findFirstByIdRegistro(idRegistros.get(0))).thenReturn(reparacionId);
        Mockito.when(registroService.obtenerNumeroTiposAutos(idRegistros)).thenReturn(1);
        Mockito.when(reparacionRespository.sumPrecioByTipoReparacion("Reparaciones del Sistema de Frenos")).thenReturn(240000);
        Mockito.when(registroService.obtenerNumeroPorTiposMotores(idRegistros, "Gasolina")).thenReturn(2);
        Mockito.when(registroService.obtenerNumeroPorTiposMotores(idRegistros, "Diesel")).thenReturn(0);
        Mockito.when(registroService.obtenerNumeroPorTiposMotores(idRegistros, "Hibrido")).thenReturn(0);
        Mockito.when(registroService.obtenerNumeroPorTiposMotores(idRegistros, "Electrico")).thenReturn(0);
        Mockito.when(registroService.obtenerNumeroPorTiposAutos(idRegistros, "Sedan")).thenReturn(1);
        Mockito.when(registroService.obtenerNumeroPorTiposAutos(idRegistros, "Hatchback")).thenReturn(0);
        Mockito.when(registroService.obtenerNumeroPorTiposAutos(idRegistros, "SUV")).thenReturn(0);
        Mockito.when(registroService.obtenerNumeroPorTiposAutos(idRegistros, "Pickup")).thenReturn(0);
        Mockito.when(registroService.obtenerNumeroPorTiposAutos(idRegistros, "Furgoneta")).thenReturn(0);
    }

    @Test
    public void testCrearReparacion() {
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setNumeroReparacion(1);
        reparacion.setTipoReparacion("Reparaciones del Sistema de Frenos");
        reparacion.setPrecio(120000);
        reparacion.setIdRegistro(1L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacion);

        List<ReparacionEntity> reparacionesTest = reparacionService.crearReparacion(reparaciones, 1L);

        assertEquals(reparaciones.get(0).getIdRegistro(), reparacionesTest.get(0).getIdRegistro());
        assertEquals(1L, reparacionesTest.get(0).getIdReparacion());
        System.out.println("Reparaciones creadas = " + reparacionesTest);
    }

    @Test
    public void testReporteReparacionPorTipoAuto() {
        ReparacionPorTipoAuto reparacionPorTipoAuto = new ReparacionPorTipoAuto();
        reparacionPorTipoAuto.setCantidadSedan(1);
        reparacionPorTipoAuto.setCantidadHatchback(0);
        reparacionPorTipoAuto.setCantidadSUV(0);
        reparacionPorTipoAuto.setCantidadPickup(0);
        reparacionPorTipoAuto.setCantidadFurgoneta(0);
        reparacionPorTipoAuto.setTipoReparacion("Reparaciones del Sistema de Frenos");
        reparacionPorTipoAuto.setMontoTotal(240000);

        List<ReparacionPorTipoAuto> reparacionesPorTipoAuto = new ArrayList<>();
        reparacionesPorTipoAuto.add(reparacionPorTipoAuto);

        List<ReparacionPorTipoAuto> reparacionesPorTipoAutoTest = reparacionService.reporteReparacionPorTipoAuto();

        assertEquals(reparacionesPorTipoAuto, reparacionesPorTipoAutoTest);
        System.out.println("Reparaciones por tipo de auto = " + reparacionesPorTipoAutoTest);
    }

    @Test
    public void testReporteReparacionTipoMotor() {
        ReparacionPorTipoMotor reparacionPorTipoMotor = new ReparacionPorTipoMotor();
        reparacionPorTipoMotor.setTipoReparacion("Reparaciones del Sistema de Frenos");
        reparacionPorTipoMotor.setCantidadGasolina(2);
        reparacionPorTipoMotor.setCantidadDiesel(0);
        reparacionPorTipoMotor.setCantidadHibrido(0);
        reparacionPorTipoMotor.setCantidadElectrico(0);
        reparacionPorTipoMotor.setMontoTotal(240000);

        List<ReparacionPorTipoMotor> reparacionesPorTipoMotor = new ArrayList<>();
        reparacionesPorTipoMotor.add(reparacionPorTipoMotor);

        List<ReparacionPorTipoMotor> reparacionesPorTipoMotorTest = reparacionService.reporteReparacionPorTipoMotor();

        assertEquals(reparacionesPorTipoMotor, reparacionesPorTipoMotorTest);
        System.out.println("Reparaciones por tipo de motor = " + reparacionesPorTipoMotorTest);
    }
}
