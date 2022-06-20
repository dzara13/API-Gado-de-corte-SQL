package com.prog2.registrofazenda.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private char sexo;

    @Temporal(TemporalType.DATE)
    private Date nascimento;
    private String marca;
}
