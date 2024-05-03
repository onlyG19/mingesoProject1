package com.autofix.backend.repositories;

import com.autofix.backend.entities.BonoDcto;
import com.autofix.backend.entities.Reparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReparacionRepository extends JpaRepository<Reparacion, Long> {
    @Query(value = "SELECT COUNT(*) FROM reparacion WHERE id_vehiculo = :vehiculoId", nativeQuery = true)
    int getNumeroReparacionesByVehiculoId(@Param("vehiculoId") Long vehiculoId);

    @Query(value = "SELECT * FROM bono_dcto WHERE id_reparacion = :reparacionId", nativeQuery = true)
    BonoDcto getBonoDctoByReparacionId(Long reparacionId);
}
