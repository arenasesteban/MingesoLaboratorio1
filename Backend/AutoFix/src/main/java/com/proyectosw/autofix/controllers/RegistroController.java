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

    // [1]
    @PostMapping("/")
    public ResponseEntity<RegistroEntity> crearRegistro(@RequestBody RegistroEntity registro) {
        RegistroEntity registroNuevo = registroService.crearRegistro(registro);
        return ResponseEntity.ok(registroNuevo);
    }

    // [1]
    @GetMapping("/calcular-total")
    public ResponseEntity<RegistroEntity> calcularTotal(@RequestParam Long idRegistro, @RequestParam int descuentoPorBono) {
        RegistroEntity registroActualizado = registroService.calcularTotal(idRegistro, descuentoPorBono);
        return ResponseEntity.ok(registroActualizado);
    }
}
