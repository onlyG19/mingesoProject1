package com.autofix.backend.controller;

import com.autofix.backend.entities.Reparacion;
import com.autofix.backend.service.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reparaciones")
@CrossOrigin("*")
public class ReparacionController {

    private final ReparacionService reparacionService;

    @Autowired
    public ReparacionController(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }

    @PostMapping
    public Reparacion registrarReparacion(@RequestBody Reparacion reparacion) {
        return reparacionService.registrarReparacion(reparacion);
    }

    @GetMapping
    public List<Reparacion> obtenerTodasLasReparaciones() {
        return reparacionService.obtenerTodasLasReparaciones();
    }

}
