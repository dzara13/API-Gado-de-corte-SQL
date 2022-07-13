package com.prog2.registrofazenda.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Animal {
    public Animal(Integer numero, char sexo, Date nascimento, String marca) {
        this.numero = numero;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.marca = marca;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private char sexo;

    @Temporal(TemporalType.DATE)
    private Date nascimento;
    private String marca;
    private boolean desmama;
}