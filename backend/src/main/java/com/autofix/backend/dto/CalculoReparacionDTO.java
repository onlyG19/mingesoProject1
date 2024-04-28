package com.autofix.backend.dto;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalculoReparacionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalculoReparacion;

    private BigDecimal dctoNumeroReparaciones; // Porcentaje de Descuento
    private BigDecimal recargoPorAntiguedad;
    private BigDecimal recargoPorRetrasoRecogida;
    private BigDecimal dctoPorDiaAtencion; // Se aplica cuando el monto Total esta fijado
    private BigDecimal recargoKilometrajeVehiculo; // Sobre el costo total, depende del kilometraje
    private BigDecimal montoReparaciones; // Sumatoria de los montos de las reparaciones
    private BigDecimal montoTotalFinal; // Monto  [Suma(Reparaciones) + Recargos – Descuentos] + IVA

    @Column(name = "id_reparacion")
    private Long id_reparacion;

    public CalculoReparacionDTO(BigDecimal dctoNumeroReparaciones, BigDecimal recargoPorAntiguedad, BigDecimal recargoPorRetrasoRecogida, BigDecimal dctoPorDiaAtencion, BigDecimal recargoKilometrajeVehiculo, BigDecimal montoReparacion, BigDecimal montoFinalCobro, Long id_reparacion) {
        this.dctoNumeroReparaciones = dctoNumeroReparaciones;
        this.recargoPorAntiguedad = recargoPorAntiguedad;
        this.recargoPorRetrasoRecogida = recargoPorRetrasoRecogida;
        this.dctoPorDiaAtencion = dctoPorDiaAtencion;
        this.recargoKilometrajeVehiculo = recargoKilometrajeVehiculo;
        this.montoReparaciones = montoReparacion;
        this.montoTotalFinal = montoFinalCobro;
        this.id_reparacion = id_reparacion;
    }
}