package com.autofix.backend.controller;

import com.autofix.backend.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    private Map<String, Object> reportData;

    @BeforeEach
    public void setUp() {
        reportData = new HashMap<>();
        reportData.put("key", "value");
    }

    @Test
    public void getReport1DataReturnsExpectedData() {
        when(reportService.getReport1Data()).thenReturn(Collections.singletonList(reportData));

        List<Map<String, Object>> response = reportController.getReport1Data();

        assertEquals(1, response.size());
        assertEquals("value", response.get(0).get("key"));
    }

    @Test
    public void getReport2DataReturnsExpectedData() {
        when(reportService.getReport2Data()).thenReturn(Collections.singletonList(reportData));

        List<Map<String, Object>> response = reportController.getReport2Data();

        assertEquals(1, response.size());
        assertEquals("value", response.get(0).get("key"));
    }

    @Test
    public void getReport3DataReturnsExpectedData() {
        when(reportService.getReport3Data()).thenReturn(Collections.singletonList(reportData));

        List<Map<String, Object>> response = reportController.getReport3Data();

        assertEquals(1, response.size());
        assertEquals("value", response.get(0).get("key"));
    }

    @Test
    public void getReport4DataReturnsExpectedData() {
        when(reportService.getReport4Data()).thenReturn(Collections.singletonList(reportData));

        List<Map<String, Object>> response = reportController.getReport4Data();

        assertEquals(1, response.size());
        assertEquals("value", response.get(0).get("key"));
    }
}