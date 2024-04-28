package com.autofix.backend.service;

import com.autofix.backend.entities.Reparacion;
import com.autofix.backend.repositories.ReparacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReparacionService {
    ReparacionRepository reparacionRepository;

    @Autowired
    public ReparacionService(ReparacionRepository reparacionRepository){
        this.reparacionRepository = reparacionRepository;
    }

    public Reparacion registrarReparacion(Reparacion reparacion) {
        return reparacionRepository.save(reparacion);
    }

    public List<Reparacion> obtenerTodasLasReparaciones() {
        return reparacionRepository.findAll();
    }
}
