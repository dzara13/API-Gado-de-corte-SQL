package com.prog2.registrofazenda.controller;

import com.prog2.registrofazenda.model.MetricasModel;
import com.prog2.registrofazenda.model.Maes;
import com.prog2.registrofazenda.model.exception.NegocioException;
import com.prog2.registrofazenda.service.MaesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/maes")
public class MaesController {

    private MaesService maesService;

    @GetMapping
    public List listar() {
        return maesService.listar();
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Maes> findByNumero(@PathVariable int numero) {
        try {
            Maes animal = maesService.buscarNumero(numero);
            return ResponseEntity.status(HttpStatus.FOUND).body(animal);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/periodonascimento")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<Maes>> buscarPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fim) {
        try {
            List<Maes> animals = maesService.buscarPorPeriodo(inicio, fim);
            return ResponseEntity.status(HttpStatus.FOUND).body(animals);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contagem")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Long> contagem() {
        try {
            Long animais = maesService.contadorDeRegistros();
            return ResponseEntity.status(HttpStatus.FOUND).body(animais);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contagemporperiodo")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Long> contagemPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fim) {
        try {
            long registros = maesService.contagemPorPeriodo(inicio, fim);
            return ResponseEntity.status(HttpStatus.FOUND).body(registros);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/metricas")
    public ResponseEntity<MetricasModel> metricas(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fim) {
        MetricasModel metricas = maesService.metricas(inicio, fim);
        return ResponseEntity.status(HttpStatus.OK).body(metricas);

    }
}
