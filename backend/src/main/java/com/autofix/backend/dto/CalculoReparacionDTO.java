package com.autofix.backend.dto;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Transactional
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalculoReparacionDTO {
    private BigDecimal dctoNumeroReparaciones; // Porcentaje de Descuento

    private BigDecimal recargoPorAntiguedad;
    private BigDecimal recargoPorRetrasoRecogida;
    private BigDecimal dctoPorDiaAtencion; // Se aplica cuando el monto Total esta fijado
    private BigDecimal recargoKilometrajeVehiculo; // Sobre el costo total, depende del kilometraje
    private BigDecimal montoReparaciones; // Sumatoria de los montos de las reparaciones
    private BigDecimal montoTotalFinal; // Monto  [Suma(Reparaciones) + Recargos – Descuentos] + IVA

}