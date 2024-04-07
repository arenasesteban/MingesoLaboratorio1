package com.proyectosw.autofix.services;

import com.proyectosw.autofix.dtos.ReparacionPorTipoAuto;
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

    public int calcularTotalReparaciones(Long idRegistro) {
        List<ReparacionEntity> reparaciones = reparacionRespository.findByIdRegistro(idRegistro);
        int sumaReparaciones = 0;

        for(ReparacionEntity reparacion : reparaciones) {
            sumaReparaciones += reparacion.getPrecio();
        }

        return sumaReparaciones;
    }

    public int contarReparaciones(Long idRegistro) {
        List<ReparacionEntity> reparaciones = reparacionRespository.findByIdRegistro(idRegistro);
        return reparaciones.toArray().length;
    }

    public List<ReparacionPorTipoAuto> reporteReparacionPorTipoAuto() {
        List<ReparacionPorTipoAuto> reparacionesPorTipoAuto = new ArrayList<>();

        for(int numeroReparacion = 1; numeroReparacion <= 11; numeroReparacion++) {
            List<Long> idRegistros = reparacionRespository.findIdRegistroByNumeroReparacion(numeroReparacion);

            String tipoReparacion = reparacionRespository.getByIdRegistro(idRegistros.get(0)).getTipoReparacion();
            int numeroTiposAutos = registroService.obtenerNumeroTiposAutos(idRegistros);
            int montoTotal = reparacionRespository.sumPrecioByNumeroReparacion(numeroReparacion);

            ReparacionPorTipoAuto reparacionPorTipoAuto = new ReparacionPorTipoAuto(tipoReparacion, numeroTiposAutos, montoTotal);
            reparacionesPorTipoAuto.add(reparacionPorTipoAuto);
        }

        return reparacionesPorTipoAuto;
    }
}
