package com.autofix.backend.controller;

import com.autofix.backend.entities.Vehiculo;
import com.autofix.backend.service.VehiculoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehiculoControllerTest {

    @Mock
    private VehiculoService vehiculoService;

    @InjectMocks
    private VehiculoController vehiculoController;

    @Test
    public void obtenerTodosLosVehiculosShouldReturnVehiculoList() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1L);
        when(vehiculoService.obtenerTodosLosVehiculos()).thenReturn(Collections.singletonList(vehiculo));

        List<Vehiculo> response = vehiculoController.obtenerTodosLosVehiculos();

        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).getId());
    }

    @Test
    public void registrarVehiculoShouldReturnVehiculo() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1L);
        when(vehiculoService.registrarVehiculo(vehiculo)).thenReturn(vehiculo);

        Vehiculo response = vehiculoController.registrarVehiculo(vehiculo);

        assertEquals(1L, response.getId());
    }
}