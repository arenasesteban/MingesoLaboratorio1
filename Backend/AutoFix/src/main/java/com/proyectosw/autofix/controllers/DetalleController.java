package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.entities.DetalleEntity;
import com.proyectosw.autofix.services.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detalle")
public class DetalleController {
    @Autowired
    DetalleService detalleService;

    @GetMapping("/")
    public ResponseEntity<DetalleEntity> obtenerDetalle(@RequestParam Long idRegistro) {
        DetalleEntity detalle = detalleService.obtenerDetalle(idRegistro);
        return ResponseEntity.ok(detalle);
    }
}
