package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.entities.RegistroEntity;
import com.proyectosw.autofix.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @PostMapping("/")
    public ResponseEntity<RegistroEntity> registrarRegistro(@RequestBody RegistroEntity registro) {
        RegistroEntity registroNuevo = registroService.registrarRegistro(registro);
        return ResponseEntity.ok(registroNuevo);
    }

    @GetMapping("/calcular-total")
    public ResponseEntity<RegistroEntity> calcularTotal(@RequestParam Long idRegistro, @RequestParam Boolean bono) {
        RegistroEntity registroActualizado = registroService.calcularTotal(idRegistro, bono);
        return ResponseEntity.ok(registroActualizado);
    }
}
