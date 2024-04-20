package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.dtos.ReparacionPorTipoAuto;
import com.proyectosw.autofix.dtos.ReparacionPorTipoMotor;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.services.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reparacion")
@CrossOrigin("*")
public class ReparacionController {
    @Autowired
    ReparacionService reparacionService;

    // [1]
    @PostMapping("/")
    public ResponseEntity<List<ReparacionEntity>> crearReparacion(@RequestBody List<ReparacionEntity> reparaciones, @RequestParam Long idRegistro) {
        List<ReparacionEntity> reparacionesNuevo = reparacionService.crearReparacion(reparaciones, idRegistro);
        return ResponseEntity.ok(reparacionesNuevo);
    }

    // [1]
    @GetMapping("/reparaciones-por-tipo-auto")
    public ResponseEntity<List<ReparacionPorTipoAuto>> reporteReparacionPorTipoAuto() {
        List<ReparacionPorTipoAuto> reparacionesPorTipoAuto= reparacionService.reporteReparacionPorTipoAuto();
        return ResponseEntity.ok(reparacionesPorTipoAuto);
    }

    // [1]
    @GetMapping("/reparaciones-por-tipo-motor")
    public ResponseEntity<List<ReparacionPorTipoMotor>> reporteReparacionPorTipoMotor() {
        List<ReparacionPorTipoMotor> reparacionesPorTipoMotor= reparacionService.reporteReparacionPorTipoMotor();
        return ResponseEntity.ok(reparacionesPorTipoMotor);
    }
}
