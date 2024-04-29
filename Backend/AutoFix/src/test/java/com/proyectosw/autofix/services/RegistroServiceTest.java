package com.proyectosw.autofix.services;

import com.proyectosw.autofix.dtos.Detalle;
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

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registroId.getIdRegistro());
        idRegistros.add(registroB.getIdRegistro());

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

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("LJSY77", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(registroRepository.findPatenteByIdRegistro(1L)).thenReturn("LJSY77");
        Mockito.when(registroRepository.findPatenteByIdRegistro(2L)).thenReturn("LJSY77");
        Mockito.when(vehiculoRepository.countTipoMotor("LJSY77", "Gasolina")).thenReturn(1);
        Mockito.when(registroRepository.findRegistroEntitiesByPatente("LJSY77")).thenReturn(registrosA);
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
        Detalle detalle = new Detalle();
        detalle.setReparaciones(270000);
        detalle.setRecargos(67500);
        detalle.setDescuentos(27000);
        detalle.setIva(58995);
        detalle.setMontoTotal(369495);

        Detalle detalleTest = registroService.calcularTotal(1L, 0);

        assertEquals(detalle, detalleTest);
        System.out.println("Detalle registro = " + detalleTest);
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

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("LJSY77", "Gasolina", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }


    @Test
    public void testDescuentoPorNumeroReparacionesCaso0() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .1;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Gasolina", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso1() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .12;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Diesel", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);

}
    @Test
    public void testDescuentoPorNumeroReparacionesCaso2() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacion);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .05;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Gasolina", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso3() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacion);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .07;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Diesel", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso4() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacion);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .1;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Híbrido", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso5() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacion);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .08;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Eléctrico", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso6() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .15;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Gasolina", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso7() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .17;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Diesel", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso8() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .17;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Diesel", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso9() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .2;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Híbrido", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso10() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .18;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Eléctrico", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso11() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionG = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionH = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionI = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionJ = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);
        reparaciones.add(reparacionG);
        reparaciones.add(reparacionH);
        reparaciones.add(reparacionI);
        reparaciones.add(reparacionJ);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .2;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Gasolina", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso12() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionG = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionH = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionI = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionJ = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);
        reparaciones.add(reparacionG);
        reparaciones.add(reparacionH);
        reparaciones.add(reparacionI);
        reparaciones.add(reparacionJ);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .22;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Diesel", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso13() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionG = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionH = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionI = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionJ = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);
        reparaciones.add(reparacionG);
        reparaciones.add(reparacionH);
        reparaciones.add(reparacionI);
        reparaciones.add(reparacionJ);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .25;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Híbrido", LocalDate.parse("2023-05-10"));

        assertEquals(descuento, descuentoPorNumeroReparacionesTest);
        System.out.println("Descuento por numero reparaciones = " + descuentoPorNumeroReparacionesTest);
    }

    @Test
    public void testDescuentoPorNumeroReparacionesCaso14() {
        RegistroEntity registro = new RegistroEntity();
        registro.setIdRegistro(1L);

        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(registro.getIdRegistro());

        ReparacionEntity reparacionA = new ReparacionEntity();
        reparacionA.setIdReparacion(1L);

        ReparacionEntity reparacionB = new ReparacionEntity();
        reparacionB.setIdReparacion(2L);

        ReparacionEntity reparacionC = new ReparacionEntity();
        reparacionC.setIdReparacion(3L);

        ReparacionEntity reparacionD = new ReparacionEntity();
        reparacionD.setIdReparacion(4L);

        ReparacionEntity reparacionE = new ReparacionEntity();
        reparacionE.setIdReparacion(5L);

        ReparacionEntity reparacionF = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionG = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionH = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionI = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        ReparacionEntity reparacionJ = new ReparacionEntity();
        reparacionF.setIdReparacion(6L);

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);
        reparaciones.add(reparacionC);
        reparaciones.add(reparacionD);
        reparaciones.add(reparacionE);
        reparaciones.add(reparacionF);
        reparaciones.add(reparacionG);
        reparaciones.add(reparacionH);
        reparaciones.add(reparacionI);
        reparaciones.add(reparacionJ);

        Mockito.when(registroRepository.findByPatenteAndFechaReparacionAnterior("BKLF54", LocalDate.parse("2022-05-09"), LocalDate.parse("2023-05-10")))
                .thenReturn(idRegistros);
        Mockito.when(reparacionRepository.findByIdRegistro(registro.getIdRegistro())).thenReturn(reparaciones);

        double descuento = .23;

        double descuentoPorNumeroReparacionesTest = registroService.descuentoPorNumeroReparaciones("BKLF54", "Eléctrico", LocalDate.parse("2023-05-10"));

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
    public void testRecargoPorKilometrajeCaso1() {
        double recargo = .03;

        double recargoTest = registroService.recargoPorKilometraje(6000, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por kilometraje = " + recargoTest);
    }

    @Test
    public void testRecargoPorKilometrajeCaso2() {
        double recargo = .07;

        double recargoTest = registroService.recargoPorKilometraje(13000, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por kilometraje = " + recargoTest);
    }

    @Test
    public void testRecargoPorKilometrajeCaso3() {
        double recargo = .12;

        double recargoTest = registroService.recargoPorKilometraje(26000, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por kilometraje = " + recargoTest);
    }

    @Test
    public void testRecargoPorKilometrajeCaso4() {
        double recargo = .2;

        double recargoTest = registroService.recargoPorKilometraje(41000, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por kilometraje = " + recargoTest);
    }

    @Test
    public void testRecargoPorKilometrajeCaso5() {
        double recargo = .05;

        double recargoTest = registroService.recargoPorKilometraje(6000, "SUV");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por kilometraje = " + recargoTest);
    }

    @Test
    public void testRecargoPorKilometrajeCaso6() {
        double recargo = .09;

        double recargoTest = registroService.recargoPorKilometraje(13000, "SUV");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por kilometraje = " + recargoTest);
    }

    @Test
    public void testRecargoPorAntiguedadCaso1() {
        double recargo = .05;

        double recargoTest = registroService.recargoPorAntiguedad(2018, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por antiguedad = " + recargoTest);
    }
    @Test
    public void testRecargoPorAntiguedadCaso2() {
        double recargo = .09;

        double recargoTest = registroService.recargoPorAntiguedad(2013, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por antiguedad = " + recargoTest);
    }

    @Test
    public void testRecargoPorAntiguedadCaso3() {
        double recargo = .15;

        double recargoTest = registroService.recargoPorAntiguedad(2008, "Sedan");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por antiguedad = " + recargoTest);
    }

    @Test
    public void testRecargoPorAntiguedadCaso4() {
        double recargo = .07;

        double recargoTest = registroService.recargoPorAntiguedad(2018, "SUV");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por antiguedad = " + recargoTest);
    }

    @Test
    public void testRecargoPorAntiguedadCaso5() {
        double recargo = .11;

        double recargoTest = registroService.recargoPorAntiguedad(2013, "SUV");

        assertEquals(recargo, recargoTest);
        System.out.println("Recargo por antiguedad = " + recargoTest);
    }

    @Test
    public void testRecargoPorAntiguedadCaso6() {
        double recargo = .20;

        double recargoTest = registroService.recargoPorAntiguedad(2008, "SUV");

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
    public void testContarReparaciones() {
        int cantidadReparaciones = 3;

        int cantidadReparacionesTest = registroService.contarReparaciones("LJSY77", LocalDate.parse("2023-05-10"));
        System.out.println("Cantidad de reparaciones = " + cantidadReparacionesTest);
        assertEquals(cantidadReparaciones, cantidadReparacionesTest);

    }

    @Test
    public void testObtenerNumeroTiposAutos() {
        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(1L);
        idRegistros.add(2L);

        int tiposAutos = 1;

        int tiposAutosTest = registroService.obtenerNumeroTiposAutos(idRegistros);

        assertEquals(tiposAutos, tiposAutosTest);
        System.out.println("Cantidad tipos de autos = " + tiposAutos);
    }

    @Test
    public void testObtenerNumeroPorTiposMotores() {
        List<Long> idRegistros = new ArrayList<>();
        idRegistros.add(1L);
        idRegistros.add(2L);

        int numeroAutos = 1;

        int numeroAutosTest = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Gasolina");

        assertEquals(numeroAutos, numeroAutosTest);
        System.out.println("Cantidad de autos = " + numeroAutosTest);
    }

    @Test
    public void testCalcularPromedioTiempoReparacion() {
        List<String> patentes = new ArrayList<>();
        patentes.add("LJSY77");

        double promedioTiempoReparacion = 1;

        double promedioTiempoReparacionTest = registroService.calcularPromedioTiempoReparacion(patentes);

        assertEquals(promedioTiempoReparacion, promedioTiempoReparacionTest);
        System.out.println("Promedio tiempo de reparacion = " + promedioTiempoReparacionTest);
    }
}