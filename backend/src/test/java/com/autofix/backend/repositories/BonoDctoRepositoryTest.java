package com.autofix.backend.repositories;

import com.autofix.backend.entities.BonoDcto;
import com.autofix.backend.repositories.BonoDctoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BonoDctoRepositoryTest {

    @Mock
    private BonoDctoRepository bonoDctoRepository;

    @Mock
    private BonoDcto bonoDcto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdReturnsExpectedBonoDcto() {
        when(bonoDctoRepository.findById(1L)).thenReturn(Optional.of(bonoDcto));
        Optional<BonoDcto> returnedBonoDcto = bonoDctoRepository.findById(1L);
        assertEquals(Optional.of(bonoDcto), returnedBonoDcto);
    }

    @Test
    public void findByIdReturnsEmptyWhenNoBonoDcto() {
        when(bonoDctoRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<BonoDcto> returnedBonoDcto = bonoDctoRepository.findById(1L);
        assertEquals(Optional.empty(), returnedBonoDcto);
    }
}