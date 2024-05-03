package com.autofix.backend.service;

import com.autofix.backend.dto.CalculoReparacionDTO;
import com.autofix.backend.entities.BonoDcto;
import com.autofix.backend.entities.Vehiculo;
import com.autofix.backend.entities.Reparacion;

import com.autofix.backend.repositories.BonoDctoRepository;
import com.autofix.backend.repositories.ReparacionRepository;
import com.autofix.backend.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class CalculadoraService {
    VehiculoRepository vehiculoRepository;
    ReparacionRepository reparacionRepository;
    BonoDctoRepository bonoDctoRepository;

    @Autowired
    public CalculadoraService(VehiculoRepository vehiculoRepository,
                              ReparacionRepository reparacionRepository,
                              BonoDctoRepository bonoDctoRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.reparacionRepository = reparacionRepository;
        this.bonoDctoRepository = bonoDctoRepository;
    }

    // BONOS AUX METHODS

    public void actualizarBonoDcto(Long bonoDctoId) {
        BonoDcto bonoDcto = bonoDctoRepository.findById(bonoDctoId).orElse(null);
        if (bonoDcto == null) {
            throw new IllegalArgumentException("BonoDcto no encontrado");
        }
        int numBonos = bonoDcto.getNum_bonos() - 1;
        if (numBonos == 0) {
            bonoDctoRepository.delete(bonoDcto);
        } else {
            bonoDcto.setNum_bonos(numBonos);
            bonoDctoRepository.save(bonoDcto);
        }
    }

    public boolean validateIfBonoAppliesToVehiculo(Long bonoDctoId, Long vehiculoId) {
        BonoDcto bonoDcto = bonoDctoRepository.findById(bonoDctoId).orElse(null);
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId).orElse(null);
        if (bonoDcto == null) {
            throw new IllegalArgumentException("BonoDcto no encontrado");
        }
        if (vehiculo == null) {
            throw new IllegalArgumentException("Vehiculo no encontrado");
        }
        return  bonoDcto.getMarca().equals(vehiculo.getMarca());


    }



    // OBTENER BONOS
    public BigDecimal descuentoPorBonoDescuento(Reparacion reparacion, Vehiculo vehiculo) {

        BonoDcto bonoDtoToAply = reparacionRepository.getBonoDctoByReparacionId(reparacion.getId_reparacion());
        if (bonoDtoToAply == null) {
            return BigDecimal.ZERO;
        }
        if (!validateIfBonoAppliesToVehiculo(bonoDtoToAply.getId_bono(), vehiculo.getId())) {
            return BigDecimal.ZERO;
        }
        actualizarBonoDcto(bonoDtoToAply.getId_bono());
        return BigDecimal.valueOf(bonoDtoToAply.getMonto());
    }

    // Tabla de precios de reparaciones
    private final int[][] matrixPreciosReparaciones = {
            {120000,120000,180000,220000}, // # Reparacion de id 1
            {130000,130000,190000,230000}, // # Reparacion de id 2
            {350000,450000,700000,800000}, // # Reparacion de id 3
            {150000,150000,200000,250000}, // # Reparacion de id 4
            {100000,120000,450000,0}, // # Reparacion de id 5
            {100000,120000,450000,0}, // # Reparacion de id 6
            {100000,100000,100000,100000}, // # Reparacion de id 7
            {180000,180000,210000,250000}, // # Reparacion de id 8
            {150000,150000,180000,180000}, // # Reparacion de id 9
            {130000,140000,220000,0}, // # Reparacion de id 10
            {80000,80000,80000,80000} // # Reparacion de id 11
    };

    // Método auxiliar para obtener el descuento de la matriz de descuentos
    int getPrecioReparacion(int id_reparacion, String tipoMotor) {
        int columna = switch (tipoMotor) {
            case "Gasolina" -> 0;
            case "Diesel" -> 1;
            case "Hibrido" -> 2;
            case "Electrico" -> 3;
            default -> throw new IllegalArgumentException("Tipo de motor no válido: " + tipoMotor);
        };
        return matrixPreciosReparaciones[id_reparacion-1][columna];
    }



    // ------------------------
    // Descuentos por numero de Reparaciones
    // ------------------------

    // Definir los porcentajes de descuento del dctoNumeroReparaciones
    BigDecimal[][] descuentosNumeroReparaciones = {
            {BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.07), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.08)}, // 1 - 2 Reparaciones
            {BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.12), BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.13)}, // 3 - 5 Reparaciones
            {BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.17), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.18)}, // 6 - 9 Reparaciones
            {BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.22), BigDecimal.valueOf(0.25), BigDecimal.valueOf(0.23)} // 10 - mas Reparaciones
    };

    // Método auxiliar para obtener el descuento de la matriz de descuentos
    BigDecimal getDescuentoNumeroReparaciones(int fila, String tipoMotor) {
        int columna = switch (tipoMotor) {
            case "Gasolina" -> 0;
            case "Diesel" -> 1;
            case "Hibrido" -> 2;
            case "Electrico" -> 3;
            default -> throw new IllegalArgumentException("Tipo de motor no válido: " + tipoMotor);
        };
        return descuentosNumeroReparaciones[fila][columna];
    }

    // Metodo para obtener el descuento por el numero de reparaciones
    public BigDecimal dctoNumeroReparaciones(Vehiculo vehiculo) {
        int numeroReparaciones = reparacionRepository.getNumeroReparacionesByVehiculoId(vehiculo.getId());
        String tipoMotor = vehiculo.getTipoMotor();
        BigDecimal porcentajeDescuento = BigDecimal.ZERO;

        if (numeroReparaciones >= 10) {
            porcentajeDescuento = getDescuentoNumeroReparaciones(3, tipoMotor);
        } else if (numeroReparaciones >= 6) {
            porcentajeDescuento = getDescuentoNumeroReparaciones(2, tipoMotor);
        } else if (numeroReparaciones >= 3) {
            porcentajeDescuento = getDescuentoNumeroReparaciones(1, tipoMotor);
        } else if (numeroReparaciones >= 1) {
            porcentajeDescuento = getDescuentoNumeroReparaciones(0, tipoMotor);
        }
        return porcentajeDescuento; // Retorno el porcentaje de descuento
    }

    // ------------------------
    // Descuentos por dia de Atencion
    // ------------------------

    public BigDecimal dctoPorDiaAtencion(Reparacion reparacion) {
        LocalDate fechaReparacion = reparacion.getFechaIngreso();
        LocalTime horaReparacion = reparacion.getHoraIngreso();

        DayOfWeek diaSemana = fechaReparacion.getDayOfWeek();
        boolean esLunesOJueves = diaSemana == DayOfWeek.MONDAY || diaSemana == DayOfWeek.THURSDAY;
        boolean esHoraValida = horaReparacion.isAfter(LocalTime.of(9, 0)) && horaReparacion.isBefore(LocalTime.of(12, 0));

        // Aplicar el descuento del 10% si se cumplen las condiciones
        if (esLunesOJueves && esHoraValida) {
            return reparacion.getMontoTotal().multiply(BigDecimal.valueOf(0.10)); // 10% de descuento
        } else {
            return BigDecimal.ZERO; // No hay descuento
        }

    }


    // ------------------------
    // Recargo por Kilometraje
    // ------------------------

    // Definir los porcentajes de descuento del dctoNumeroReparaciones
    private final BigDecimal[][] matrixRecargoKilometrajeVehiculo = {
            {BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0)}, // 0 - 5.000 kilometraje
            {BigDecimal.valueOf(0.03), BigDecimal.valueOf(0.03), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.05)}, // 5.001 - 12.000
            {BigDecimal.valueOf(0.07), BigDecimal.valueOf(0.07), BigDecimal.valueOf(0.09), BigDecimal.valueOf(0.09), BigDecimal.valueOf(0.09)}, // 12.001 - 25.000
            {BigDecimal.valueOf(0.12), BigDecimal.valueOf(0.12), BigDecimal.valueOf(0.12), BigDecimal.valueOf(0.12), BigDecimal.valueOf(0.12)}, // 25.001 - 40.000
            {BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.20)} // 40.000 y mas
    };

    // Método auxiliar para obtener el descuento de la matriz de descuentos
    private BigDecimal getRecargoKilometrajeVehiculo(int fila, String tipoVehiculo) {
        int columna = switch (tipoVehiculo) {
            case "Sedan" -> 0;
            case "Hatchback" -> 1;
            case "SUV" -> 2;
            case "Pickup" -> 3;
            case "Furgoneta" -> 4;
            default -> throw new IllegalArgumentException("Tipo de vehiculo no válido: " + tipoVehiculo);
        };
        return matrixRecargoKilometrajeVehiculo[fila][columna];
    }

    // Método para obtener el recargo por kilometraje y tipo de vehículo
    public BigDecimal recargoKilometrajeVehiculo(int kilometraje, String tipoVehiculo) {
        int fila;
        if (kilometraje <= 5000) {
            fila = 0;
        } else if (kilometraje <= 12000) {
            fila = 1;
        } else if (kilometraje <= 25000) {
            fila = 2;
        } else if (kilometraje <= 40000) {
            fila = 3;
        } else {
            fila = 4;
        }
        // Obtener el recargo correspondiente de la matriz
        return getRecargoKilometrajeVehiculo(fila, tipoVehiculo);
    }


    // ------------------------
    // Recargo por Antiguedad del Vehiculo
    // ------------------------

    private final BigDecimal[][] matrixRecargoAntiguedadVehiculo = {
            {BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0)},
            {BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.07), BigDecimal.valueOf(0.07), BigDecimal.valueOf(0.07)},
            {BigDecimal.valueOf(0.09), BigDecimal.valueOf(0.09), BigDecimal.valueOf(0.11), BigDecimal.valueOf(0.11), BigDecimal.valueOf(0.11)},
            {BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.15), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.20)},
    };

    // Método auxiliar para obtener el descuento de la matriz de descuentos
    private BigDecimal getRecargoAntiguedadVehiculo(int fila, String tipoVehiculo) {
        int columna = switch (tipoVehiculo) {
            case "Sedan" -> 0;
            case "Hatchback" -> 1;
            case "SUV" -> 2;
            case "Pickup" -> 3;
            case "Furgoneta" -> 4;
            default -> throw new IllegalArgumentException("Tipo de vehiculo no válido: " + tipoVehiculo);
        };
        return matrixRecargoAntiguedadVehiculo[fila][columna];
    }

    // Método para obtener el recargo por antigüedad del vehículo
    public BigDecimal recargoPorAntiguedad(int antiguedad, String tipoVehiculo) {
        // Determinar el rango de antigüedad
        int indiceAntiguedad;
        if (antiguedad <= 5) {
            indiceAntiguedad = 0;
        } else if (antiguedad <= 10) {
            indiceAntiguedad = 1;
        } else if (antiguedad <= 15) {
            indiceAntiguedad = 2;
        } else {
            indiceAntiguedad = 3;
        }

        // Obtener el recargo correspondiente de la matriz
        return getRecargoAntiguedadVehiculo(indiceAntiguedad, tipoVehiculo);
    }


    // ------------------------
    // Recargo por Retraso en la Recogida
    // ------------------------

    // Método para obtener el recargo por retraso en la recogida del vehículo
    public BigDecimal recargoRetrasoRecogidaVehiculo(LocalDate fechaEntregaPrevista, LocalDate fechaRecogidaReal, BigDecimal montoTotalReparacion) {
        // Calcular la diferencia en días entre las fechas
        long diasRetraso = ChronoUnit.DAYS.between(fechaEntregaPrevista, fechaRecogidaReal);
        // Aplicar un recargo del 5% por cada día de retraso en la recogida del vehículo
        BigDecimal recargo = BigDecimal.ZERO;
        if (diasRetraso > 0) {
            recargo = montoTotalReparacion.multiply(BigDecimal.valueOf(0.05)).multiply(BigDecimal.valueOf(diasRetraso));
        }
        return recargo;
    }

    public BigDecimal recargoIVA(BigDecimal montoReparacion) {
        return montoReparacion.multiply(BigDecimal.valueOf(0.19));
    }

    // create a method to calculate the total amount of the repair
    public CalculoReparacionDTO calcularReparacion(Reparacion reparacion) {
        Vehiculo vehiculo = vehiculoRepository.findById(reparacion.getIdVehiculo()).orElse(null);
        if (vehiculo == null) {
            throw new IllegalArgumentException("Vehiculo no encontrado");
        }

        BigDecimal dctoNumeroReparaciones = dctoNumeroReparaciones(vehiculo);
        System.out.println("Descuento por numero de reparaciones: %" + dctoNumeroReparaciones);


        BigDecimal recargoPorAntiguedad = recargoPorAntiguedad(vehiculo.getYearFabricacion(), vehiculo.getTipo());
        System.out.println("Recargo por antiguedad: %" + recargoPorAntiguedad);


        BigDecimal dctoPorDiaAtencion = dctoPorDiaAtencion(reparacion);
        System.out.println("Descuento por dia de atencion: $" + dctoPorDiaAtencion);


        BigDecimal recargoKilometrajeVehiculo = recargoKilometrajeVehiculo(vehiculo.getKilometraje(), vehiculo.getTipo());
        System.out.println("Recargo por kilometraje del vehiculo: %" + recargoKilometrajeVehiculo);

        BigDecimal montoReparacion = BigDecimal.valueOf(getPrecioReparacion(Integer.parseInt(reparacion.getTipoReparacion()), vehiculo.getTipoMotor()));
        System.out.println("Monto de la reparacion: $" + montoReparacion);

        BigDecimal recargoIVAaux =  recargoIVA(montoReparacion);
        System.out.println("Monto IVA: $" + montoReparacion);


        // campo descuento por bono del calculo dto
        BigDecimal descuentoPorBono;
        if (reparacion.isAplicaBonoDescuento()) {
            descuentoPorBono = descuentoPorBonoDescuento(reparacion, vehiculo);
            System.out.println("Descuento por bono: $" + descuentoPorBono);
        }else {
            descuentoPorBono = BigDecimal.ZERO;
        }
        System.out.println("Descuento por bono: $" + descuentoPorBono);


        BigDecimal recargoPorRetrasoRecogida = recargoRetrasoRecogidaVehiculo(reparacion.getFechaEntregaCliente(), LocalDate.now(), montoReparacion);
        System.out.println("Recargo por retraso en la recogida: $" + recargoPorRetrasoRecogida);


        BigDecimal sumaRecargosPorcentajeTotales = recargoPorAntiguedad.add(recargoKilometrajeVehiculo);
        BigDecimal sumaDescuentosPorcentajeTotales = dctoNumeroReparaciones;



        System.out.println("Suma recargos totales: %" + sumaRecargosPorcentajeTotales);

         // No se esta contemplando el descuento del bono, TODO: Agregar el descuento del bono
        System.out.println("Suma descuentos totales: % " + sumaDescuentosPorcentajeTotales);

        BigDecimal montoFinalCobro = montoReparacion.add((montoReparacion.multiply(sumaRecargosPorcentajeTotales)).add(recargoPorRetrasoRecogida)).subtract(montoReparacion.multiply(sumaDescuentosPorcentajeTotales).add(dctoPorDiaAtencion).add(descuentoPorBono)).add(recargoIVAaux);
        System.out.println("Monto final de cobro: $" + montoFinalCobro);

        // now edit the actual reparacion param to set the new value of montofinalcobro has the montoTotal attribute
        reparacion.setMontoTotal(montoFinalCobro);

        // TO DO: ADAPTAR EL RETURN CON IVA Y DCTOBONO
        return new CalculoReparacionDTO(dctoNumeroReparaciones, recargoPorAntiguedad, recargoPorRetrasoRecogida, dctoPorDiaAtencion, recargoKilometrajeVehiculo, montoReparacion, montoFinalCobro, reparacion.getId_reparacion(), descuentoPorBono, recargoIVAaux);
    }







}
