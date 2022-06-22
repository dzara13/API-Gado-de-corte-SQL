package com.prog2.registrofazenda.controller;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.model.exception.NegocioException;
import com.prog2.registrofazenda.service.AnimalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/animais")
public class AnimalController {

    private AnimalService animalService;

    @GetMapping
    public List<Animal> listar() {
        return animalService.listar();
    }

    @PostMapping
    public ResponseEntity<Animal> registrar(@Valid @RequestBody Animal animal) {
        try {
            Animal animals = animalService.salvar(animal);
            return ResponseEntity.status(HttpStatus.CREATED).body(animals);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Animal> buscarId(@PathVariable Long id) {
        try {
            Animal animal = animalService.buscarId(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(animal);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Animal> findByNumero(@PathVariable int numero) {
        try {
            Animal animal = animalService.buscarNumero(numero);
            return ResponseEntity.status(HttpStatus.FOUND).body(animal);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteid/{id}")
    public ResponseEntity<Void> deletarId(@PathVariable Long id) {
        try {
            Void animal = animalService.deletarId(id);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletenumero/{numero}")
    public ResponseEntity<Void> deletarNumero(@PathVariable int numero) {
        try {
            Void animal = animalService.deletarNumero(numero);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/periodonascimento")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<Animal>> buscarPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fim) {
        try {
            List<Animal> animals = animalService.buscarPorPeriodo(inicio, fim);
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
            Long animais = animalService.contadorDeRegistros();
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
            long registros = animalService.contagemPorPeriodo(inicio, fim);
            return ResponseEntity.status(HttpStatus.FOUND).body(registros);
        } catch (NegocioException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}