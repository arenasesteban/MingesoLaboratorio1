package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.RegistroEntity;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.repositories.ReparacionRespository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
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

        VehiculoEntity vehiculoA = new VehiculoEntity();
        vehiculoA.setPatente("LJSY77");
        vehiculoA.setMarca("Hyundai");
        vehiculoA.setModelo("Accent");
        vehiculoA.setTipoAuto("Sedan");
        vehiculoA.setAnoFabricacion(2019);
        vehiculoA.setTipoMotor("Gasolina");
        vehiculoA.setNumeroAsientos(5);
        vehiculoA.setKilometraje(42000);

        RegistroEntity registroA = new RegistroEntity();
        registroA.setFechaIngreso(LocalDate.parse("10-04-2020"));
        registroA.setHoraIngreso(LocalTime.parse("15:00"));
        registroA.setFechaSalida(LocalDate.parse("11-04-2020"));
        registroA.setHoraSalida(LocalTime.parse("12:30"));
        registroA.setFechaRetiro(LocalDate.parse("12-04-2020"));
        registroA.setHoraRetiro(LocalTime.parse("16:15"));
        registroA.setPatente("LJSY77");
        registroA.setIdRegistro(1L);

        RegistroEntity registroB = new RegistroEntity();
        registroB.setFechaIngreso(LocalDate.parse("06-07-2023"));
        registroB.setHoraIngreso(LocalTime.parse("11:45"));
        registroB.setFechaSalida(LocalDate.parse("07-07-2023"));
        registroB.setHoraSalida(LocalTime.parse("13:00"));
        registroB.setFechaRetiro(LocalDate.parse("08-07-2023"));
        registroB.setHoraRetiro(LocalTime.parse("18:00"));
        registroB.setPatente("LJSY77");
        registroB.setIdRegistro(2L);

        Mockito.when(reparacionRespository.save(reparacionA)).thenReturn(reparacionId);
        Mockito.when(reparacionRespository.findIdRegistroByNumeroReparacion(1)).thenReturn(idRegistros);
        Mockito.when(reparacionRespository.findFirstByIdRegistro(idRegistros.get(0))).thenReturn(reparacionId);
        Mockito.when(registroService.obtenerNumeroTiposAutos(idRegistros)).thenReturn(1);
        Mockito.when(reparacionRespository.sumPrecioByNumeroReparacion(1)).thenReturn(240000);
        Mockito.when(registroService.obtenerNumeroPorTiposMotores(idRegistros, "Gasolina")).thenReturn(1);
    }
}
