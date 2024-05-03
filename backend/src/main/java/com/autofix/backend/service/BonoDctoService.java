package com.autofix.backend.service;

import com.autofix.backend.entities.BonoDcto;
import com.autofix.backend.repositories.BonoDctoRepository;
import com.autofix.backend.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonoDctoService {
    BonoDctoRepository bonoDctoRepository;

    @Autowired
    public BonoDctoService(BonoDctoRepository bonoDctoRepository){ this.bonoDctoRepository = bonoDctoRepository; }

    public BonoDcto registrarBonoDcto(BonoDcto bonoDcto){
        return bonoDctoRepository.save(bonoDcto);
    }

    public List<BonoDcto> obtenerTodosLosBonosDcto() {
        return bonoDctoRepository.findAll();
    }

    public BonoDcto getBonoDctoById(Long bonoDctoId) {
        return bonoDctoRepository.findById(bonoDctoId).orElse(null);
    }






}
