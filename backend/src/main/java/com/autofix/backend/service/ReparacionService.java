package com.autofix.backend.service;

import com.autofix.backend.dto.CalculoReparacionDTO;
import com.autofix.backend.entities.Reparacion;
import com.autofix.backend.repositories.CalculoReparacionDTORepository;
import com.autofix.backend.repositories.ReparacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReparacionService {
    CalculadoraService calculadoraService;
    ReparacionRepository reparacionRepository;
    CalculoReparacionDTORepository calculoReparacionDTORepository;


    @Autowired
    public ReparacionService(ReparacionRepository reparacionRepository, CalculoReparacionDTORepository calculoReparacionDTORepository, CalculadoraService calculadoraService){
        this.reparacionRepository = reparacionRepository;
        this.calculoReparacionDTORepository = calculoReparacionDTORepository;
        this.calculadoraService = calculadoraService;
    }


    public Reparacion registrarReparacion(Reparacion reparacion) {
        // Calcular el monto total
        CalculoReparacionDTO calculoReparacionDTO = calculadoraService.calcularReparacion(reparacion);
        reparacion.setMontoTotal(calculoReparacionDTO.getMontoTotalFinal());

        // Guardar la reparacion
        Reparacion savedReparacion = reparacionRepository.save(reparacion);

        // Guardar el CalculoReparacionDTO
        calculoReparacionDTO.setId_reparacion(savedReparacion.getId_reparacion());
        calculoReparacionDTORepository.save(calculoReparacionDTO);

        return savedReparacion;
    }

    public List<Reparacion> obtenerTodasLasReparaciones() {
        return reparacionRepository.findAll();
    }

    // create a Reparacion getReparacionById
    public Reparacion getReparacionById(Long reparacionId) {
        return reparacionRepository.findById(reparacionId).orElse(null);
    }
}
