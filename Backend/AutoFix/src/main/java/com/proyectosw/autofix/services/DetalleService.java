package com.proyectosw.autofix.services;

import com.proyectosw.autofix.repositories.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleService {
    @Autowired
    DetalleRepository detalleRepository;

}
