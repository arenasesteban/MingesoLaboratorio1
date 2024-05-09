package com.proyectosw.autofix.services;

import com.proyectosw.autofix.controllers.ReparacionController;
import com.proyectosw.autofix.dtos.ReparacionPorTipoAuto;
import com.proyectosw.autofix.dtos.ReparacionPorTipoMotor;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.repositories.ReparacionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReparacionService {
    @Autowired
    ReparacionRespository reparacionRespository;

    @Autowired
    RegistroService registroService;

    public List<ReparacionEntity> crearReparacion(List<ReparacionEntity> reparaciones, Long idRegistro) {
        List<ReparacionEntity> reparacionesGuardados = new ArrayList<>();

        for(ReparacionEntity reparacion : reparaciones) {
            reparacion.setIdRegistro(idRegistro);
            ReparacionEntity reparacionGuardado = reparacionRespository.save(reparacion);
            reparacionesGuardados.add(reparacionGuardado);
        }

        return reparacionesGuardados;
    }

    public List<ReparacionPorTipoAuto> reporteReparacionPorTipoAuto() {
        List<ReparacionPorTipoAuto> reparacionesPorTipoAuto = new ArrayList<>();
        List<String> tiposReparaciones = reparacionRespository.findDistinctTipoReparacion();

        for(String tipoReparacion : tiposReparaciones) {
            List<Long> idRegistros = reparacionRespository.findIdRegistroByTipoReparacion(tipoReparacion);
            int cantidadSedan = registroService.obtenerNumeroPorTiposAutos(idRegistros, "Sedan");
            int cantidadHatchback = registroService.obtenerNumeroPorTiposAutos(idRegistros, "Hatchback");
            int cantidadSUV = registroService.obtenerNumeroPorTiposAutos(idRegistros, "SUV");
            int cantidadPickup = registroService.obtenerNumeroPorTiposAutos(idRegistros, "Pickup");
            int cantidadFurgoneta = registroService.obtenerNumeroPorTiposAutos(idRegistros, "Furgoneta");
            int montoTotal = reparacionRespository.sumPrecioByTipoReparacion(tipoReparacion);

            ReparacionPorTipoAuto reparacionPorTipoAuto = new ReparacionPorTipoAuto(tipoReparacion, cantidadSedan, cantidadHatchback, cantidadSUV, cantidadPickup, cantidadFurgoneta, montoTotal);
            reparacionesPorTipoAuto.add(reparacionPorTipoAuto);
        }
        return reparacionesPorTipoAuto;
    }

    public List<ReparacionPorTipoMotor> reporteReparacionPorTipoMotor() {
        List<ReparacionPorTipoMotor> reparacionesTipoMotor = new ArrayList<>();
        List<String> tiposReparaciones = reparacionRespository.findDistinctTipoReparacion();

        for(String tipoReparacion : tiposReparaciones) {
            List<Long> idRegistros = reparacionRespository.findIdRegistroByTipoReparacion(tipoReparacion);
            int cantidadGasolina = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Gasolina");
            int cantidadDiesel = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Diesel");
            int cantidadHibrido = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Hibrido");
            int cantidadElectrico = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Electrico");
            int montoTotal = reparacionRespository.sumPrecioByTipoReparacion(tipoReparacion);

            ReparacionPorTipoMotor reparacionPorTipoMotor = new ReparacionPorTipoMotor(tipoReparacion, cantidadGasolina, cantidadDiesel, cantidadHibrido, cantidadElectrico, montoTotal);
            reparacionesTipoMotor.add(reparacionPorTipoMotor);
        }
        return reparacionesTipoMotor;
    }
}
