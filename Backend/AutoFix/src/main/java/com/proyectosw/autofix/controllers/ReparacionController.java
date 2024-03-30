package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.services.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reparacion")
public class ReparacionController {
    @Autowired
    ReparacionService reparacionService;

    @PostMapping("/")
    public ResponseEntity<ReparacionEntity> registrarReparacion(@RequestBody ReparacionEntity reparacion) {
        ReparacionEntity reparacionNueva = reparacionService.registrarReparacion(reparacion);
        return ResponseEntity.ok(reparacionNueva);
    }
}
