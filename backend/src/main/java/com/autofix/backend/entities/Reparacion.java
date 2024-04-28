package com.autofix.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reparacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reparacion;
    private LocalDate fechaIngreso;
    private LocalTime horaIngreso;
    private String tipoReparacion;
    private BigDecimal montoTotal;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    private LocalDate fechaEntregaCliente;
    private LocalTime horaEntregaCliente;

    @Column(name = "id_vehiculo")
    private Long idVehiculo;
}
