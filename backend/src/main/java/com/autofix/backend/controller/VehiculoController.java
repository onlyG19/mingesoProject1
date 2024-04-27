package com.autofix.backend.controller;

import com.autofix.backend.entities.Vehiculo;
import com.autofix.backend.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping
    public Vehiculo registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.registrarVehiculo(vehiculo);
    }

    @GetMapping
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoService.obtenerTodosLosVehiculos();
    }

}
