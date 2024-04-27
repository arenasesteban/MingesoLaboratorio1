package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.RegistroEntity;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.repositories.RegistroRepository;
import com.proyectosw.autofix.repositories.ReparacionRespository;
import com.proyectosw.autofix.repositories.VehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RegistroServiceTest {
    @InjectMocks
    RegistroService registroService;

    @Mock
    RegistroRepository registroRepository;

    @Mock
    VehiculoRepository vehiculoRepository;

    @Mock
    ReparacionRespository reparacionRepository;

    @BeforeEach
    public void setUp() {
        RegistroEntity registroA = new RegistroEntity();
        registroA.setFechaIngreso(LocalDate.parse("2023-05-10"));
        registroA.setHoraIngreso(LocalTime.parse("15:00"));
        registroA.setFechaSalida(LocalDate.parse("2023-05-11"));
        registroA.setHoraSalida(LocalTime.parse("12:30"));
        registroA.setFechaRetiro(LocalDate.parse("2023-05-12"));
        registroA.setHoraRetiro(LocalTime.parse("16:15"));
        registroA.setPatente("LJSY77");

        RegistroEntity registroId = new RegistroEntity();
        registroId.setFechaIngreso(registroA.getFechaIngreso());
        registroId.setHoraIngreso(registroA.getHoraIngreso());
        registroId.setFechaSalida(registroA.getFechaSalida());
        registroId.setHoraSalida(registroA.getHoraSalida());
        registroId.setFechaRetiro(registroA.getFechaRetiro());
        registroId.setHoraRetiro(registroA.getHoraRetiro());
        registroId.setPatente(registroA.getPatente());
        registroId.setIdRegistro(1L);

        RegistroEntity registroB = new RegistroEntity();
        registroB.setFechaIngreso(LocalDate.parse("2023-07-06"));
        registroB.setHoraIngreso(LocalTime.parse("11:45"));
        registroB.setFechaSalida(LocalDate.parse("2023-07-07"));
        registroB.setHoraSalida(LocalTime.parse("13:00"));
        registroB.setFechaRetiro(LocalDate.parse("2023-07-08"));
        registroB.setHoraRetiro(LocalTime.parse("18:00"));
        registroB.setPatente("LJSY77");
        registroB.setIdRegistro(2L);

        List<RegistroEntity> registrosA = new ArrayList<>();
        registrosA.add(registroId);
        registrosA.add(registroB);

        VehiculoEntity vehiculoA = new VehiculoEntity();
        vehiculoA.setPatente("LJSY77");
        vehiculoA.setMarca("Hyundai");
        vehiculoA.setModelo("Accent");
        vehiculoA.setTipoAuto("Sedan");
        vehiculoA.setAnoFabricacion(2019);
        vehiculoA.setTipoMotor("Gasolina");
        vehiculoA.setNumeroAsientos(5);
        vehiculoA.setKilometraje(42000);

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setNumeroReparacion(1);
        reparacionA.setTipoReparacion("Reparaciones del Sistema de Frenos");
        reparacionA.setPrecio(120000);
        reparacionA.setIdRegistro(1L);
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setNumeroReparacion(5);
        reparacionB.setTipoReparacion("Reparación del Sistema Eléctrico");
        reparacionB.setPrecio(150000);
        reparacionB.setIdRegistro(1L);
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionB.setNumeroReparacion(4);
        reparacionB.setTipoReparacion("Reparaciones de la Transmisión");
        reparacionB.setPrecio(150000);
        reparacionB.setIdRegistro(2L);
        reparacionB.setIdReparacion(3L);

        List<ReparacionEntity> reparacionesA = new ArrayList<>();
        reparacionesA.add(reparacionA);
        reparacionesA.add(reparacionB);

        List<ReparacionEntity> reparacionesB = new ArrayList<>();
        reparacionesB.add(reparacionC);

        Mockito.when(registroRepository.save(registroA)).thenReturn(registroId);
        Mockito.when(registroRepository.findAll()).thenReturn(registrosA);
        Mockito.when(registroRepository.findByIdRegistro(1L)).thenReturn(registroId);
        Mockito.when(vehiculoRepository.findByPatente("LJSY77")).thenReturn(vehiculoA);

        Mockito.when(reparacionRepository.findByIdRegistro(1L)).thenReturn(reparacionesA);
        Mockito.when(reparacionRepository.findByIdRegistro(2L)).thenReturn(reparacionesB);

        Mockito.when(registroRepository.findByPatenteAndFechaSalidaAfter("LJSY77", LocalDate.now().minusMonths(12).minusDays(1)))
                .thenReturn(registrosA);
        // Mockito.when(registroRepository.findPatenteByIdRegistro(1L)).thenReturn("LJSY77");
    }

    @Test
    public void testCrearRegistro() {
        RegistroEntity registro = new RegistroEntity();
        registro.setFechaIngreso(LocalDate.parse("2023-05-10"));
        registro.setHoraIngreso(LocalTime.parse("15:00"));
        registro.setFechaSalida(LocalDate.parse("2023-05-11"));
        registro.setHoraSalida(LocalTime.parse("12:30"));
        registro.setFechaRetiro(LocalDate.parse("2023-05-12"));
        registro.setHoraRetiro(LocalTime.parse("16:15"));
        registro.setPatente("LJSY77");

        RegistroEntity registroTest = registroService.crearRegistro(registro);

        registro.setIdRegistro(1L);

        assertEquals(registro.getPatente(), registroTest.getPatente());
        assertEquals(registro.getIdRegistro(), registroTest.getIdRegistro());
        System.out.println("Registro creado = " + registroTest);
    }

    @Test
    public void testObtenerRegistros() {
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

        List<RegistroEntity> registrosTest = registroService.obtenerRegistros();

        assertEquals(registros, registrosTest);
        System.out.println("Registros obtenidos = " + registrosTest);
    }

    @Test
    public void testCalcularTotal() {

    }

    @Test
    public void testCalcularTotalReparaciones() {
        int sumaReparaciones = 270000;

        int sumaReparacionesTest = registroService.calcularTotalReparaciones(1L);

        assertEquals(sumaReparaciones, sumaReparacionesTest);
        System.out.println("Suma de reparaciones = " + sumaReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparaciones() {
        double descuento = .1;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("LJSY77", "Gasolina");

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorDiaAtencion() {
        double descuento = .1;

        double descuentoTest = registroService.descuentoPorDiaAtencion(LocalDate.parse("2023-05-11"), LocalTime.parse("10:15"));

        assertEquals(descuento, descuentoTest);
        System.out.println("Descuento por dia de atencion = " + descuentoTest);
    }

    @Test
    public void testRecargoPorKilometraje() {
        double recargo = .03;

        double recargoTest = registroService.recargoPorKilometraje(6000, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por kilometraje = " + recargoTest);
    }

    @Test
    public void testRecargoPorAntiguedad() {
        double recargo = .05;

        double recargoTest = registroService.recargoPorAntiguedad(2018, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por antiguedad = " + recargoTest);
    }

    @Test
    public void testRecargoPorRetrasoRecogida() {
        double recargo = .05;

        double recargoTest = registroService.recargoPorRetrasoRecogida(LocalDate.parse("2023-05-11"), LocalDate.parse("2023-05-12"));

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por retraso en recogida = " + recargoTest);
    }

    @Test
    public void contarReparaciones() {
        int cantidadReparaciones = 3;

        int cantidadReparacionesTest = registroService.contarReparaciones("LJSY77");

        assertEquals(cantidadReparaciones, cantidadReparacionesTest);
        System.out.println("Cantidad de reparaciones = " + cantidadReparacionesTest);
    }
}
