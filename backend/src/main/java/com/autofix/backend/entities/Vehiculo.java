package com.autofix.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroPatente;
    private String marca;
    private String modelo;
    private String tipo;
    private int yearFabricacion;
    private String tipoMotor;
    private int numeroAsientos;
    private int kilometraje;

    // Puede ser necesario en el modelo alguna fk no referenciada por ORM
    // @COLUMN sirve para mapear
}
