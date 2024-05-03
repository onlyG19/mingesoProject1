package com.autofix.backend.controller;

import com.autofix.backend.entities.BonoDcto;
import com.autofix.backend.service.BonoDctoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bonos")
@CrossOrigin("*")
public class BonoDctoController {
    private final BonoDctoService bonoDctoService;

    @Autowired
    public BonoDctoController(BonoDctoService bonoDctoService) {
        this.bonoDctoService = bonoDctoService;
    }

    @PostMapping
    public BonoDcto registrarBonoDcto(@RequestBody BonoDcto bonoDcto) {
        return bonoDctoService.registrarBonoDcto(bonoDcto);
    }

    @GetMapping
    public List<BonoDcto> obtenerTodosLosBonosDcto() {
        return bonoDctoService.obtenerTodosLosBonosDcto();
    }
}
