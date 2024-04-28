package com.autofix.backend.service;

import com.autofix.backend.dto.CalculoReparacionDTO;
import com.autofix.backend.entities.Reparacion;
import com.autofix.backend.entities.Vehiculo;
import com.autofix.backend.repositories.ReparacionRepository;
import com.autofix.backend.repositories.VehiculoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculadoraServiceTest {

    @Mock
    private ReparacionRepository reparacionRepository;

    @Mock
    private VehiculoRepository vehiculoRepository;

    @InjectMocks
    private CalculadoraService calculadoraService;

    private Vehiculo vehiculo;
    private Reparacion reparacion;

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


    // make a test for the method getPrecioReparacion
    @Test
    public void testGetPrecioReparacion() {
        // Arrange
        int id_codigoTipoReparacion = 1;
        int id_codigoTipoReparacion2 = 9;
        String tipoMotor = "Gasolina";
        String tipoMotor2 = "Diesel";
        int expectedPrecio_TipoReparacion1 = 120000;
        int expectedPrecio_TipoReparacion2 = 150000;


        int actualPrecio1 = calculadoraService.getPrecioReparacion(id_codigoTipoReparacion,tipoMotor);
        int actualPrecio2 = calculadoraService.getPrecioReparacion(id_codigoTipoReparacion2,tipoMotor2);

        // Assert
        assertEquals(expectedPrecio_TipoReparacion1, actualPrecio1);
        assertEquals(expectedPrecio_TipoReparacion2, actualPrecio2);

    }

// make a test for the method calcularReparacion(Reparacion reparacion)
    @Test
    public void calcularReparacionShouldCalculateCorrectly() {
        vehiculo = new Vehiculo();
        vehiculo.setId(1L);
        vehiculo.setYearFabricacion(10);
        vehiculo.setTipo("Sedan");
        vehiculo.setKilometraje(100000);
        vehiculo.setTipoMotor("Gasolina");

        reparacion = new Reparacion();
        reparacion.setId_reparacion(1L);
        reparacion.setFechaIngreso(LocalDate.of(2022, 10, 3));
        reparacion.setHoraIngreso(LocalTime.of(10, 0));
        reparacion.setMontoTotal(BigDecimal.valueOf(1000));
        reparacion.setFechaSalida(LocalDate.of(2022, 10, 6));
        reparacion.setHoraSalida(LocalTime.of(10, 0));
        reparacion.setFechaEntregaCliente(LocalDate.of(2022, 10, 6));
        reparacion.setHoraEntregaCliente(LocalTime.of(10, 0));
        reparacion.setIdVehiculo(1L);
        reparacion.setTipoReparacion("1");

        when(vehiculoRepository.findById(vehiculo.getId())).thenReturn(java.util.Optional.of(vehiculo));

        CalculoReparacionDTO expectedResultsCalculadora = calculadoraService.calcularReparacion(reparacion);

        BigDecimal expectedMontoReparacion = BigDecimal.valueOf(120000);
        BigDecimal expectedDctoNumeroReparaciones = BigDecimal.ZERO;
        BigDecimal expectedRecargoPorAntiguedad = BigDecimal.valueOf(0.05);
        String expectedRecargoPorRetrasoRecogida = "28500.00";
        BigDecimal expectedDctoPorDiaAtencion = BigDecimal.valueOf(100.0);
        BigDecimal expectedRecargoKilometrajeVehiculo = BigDecimal.valueOf(0.2);

        String expectedMontoFinalReparaciones = "178400.00";

        assertEquals(expectedMontoFinalReparaciones, reparacion.getMontoTotal().toString());
         assertEquals(expectedDctoNumeroReparaciones, expectedResultsCalculadora.getDctoNumeroReparaciones());
        assertEquals(expectedRecargoPorAntiguedad, expectedResultsCalculadora.getRecargoPorAntiguedad());
        assertEquals(expectedRecargoPorRetrasoRecogida, expectedResultsCalculadora.getRecargoPorRetrasoRecogida().toString());
        assertEquals(expectedDctoPorDiaAtencion, expectedResultsCalculadora.getDctoPorDiaAtencion());
        assertEquals(expectedRecargoKilometrajeVehiculo, expectedResultsCalculadora.getRecargoKilometrajeVehiculo());
        assertEquals(expectedMontoReparacion, expectedResultsCalculadora.getMontoReparaciones());




    }

    @Test
    public void calcularReparacionShouldThrowExceptionWhenVehiculoNotFound() {
        when(vehiculoRepository.findById(vehiculo.getId())).thenReturn(java.util.Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> calculadoraService.calcularReparacion(reparacion));
    }
}