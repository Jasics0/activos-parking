package com.activos.activosparking.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "automoviles")
@Getter
@Setter
public class Automovil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "placa")
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_type_automovil", referencedColumnName = "id")
    private TypeAutomovil typeAutomovil;

    @Column(name= "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column (name = "fecha_salida")
    private LocalDateTime fechaSalida;


}
