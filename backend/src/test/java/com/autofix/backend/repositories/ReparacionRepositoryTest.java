package com.autofix.backend.repositories;

import com.autofix.backend.entities.BonoDcto;
import com.autofix.backend.entities.Reparacion;
import com.autofix.backend.repositories.ReparacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReparacionRepositoryTest {

    @Mock
    private ReparacionRepository reparacionRepository;

    @Mock
    private Reparacion reparacion;

    @Mock
    private BonoDcto bonoDcto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getNumeroReparacionesByVehiculoIdReturnsExpectedCount() {
        when(reparacionRepository.getNumeroReparacionesByVehiculoId(anyLong())).thenReturn(5);
        int count = reparacionRepository.getNumeroReparacionesByVehiculoId(1L);
        assertEquals(5, count);
    }

    @Test
    public void getBonoDctoByReparacionIdReturnsExpectedBonoDcto() {
        when(reparacionRepository.getBonoDctoByReparacionId(anyLong())).thenReturn(bonoDcto);
        BonoDcto returnedBonoDcto = reparacionRepository.getBonoDctoByReparacionId(1L);
        assertEquals(bonoDcto, returnedBonoDcto);
    }
}