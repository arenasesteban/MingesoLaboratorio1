package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.BonoEntity;
import com.proyectosw.autofix.repositories.BonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonoService {
    @Autowired
    BonoRepository bonoRepository;

    public int aplicarBono(String marca) {
        BonoEntity bono = bonoRepository.findByMarca(marca);
        bono.setCantidadBonos(bono.getCantidadBonos() - 1); // Logica para saber si quedan bonos en front

        return bono.getMontoPorBonos();
    }
}
