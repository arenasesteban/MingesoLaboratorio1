package com.proyectosw.autofix.services;

import com.proyectosw.autofix.dtos.TiempoReparacionPorMarca;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoService {
    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    RegistroService registroService;

    public VehiculoEntity registrarVehiculo(VehiculoEntity vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public List<VehiculoEntity> obtenerVehiculos() {
        return vehiculoRepository.findAll();
    }

    public VehiculoEntity actualizarVehiculo(String patente, Integer kilometraje) {
        VehiculoEntity vehiculo = vehiculoRepository.findByPatente(patente);
        vehiculo.setKilometraje(kilometraje);
        return vehiculoRepository.save(vehiculo);
    }

    public List<TiempoReparacionPorMarca> reporteTiempoReparacionPorMarca() {
        List<String> marcas = vehiculoRepository.findDistinctMarca();
        List<TiempoReparacionPorMarca> tiempoReparacionesPorMarca = new ArrayList<>();

        for(String marca : marcas) {
            List<String> patentes = vehiculoRepository.findPatenteByMarca(marca);
            long tiempoPromedioReparacion = registroService.calcularPromedioTiempoReparacion(patentes);

            TiempoReparacionPorMarca tiempoReparacionPorMarca = new TiempoReparacionPorMarca(marca, tiempoPromedioReparacion);
            tiempoReparacionesPorMarca.add(tiempoReparacionPorMarca);
        }

        return tiempoReparacionesPorMarca;
    }
}
