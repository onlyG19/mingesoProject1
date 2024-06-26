package com.autofix.backend.controller;

import com.autofix.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
@CrossOrigin("*")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/1")
    public List<Map<String, Object>> getReport1Data() {
        return reportService.getReport1Data();
    }

    @GetMapping("/2")
    public List<Map<String, Object>> getReport2Data() {
        return reportService.getReport2Data();
    }

    @GetMapping("/3")
    public List<Map<String, Object>> getReport3Data() {
        return reportService.getReport3Data();
    }

    @GetMapping("/4")
    public List<Map<String, Object>> getReport4Data() {
        return reportService.getReport4Data();
    }
}