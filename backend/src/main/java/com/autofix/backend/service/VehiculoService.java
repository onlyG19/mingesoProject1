package com.autofix.backend.service;

import com.autofix.backend.entities.Vehiculo;
import com.autofix.backend.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {
    VehiculoRepository vehiculoRepository;


    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository){ this.vehiculoRepository = vehiculoRepository; }

    public Vehiculo registrarVehiculo(Vehiculo vehiculo){
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo getVehiculoById(Long vehiculoId) {
        return vehiculoRepository.findById(vehiculoId).orElse(null);
    }
}
