package com.autofix.backend.controller;

import com.autofix.backend.entities.Reparacion;
import com.autofix.backend.service.ReparacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReparacionControllerTest {

    @Mock
    private ReparacionService reparacionService;

    @InjectMocks
    private ReparacionController reparacionController;

    @Test
    public void obtenerTodasLasReparacionesShouldReturnReparacionList() {
        Reparacion reparacion = new Reparacion();
        reparacion.setId_reparacion(1L);
        when(reparacionService.obtenerTodasLasReparaciones()).thenReturn(Collections.singletonList(reparacion));

        List<Reparacion> response = reparacionController.obtenerTodasLasReparaciones();

        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).getId_reparacion());
    }

    @Test
    public void registrarReparacionShouldReturnReparacion() {
        Reparacion reparacion = new Reparacion();
        reparacion.setId_reparacion(1L);
        when(reparacionService.registrarReparacion(reparacion)).thenReturn(reparacion);

        Reparacion response = reparacionController.registrarReparacion(reparacion);

        assertEquals(1L, response.getId_reparacion());
    }
}