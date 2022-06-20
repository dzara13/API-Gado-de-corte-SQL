package com.prog2.registrofazenda.controller;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class AnimalController {

    private AnimalService animalService;

    @GetMapping("/animal")
    public List<Animal> listar() {
        return animalService.listar();
    }

    @PostMapping("/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal registrar(@Valid @RequestBody Animal animal) {
        return animalService.salvar(animal);
    }

    @GetMapping("/animais/id/{id}")
    public ResponseEntity<Animal> buscarId(@PathVariable Long id) {
        return animalService.buscarId(id);
    }

    @GetMapping("/animais/numero/{numero}")
    public ResponseEntity<Animal> findByNumero(@PathVariable int numero) {
        return animalService.buscarNumero(numero);
    }

    @DeleteMapping("/animais/deleteid/{id}")
    public ResponseEntity<Void> deletarId(@PathVariable Long id) {

        return animalService.deletarId(id);
    }

    @DeleteMapping("/animais/deletenumero/{numero}")
    public ResponseEntity<Void> deletarNumero(@PathVariable int numero) {
        return animalService.deletarNumero(numero);
    }

    @GetMapping("/animais/periodonascimento")
    public List<Animal> buscarPorPeriodo(@RequestParam Date inicio, @RequestParam Date fim) {
        return animalService.buscarPorPeriodo(inicio, fim);
    }
}