package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.RegistroEntity;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.repositories.RegistroRepository;
import com.proyectosw.autofix.repositories.ReparacionRespository;
import com.proyectosw.autofix.repositories.VehiculoRepository;
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
public class RegistroServiceTest {
    @InjectMocks
    RegistroService registroService;

    @Mock
    RegistroRepository registroRepository;

    @Mock
    VehiculoRepository vehiculoRepository;

    @Mock
    ReparacionRespository reparacionRespository;

    @BeforeEach
    public void setUp() {
        RegistroEntity registroA = new RegistroEntity();
        registroA.setFechaIngreso(LocalDate.parse("10-04-2020"));
        registroA.setHoraIngreso(LocalTime.parse("15:00"));
        registroA.setFechaSalida(LocalDate.parse("11-04-2020"));
        registroA.setHoraSalida(LocalTime.parse("12:30"));
        registroA.setFechaRetiro(LocalDate.parse("12-04-2020"));
        registroA.setHoraRetiro(LocalTime.parse("16:15"));
        registroA.setPatente("LJSY77");

        RegistroEntity registroId = new RegistroEntity();
        registroA.setFechaIngreso(registroA.getFechaIngreso());
        registroA.setHoraIngreso(registroA.getHoraIngreso());
        registroA.setFechaSalida(registroA.getFechaSalida());
        registroA.setHoraSalida(registroA.getHoraSalida());
        registroA.setFechaRetiro(registroA.getFechaRetiro());
        registroA.setHoraRetiro(registroA.getHoraRetiro());
        registroA.setPatente(registroA.getPatente());
        registroId.setIdRegistro(1L);

        RegistroEntity registroB = new RegistroEntity();
        registroB.setFechaIngreso(LocalDate.parse("06-07-2023"));
        registroB.setHoraIngreso(LocalTime.parse("11:45"));
        registroB.setFechaSalida(LocalDate.parse("07-07-2023"));
        registroB.setHoraSalida(LocalTime.parse("13:00"));
        registroB.setFechaRetiro(LocalDate.parse("08-07-2023"));
        registroB.setHoraRetiro(LocalTime.parse("18:00"));
        registroB.setPatente("BKLF54");
        registroId.setIdRegistro(2L);

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

        List<RegistroEntity> registrosB = new ArrayList<>();
        registrosB.add(registroId);

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

        List<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacionA);
        reparaciones.add(reparacionB);

        Mockito.when(registroRepository.save(registroA)).thenReturn(registroId);
        Mockito.when(registroRepository.findAll()).thenReturn(registrosA);
        Mockito.when(registroRepository.findByIdRegistro(1L)).thenReturn(registroId);
        Mockito.when(vehiculoRepository.findByPatente(registroId.getPatente())).thenReturn(vehiculoA);
        Mockito.when(reparacionRespository.findByIdRegistro(1L)).thenReturn(reparaciones);
        Mockito.when((registroRepository.findByPatenteAndFechaSalidaAfter("LJSY77", LocalDate.now().minusMonths(12).minusDays(1))))
                .thenReturn(registrosB);
        Mockito.when(registroRepository.findPatenteByIdRegistro(1L)).thenReturn("LJSY77");
        Mockito.when(vehiculoRepository.findByPatente("LJSY77")).thenReturn(vehiculoA);
    }
}
