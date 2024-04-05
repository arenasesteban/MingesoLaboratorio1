package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.services.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reparacion")
public class ReparacionController {
    @Autowired
    ReparacionService reparacionService;

    @PostMapping("/")
    public ResponseEntity<List<ReparacionEntity>> crearReparacion(@RequestBody List<ReparacionEntity> reparaciones, @RequestParam Long idRegistro) {
        List<ReparacionEntity> reparacionesNuevo = reparacionService.crearReparacion(reparaciones, idRegistro);
        return ResponseEntity.ok(reparacionesNuevo);
    }
}
