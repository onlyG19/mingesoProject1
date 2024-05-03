package com.autofix.backend.repositories;

import com.autofix.backend.entities.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VehiculoRepositoryTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private Vehiculo vehiculo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdReturnsExpectedVehiculo() {
        when(vehiculoRepository.findById(1L)).thenReturn(Optional.of(vehiculo));
        Optional<Vehiculo> returnedVehiculo = vehiculoRepository.findById(1L);
        assertEquals(Optional.of(vehiculo), returnedVehiculo);
    }

    @Test
    public void findByIdReturnsEmptyWhenNoVehiculo() {
        when(vehiculoRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Vehiculo> returnedVehiculo = vehiculoRepository.findById(1L);
        assertEquals(Optional.empty(), returnedVehiculo);
    }
}