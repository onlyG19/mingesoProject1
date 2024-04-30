package com.autofix.backend.repositories;

import com.autofix.backend.dto.CalculoReparacionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculoReparacionDTORepository extends JpaRepository<CalculoReparacionDTO, Long> {
}