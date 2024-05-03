package com.autofix.backend.service;

import com.autofix.backend.entities.BonoDcto;
import com.autofix.backend.repositories.BonoDctoRepository;
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

public class BonoDctoServiceTest {

    @Mock
    private BonoDctoRepository bonoDctoRepository;

    private BonoDctoService bonoDctoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        bonoDctoService = new BonoDctoService(bonoDctoRepository);
    }

    @Test
    public void registrarBonoDctoReturnsSavedBonoDcto() {
        BonoDcto bonoDcto = new BonoDcto();
        when(bonoDctoRepository.save(bonoDcto)).thenReturn(bonoDcto);
        BonoDcto returnedBonoDcto = bonoDctoService.registrarBonoDcto(bonoDcto);
        assertEquals(bonoDcto, returnedBonoDcto);
    }

    @Test
    public void obtenerTodosLosBonosDctoReturnsAllBonosDcto() {
        BonoDcto bonoDcto1 = new BonoDcto();
        BonoDcto bonoDcto2 = new BonoDcto();
        List<BonoDcto> bonoDctos = Arrays.asList(bonoDcto1, bonoDcto2);
        when(bonoDctoRepository.findAll()).thenReturn(bonoDctos);
        List<BonoDcto> returnedBonoDctos = bonoDctoService.obtenerTodosLosBonosDcto();
        assertEquals(bonoDctos, returnedBonoDctos);
    }

    @Test
    public void getBonoDctoByIdReturnsBonoDctoWhenExists() {
        BonoDcto bonoDcto = new BonoDcto();
        when(bonoDctoRepository.findById(1L)).thenReturn(Optional.of(bonoDcto));
        BonoDcto returnedBonoDcto = bonoDctoService.getBonoDctoById(1L);
        assertEquals(bonoDcto, returnedBonoDcto);
    }

    @Test
    public void getBonoDctoByIdReturnsNullWhenNotExists() {
        when(bonoDctoRepository.findById(1L)).thenReturn(Optional.empty());
        BonoDcto returnedBonoDcto = bonoDctoService.getBonoDctoById(1L);
        assertNull(returnedBonoDcto);
    }
}