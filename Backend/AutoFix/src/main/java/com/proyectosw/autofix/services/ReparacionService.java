package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.repositories.ReparacionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReparacionService {
    @Autowired
    ReparacionRespository reparacionRespository;

    public List<ReparacionEntity> crearReparacion(List<ReparacionEntity> reparaciones, Integer idRegistro) {
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
}
