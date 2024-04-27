package com.autofix.backend.controller;

import com.autofix.backend.repositories.ReparacionRepository;
import com.autofix.backend.service.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reparaciones")
public class ReparacionController {

    private final ReparacionService reparacionService;

    @Autowired
    public ReparacionController(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }

}
