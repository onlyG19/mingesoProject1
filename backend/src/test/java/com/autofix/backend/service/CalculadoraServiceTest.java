package com.autofix.backend.service;

import com.autofix.backend.entities.Reparacion;
import com.autofix.backend.entities.Vehiculo;
import com.autofix.backend.repositories.ReparacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculadoraServiceTest {

    @Mock
    private ReparacionRepository reparacionRepository;

    @InjectMocks
    private CalculadoraService calculadoraService;



    @Test
    public void testRecargoPorAntiguedad() {
        // Arrange
        int antiguedad = 10;
        String tipoVehiculo = "Sedan";
        BigDecimal expectedRecargo = BigDecimal.valueOf(0.05);

        // Act
        BigDecimal actualRecargo = calculadoraService.recargoPorAntiguedad(antiguedad, tipoVehiculo);

        // Assert
        assertEquals(expectedRecargo, actualRecargo);
    }

    @Test
    public void testRecargoPorRetrasoRecogida() {
        // Arrange
        LocalDate fechaEntregaPrevista = LocalDate.of(2022, 10, 1);
        LocalDate fechaRecogidaReal = LocalDate.of(2022, 10, 6);
        BigDecimal montoTotalReparacion = BigDecimal.valueOf(1000);
        String montoExpected = "250.00";

        // Act
        BigDecimal actualRecargo = calculadoraService.recargoRetrasoRecogidaVehiculo(fechaEntregaPrevista, fechaRecogidaReal, montoTotalReparacion);

        // Assert
        assertEquals(montoExpected, actualRecargo.toString());
    }

    @Test
    public void testDctoNumeroReparacionesEs0() {
        // Test case en que el vehiculo tiene 0 reparaciones
        // Arrange
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipoMotor("Gasolina");

        BigDecimal expectedDescuento = BigDecimal.valueOf(0);

        // Act
        BigDecimal actualDescuento = calculadoraService.dctoNumeroReparaciones(vehiculo);

        // Assert
        assertEquals(expectedDescuento, actualDescuento);
    }

    @Test
    public void testDctoNumeroReparacionesEs5() {
        // Test case en que el vehiculo tiene 5 reparaciones
        // Arrange
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipoMotor("Gasolina");

        when(reparacionRepository.getNumeroReparacionesByVehiculoId(vehiculo.getId())).thenReturn(5);

        BigDecimal expectedDescuento = BigDecimal.valueOf(0.1);

        // Act
        BigDecimal actualDescuento = calculadoraService.dctoNumeroReparaciones(vehiculo);

        // Assert
        assertEquals(expectedDescuento, actualDescuento);
    }


    @Test
    public void testDctoPorDiaAtencionSiAplicaDcto() {
        // creaye a reparacion object
        Reparacion reparacion = new Reparacion();
        reparacion.setFechaIngreso(LocalDate.of(2022, 10, 3));
        reparacion.setHoraIngreso(LocalTime.of(10, 0));
        reparacion.setMontoTotal(BigDecimal.valueOf(1000));

        BigDecimal expectedDescuento = BigDecimal.valueOf(100.0);

        // Act
        BigDecimal actualDescuento = calculadoraService.dctoPorDiaAtencion(reparacion);

        // Assert
        assertEquals(expectedDescuento, actualDescuento);
    }

    @Test
    public void testDctoPorDiaAtencionNoAplicaDcto() {
        // creaye a reparacion object
        Reparacion reparacion = new Reparacion();
        reparacion.setFechaIngreso(LocalDate.of(2022, 10, 1));
        reparacion.setHoraIngreso(LocalTime.of(10, 0));
        reparacion.setMontoTotal(BigDecimal.valueOf(1000));

        BigDecimal expectedDescuento = BigDecimal.ZERO;

        // Act
        BigDecimal actualDescuento = calculadoraService.dctoPorDiaAtencion(reparacion);

        // Assert
        assertEquals(expectedDescuento, actualDescuento);
    }


    @Test
    // make a method to recargoKilometrajeVehiculo
    public void testRecargoKilometrajeVehiculo() {
        // Arrange
        int kilometraje = 100000;
        String tipoVehiculo = "Sedan";
        BigDecimal expectedRecargo = BigDecimal.valueOf(0.2);

        // Act
        BigDecimal actualRecargo = calculadoraService.recargoKilometrajeVehiculo(kilometraje, tipoVehiculo);

        // Assert
        assertEquals(expectedRecargo, actualRecargo);
    }
}