package com.prog2.registrofazenda.controller;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Animal registrar(@Valid @RequestBody Animal animal) {
        return animalService.salvar(animal);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Animal> buscarId(@PathVariable Long id) {
        return animalService.buscarId(id);
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Animal> findByNumero(@PathVariable int numero) {
        return animalService.buscarNumero(numero);
    }

    @DeleteMapping("/deleteid/{id}")
    public ResponseEntity<Void> deletarId(@PathVariable Long id) {

        return animalService.deletarId(id);
    }

    @DeleteMapping("/deletenumero/{numero}")
    public ResponseEntity<Void> deletarNumero(@PathVariable int numero) {
        return animalService.deletarNumero(numero);
    }

    @GetMapping("/periodonascimento")
    public List<Animal> buscarPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fim) {
        return animalService.buscarPorPeriodo(inicio, fim);
    }

    @GetMapping("/contagem")
    public long contagem() {
        return animalService.contadorDeRegistros();
    }
    @GetMapping("/contagemporperiodo")
    @ResponseStatus(HttpStatus.FOUND)
    public long contagemPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fim) {
        return animalService.contagemPorPeriodo(inicio, fim);
    }

}