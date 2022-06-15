package com.prog2.registrofazenda.controller;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/animal/{id}")
    public ResponseEntity<Animal> buscarId(@PathVariable Long id) {
        return animalService.buscarId(id);
    }

    @GetMapping("/animal/numero/{numero}")
    public ResponseEntity<Animal> findByNumero(@PathVariable int numero) {
        return animalService.buscarNumero(numero);
    }

    @DeleteMapping("/animal/{id}")
    public Animal excluirId(@PathVariable Long id) {
        animalService.deletarId(id);
        return null;
    }
}