package com.prog2.registrofazenda.model;


import lombok.Data;

import java.util.List;

@Data
public class MetricasModel {
    private List<Long> idades;
    private double mediaAnual;
    private double mediaPeriodo;
}