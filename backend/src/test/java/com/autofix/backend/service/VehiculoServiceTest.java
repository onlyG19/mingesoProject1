package com.autofix.backend.service;

import com.autofix.backend.entities.Vehiculo;
import com.autofix.backend.repositories.VehiculoRepository;
import com.autofix.backend.service.VehiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class VehiculoServiceTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    private VehiculoService vehiculoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        vehiculoService = new VehiculoService(vehiculoRepository);
    }

    @Test
    public void registrarVehiculoReturnsSavedVehiculo() {
        Vehiculo vehiculo = new Vehiculo();
        when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
        Vehiculo returnedVehiculo = vehiculoService.registrarVehiculo(vehiculo);
        assertEquals(vehiculo, returnedVehiculo);
    }

    @Test
    public void obtenerTodosLosVehiculosReturnsAllVehiculos() {
        Vehiculo vehiculo1 = new Vehiculo();
        Vehiculo vehiculo2 = new Vehiculo();
        List<Vehiculo> vehiculos = Arrays.asList(vehiculo1, vehiculo2);
        when(vehiculoRepository.findAll()).thenReturn(vehiculos);
        List<Vehiculo> returnedVehiculos = vehiculoService.obtenerTodosLosVehiculos();
        assertEquals(vehiculos, returnedVehiculos);
    }

    @Test
    public void getVehiculoByIdReturnsVehiculoWhenExists() {
        Vehiculo vehiculo = new Vehiculo();
        when(vehiculoRepository.findById(1L)).thenReturn(Optional.of(vehiculo));
        Vehiculo returnedVehiculo = vehiculoService.getVehiculoById(1L);
        assertEquals(vehiculo, returnedVehiculo);
    }

    @Test
    public void getVehiculoByIdReturnsNullWhenNotExists() {
        when(vehiculoRepository.findById(1L)).thenReturn(Optional.empty());
        Vehiculo returnedVehiculo = vehiculoService.getVehiculoById(1L);
        assertNull(returnedVehiculo);
    }
}