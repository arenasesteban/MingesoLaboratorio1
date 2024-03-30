package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.repositories.ReparacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReparacionService {
    @Autowired
    ReparacionRepository reparacionRepository;

    public ReparacionEntity registrarReparacion(ReparacionEntity reparacion) {
        return reparacionRepository.save(reparacion);
    }
}
