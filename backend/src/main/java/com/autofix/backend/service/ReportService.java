package com.autofix.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getReport2Data() {
        String sqlQuery = "SELECT " +
                "tipo_reparacion AS tipo_reparacion, " +
                "SUM(CASE WHEN tipo = 'Sedan' THEN 1 ELSE 0 END) AS Sedan, " +
                "SUM(CASE WHEN tipo = 'Hatchback' THEN 1 ELSE 0 END) AS Hatchback, " +
                "SUM(CASE WHEN tipo = 'SUV' THEN 1 ELSE 0 END) AS SUV, " +
                "SUM(CASE WHEN tipo = 'Pickup' THEN 1 ELSE 0 END) AS Pickup, " +
                "SUM(CASE WHEN tipo = 'Furgoneta' THEN 1 ELSE 0 END) AS Furgoneta, " +
                "SUM(monto_total) AS monto_total " +
                "FROM " +
                "reparacion r " +
                "LEFT JOIN " +
                "vehiculo v ON r.id_vehiculo = v.id " +
                "GROUP BY " +
                "tipo_reparacion " +
                "ORDER BY " +
                "MAX(CASE WHEN tipo = 'Sedan' THEN monto_total ELSE 0 END) + " +
                "MAX(CASE WHEN tipo = 'Hatchback' THEN monto_total ELSE 0 END) + " +
                "MAX(CASE WHEN tipo = 'SUV' THEN monto_total ELSE 0 END) + " +
                "MAX(CASE WHEN tipo = 'Pickup' THEN monto_total ELSE 0 END) + " +
                "MAX(CASE WHEN tipo = 'Furgoneta' THEN monto_total ELSE 0 END) DESC, " +
                "monto_total DESC;";
        return jdbcTemplate.queryForList(sqlQuery);

    }

    public List<Map<String, Object>> getReport3Data() {
        String sqlQuery = "SELECT " +
                "v.marca AS marca, " +
                "CAST(AVG(r.fecha_salida - r.fecha_ingreso) AS INTEGER) AS tiempo_promedio_reparacion " +
                "FROM " +
                "reparacion r " +
                "INNER JOIN " +
                "vehiculo v ON r.id_vehiculo = v.id " +
                "GROUP BY " +
                "v.marca " +
                "ORDER BY " +
                "tiempo_promedio_reparacion ASC;";
        return jdbcTemplate.queryForList(sqlQuery);
    }

    public List<Map<String, Object>> getReport4Data() {
        String sqlQuery = "SELECT " +
                "tipo_reparacion AS tipo_reparacion, " +
                "SUM(CASE WHEN tipo_motor = 'Gasolina' THEN 1 ELSE 0 END) AS Gasolina, " +
                "SUM(CASE WHEN tipo_motor = 'Diesel' THEN 1 ELSE 0 END) AS Diesel, " +
                "SUM(CASE WHEN tipo_motor = 'Hibrido' THEN 1 ELSE 0 END) AS Hibrido, " +
                "SUM(CASE WHEN tipo_motor = 'Electrico' THEN 1 ELSE 0 END) AS Electrico, " +
                "SUM(monto_total) AS monto_total " +
                "FROM " +
                "reparacion r " +
                "LEFT JOIN " +
                "vehiculo v ON r.id_vehiculo = v.id " +
                "GROUP BY " +
                "tipo_reparacion " +
                "ORDER BY " +
                "MAX(CASE WHEN tipo_motor = 'Gasolina' THEN monto_total ELSE 0 END) + " +
                "MAX(CASE WHEN tipo_motor = 'Diesel' THEN monto_total ELSE 0 END) + " +
                "MAX(CASE WHEN tipo_motor = 'Hibrido' THEN monto_total ELSE 0 END) + " +
                "MAX(CASE WHEN tipo_motor = 'Electrico' THEN monto_total ELSE 0 END) DESC, " +
                "monto_total DESC;";
        return jdbcTemplate.queryForList(sqlQuery);
    }
}