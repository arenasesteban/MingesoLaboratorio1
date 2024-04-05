package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.DetalleEntity;
import com.proyectosw.autofix.repositories.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleService {
    @Autowired
    DetalleRepository detalleRepository;

    public void crearDetalle(Integer sumaReparaciones, Integer recargos, Integer descuentos, Integer iva, Long idRegistro) {
        new DetalleEntity(sumaReparaciones, recargos, descuentos, iva, idRegistro);
    }

    public DetalleEntity obtenerDetalle(Long idRegistro) {
        return detalleRepository.findByIdRegistro(idRegistro);
    }
}
