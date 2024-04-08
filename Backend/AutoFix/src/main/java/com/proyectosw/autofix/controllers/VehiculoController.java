package com.proyectosw.autofix.controllers;

import com.proyectosw.autofix.dtos.TiempoReparacionPorMarca;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {
    @Autowired
    VehiculoService vehiculoService;

    // [1]
    @PostMapping("/")
    public ResponseEntity<VehiculoEntity> registarVehiculo(@RequestBody VehiculoEntity vehiculo) {
        VehiculoEntity vehiculoNuevo = vehiculoService.registrarVehiculo(vehiculo);
        return ResponseEntity.ok(vehiculoNuevo);
    }

    // [1]
    @PutMapping("/")
    public ResponseEntity<VehiculoEntity> actualizarVehiculo(@RequestParam String patente, @RequestParam Integer kilometraje) {
        VehiculoEntity vehiculo = vehiculoService.actualizarVehiculo(patente, kilometraje);
        return ResponseEntity.ok(vehiculo);
    }

    @GetMapping("/tiempo-reparacion-por-marca")
    public ResponseEntity<List<TiempoReparacionPorMarca>> reporteTiempoReparacionPorMarca() {
        List<TiempoReparacionPorMarca> tiempoReparacionesPorMarca = vehiculoService.reporteTiempoReparacionPorMarca();
        return ResponseEntity.ok(tiempoReparacionesPorMarca);
    }
}
