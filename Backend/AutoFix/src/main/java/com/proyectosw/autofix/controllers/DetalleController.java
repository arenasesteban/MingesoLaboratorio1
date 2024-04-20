package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.entities.DetalleEntity;
import com.proyectosw.autofix.services.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalle")
@CrossOrigin("*")
public class DetalleController {
    @Autowired
    DetalleService detalleService;

    // [1]
    @GetMapping("/")
    public ResponseEntity<DetalleEntity> obtenerDetalle(@RequestParam Long idRegistro) {
        DetalleEntity detalle = detalleService.obtenerDetalle(idRegistro);
        return ResponseEntity.ok(detalle);
    }
}
