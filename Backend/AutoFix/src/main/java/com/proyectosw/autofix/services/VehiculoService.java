package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {
    @Autowired
    VehiculoRepository vehiculoRepository;

    public VehiculoEntity registrarVehiculo(VehiculoEntity vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }
}
