package com.autofix.backend.controller;

import com.autofix.backend.dto.CalculoReparacionDTO;
import com.autofix.backend.entities.Vehiculo;
import com.autofix.backend.entities.Reparacion;

import com.autofix.backend.service.CalculadoraService;
import com.autofix.backend.service.ReparacionService;
import com.autofix.backend.service.VehiculoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    private final CalculadoraService calculadoraService;
    private final VehiculoService vehiculoService;
    private final ReparacionService reparacionService;

    public CalculadoraController(CalculadoraService calculadoraService, VehiculoService vehiculoService, ReparacionService reparacionService) {
        this.vehiculoService = vehiculoService;
        this.reparacionService = reparacionService;
        this.calculadoraService = calculadoraService;
    }

    /*@GetMapping("/calculo/{vehiculoId}/{reparacionId}")
    public CalculoReparacionDTO getCalculoReparacion(@PathVariable Long vehiculoId, @PathVariable Long reparacionId) {
        Vehiculo vehiculo = vehiculoService.getVehiculoById(vehiculoId);
        Reparacion reparacion = reparacionService.getReparacionById(reparacionId);// fetch reparacion by reparacionId
        return calculadoraService.getCalculoReparacion(vehiculo, reparacion);
    }*/
}