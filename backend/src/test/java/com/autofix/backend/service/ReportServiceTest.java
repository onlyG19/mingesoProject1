package com.autofix.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ReportServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private ReportService reportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        reportService = new ReportService(jdbcTemplate);
    }

    @Test
    public void getReport1DataReturnsExpectedData() {
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("id_vehiculo", 1);
        reportData.put("marca", "Toyota");
        // add other fields as per your requirements
        List<Map<String, Object>> expectedData = Arrays.asList(reportData);
        when(jdbcTemplate.queryForList(anyString())).thenReturn(expectedData);
        List<Map<String, Object>> actualData = reportService.getReport1Data();
        assertEquals(expectedData, actualData);
    }

    @Test
    public void getReport2DataReturnsExpectedData() {
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("tipo_reparacion", "Sedan");
        reportData.put("Sedan", 1);
        // add other fields as per your requirements
        List<Map<String, Object>> expectedData = Arrays.asList(reportData);
        when(jdbcTemplate.queryForList(anyString())).thenReturn(expectedData);
        List<Map<String, Object>> actualData = reportService.getReport2Data();
        assertEquals(expectedData, actualData);
    }

    @Test
    public void getReport3DataReturnsExpectedData() {
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("marca", "Toyota");
        reportData.put("tiempo_promedio_reparacion", 1);
        // add other fields as per your requirements
        List<Map<String, Object>> expectedData = Arrays.asList(reportData);
        when(jdbcTemplate.queryForList(anyString())).thenReturn(expectedData);
        List<Map<String, Object>> actualData = reportService.getReport3Data();
        assertEquals(expectedData, actualData);
    }

    @Test
    public void getReport4DataReturnsExpectedData() {
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("tipo_reparacion", "Sedan");
        reportData.put("Gasolina", 1);
        // add other fields as per your requirements
        List<Map<String, Object>> expectedData = Arrays.asList(reportData);
        when(jdbcTemplate.queryForList(anyString())).thenReturn(expectedData);
        List<Map<String, Object>> actualData = reportService.getReport4Data();
        assertEquals(expectedData, actualData);
    }
}