package com.autofix.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BonoDcto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bono;
    private String marca;
    private int num_bonos;
    private int monto;

    @Column(name = "id_reparacion")
    private Long id_reparacion;

}
