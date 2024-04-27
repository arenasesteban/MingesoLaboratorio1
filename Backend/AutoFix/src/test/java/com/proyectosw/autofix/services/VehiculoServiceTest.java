package com.proyectosw.autofix.services;

import com.proyectosw.autofix.dtos.TiempoReparacionPorMarca;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.repositories.VehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VehiculoServiceTest {
    @InjectMocks
    VehiculoService vehiculoService;

    @Mock
    VehiculoRepository vehiculoRepository;

    @Mock
    RegistroService registroService;

    @BeforeEach
    public void setUp() {
        VehiculoEntity vehiculoA = new VehiculoEntity();
        vehiculoA.setPatente("LJSY77");
        vehiculoA.setMarca("Hyundai");
        vehiculoA.setModelo("Accent");
        vehiculoA.setTipoAuto("Sedan");
        vehiculoA.setAnoFabricacion(2019);
        vehiculoA.setTipoMotor("Gasolina");
        vehiculoA.setNumeroAsientos(5);
        vehiculoA.setKilometraje(42000);

        VehiculoEntity vehiculoB = new VehiculoEntity();
        vehiculoB.setPatente("BKLF54");
        vehiculoB.setMarca("Chevrolet");
        vehiculoB.setModelo("Corsa");
        vehiculoB.setTipoAuto("Sedan");
        vehiculoB.setAnoFabricacion(2010);
        vehiculoB.setTipoMotor("Gasolina");
        vehiculoB.setNumeroAsientos(5);
        vehiculoB.setKilometraje(63000);

        List<VehiculoEntity> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculoA);
        vehiculos.add(vehiculoB);

        VehiculoEntity vehiculoC = new VehiculoEntity();
        vehiculoC.setPatente("HEIL22");
        vehiculoC.setMarca("Chevrolet");
        vehiculoC.setModelo("Silverado");
        vehiculoC.setTipoAuto("Pickup");
        vehiculoC.setAnoFabricacion(2020);
        vehiculoC.setTipoMotor("Gasolina");
        vehiculoC.setNumeroAsientos(3);
        vehiculoC.setKilometraje(20500);

        List<String> marcas = new ArrayList<>();
        marcas.add(vehiculoA.getMarca());
        marcas.add(vehiculoB.getMarca());

        List<String> patentesMarcasA = new ArrayList<>();
        patentesMarcasA.add(vehiculoA.getPatente());

        List<String> patentesMarcasB = new ArrayList<>();
        patentesMarcasB.add(vehiculoB.getPatente());
        patentesMarcasB.add(vehiculoC.getPatente());

        Mockito.when(vehiculoRepository.save(vehiculoA)).thenReturn(vehiculoA);
        Mockito.when(vehiculoRepository.findAll()).thenReturn(vehiculos);
        Mockito.when(vehiculoRepository.findByPatente("LJSY77")).thenReturn(vehiculoA);
        Mockito.when(vehiculoRepository.findDistinctMarca()).thenReturn(marcas);
        Mockito.when(vehiculoRepository.findPatenteByMarca("Hyundai")).thenReturn(patentesMarcasA);
        Mockito.when(vehiculoRepository.findPatenteByMarca("Chevrolet")).thenReturn(patentesMarcasB);
        Mockito.when(registroService.calcularPromedioTiempoReparacion(patentesMarcasA)).thenReturn(2L);
        Mockito.when(registroService.calcularPromedioTiempoReparacion(patentesMarcasB)).thenReturn(5L);
    }

    @Test
    public void testRegistrarVehiculo() {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente("LJSY77");
        vehiculo.setMarca("Hyundai");
        vehiculo.setModelo("Accent");
        vehiculo.setTipoAuto("Sedan");
        vehiculo.setAnoFabricacion(2019);
        vehiculo.setTipoMotor("Gasolina");
        vehiculo.setNumeroAsientos(5);
        vehiculo.setKilometraje(42000);

        VehiculoEntity vehiculoTest = vehiculoService.registrarVehiculo(vehiculo);

        assertEquals(vehiculo, vehiculoTest);
        System.out.println("Vehiculo registrado = " + vehiculoTest);
    }

    @Test
    public void testObtenerVehiculos() {
        VehiculoEntity vehiculoA = new VehiculoEntity();
        vehiculoA.setPatente("LJSY77");
        vehiculoA.setMarca("Hyundai");
        vehiculoA.setModelo("Accent");
        vehiculoA.setTipoAuto("Sedan");
        vehiculoA.setAnoFabricacion(2019);
        vehiculoA.setTipoMotor("Gasolina");
        vehiculoA.setNumeroAsientos(5);
        vehiculoA.setKilometraje(42000);

        VehiculoEntity vehiculoB = new VehiculoEntity();
        vehiculoB.setPatente("BKLF54");
        vehiculoB.setMarca("Chevrolet");
        vehiculoB.setModelo("Corsa");
        vehiculoB.setTipoAuto("Sedan");
        vehiculoB.setAnoFabricacion(2010);
        vehiculoB.setTipoMotor("Gasolina");
        vehiculoB.setNumeroAsientos(5);
        vehiculoB.setKilometraje(63000);

        List<VehiculoEntity> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculoA);
        vehiculos.add(vehiculoB);

        List<VehiculoEntity> vehiculosTest = vehiculoService.obtenerVehiculos();

        assertEquals(vehiculos, vehiculosTest);
        System.out.println("Vehiculos obtenidos = " + vehiculosTest);
    }

    @Test
    public void testActualizarVehiculo() {
        String patente = "LJSY77";
        int kilometraje = 47000;

        VehiculoEntity vehiculoTest = vehiculoService.actualizarVehiculo(patente, kilometraje);

        assertEquals(patente, vehiculoTest.getPatente());
        assertEquals(kilometraje, vehiculoTest.getKilometraje());
        System.out.println("Vehiculo actulizado = " + vehiculoTest);
    }

    @Test
    public void testReporteTiempoReparacionPorMarca() {
        TiempoReparacionPorMarca variableA = new TiempoReparacionPorMarca();
        variableA.setMarca("Hyundai");
        variableA.setTiempoPromedioReparacion(2L);

        TiempoReparacionPorMarca variableB = new TiempoReparacionPorMarca();
        variableB.setMarca("Chevrolet");
        variableB.setTiempoPromedioReparacion(5);

        List<TiempoReparacionPorMarca> tiempoReparacionesPorMarca = new ArrayList<>();
        tiempoReparacionesPorMarca.add(variableA);
        tiempoReparacionesPorMarca.add(variableB);

        List<TiempoReparacionPorMarca> tiempoReparacionPorMarcasTest = vehiculoService.reporteTiempoReparacionPorMarca();

        assertEquals(tiempoReparacionesPorMarca, tiempoReparacionPorMarcasTest);
        System.out.println("Promedio de reparaciones por marca = " + tiempoReparacionPorMarcasTest);
    }
}
