package com.autofix.backend.repositories;

import com.autofix.backend.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
