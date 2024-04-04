package com.proyectosw.autofix.services;

import com.proyectosw.autofix.entities.RegistroEntity;
import com.proyectosw.autofix.repositories.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {
    @Autowired
    RegistroRepository registroRepository;

    public RegistroEntity registrarRegistro(RegistroEntity registro) {
        return registroRepository.save(registro);
    }
}
