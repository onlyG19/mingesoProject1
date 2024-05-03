package com.autofix.backend.repositories;

import com.autofix.backend.dto.CalculoReparacionDTO;
import com.autofix.backend.repositories.CalculoReparacionDTORepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculoReparacionDTORepositoryTest {

    @Mock
    private CalculoReparacionDTORepository calculoReparacionDTORepository;

    @Mock
    private CalculoReparacionDTO calculoReparacionDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdReturnsExpectedCalculoReparacionDTO() {
        when(calculoReparacionDTORepository.findById(1L)).thenReturn(Optional.of(calculoReparacionDTO));
        Optional<CalculoReparacionDTO> returnedCalculoReparacionDTO = calculoReparacionDTORepository.findById(1L);
        assertEquals(Optional.of(calculoReparacionDTO), returnedCalculoReparacionDTO);
    }

    @Test
    public void findByIdReturnsEmptyWhenNoCalculoReparacionDTO() {
        when(calculoReparacionDTORepository.findById(1L)).thenReturn(Optional.empty());
        Optional<CalculoReparacionDTO> returnedCalculoReparacionDTO = calculoReparacionDTORepository.findById(1L);
        assertEquals(Optional.empty(), returnedCalculoReparacionDTO);
    }
}
