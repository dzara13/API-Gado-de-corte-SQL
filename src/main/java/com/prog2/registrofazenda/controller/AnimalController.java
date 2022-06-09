package com.prog2.registrofazenda.controller;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class AnimalController {

    private AnimalService animalService;

    @GetMapping("/animais")
    public List<Animal> listar() {
        return animalService.listar();
    }

    @PostMapping("/animais")
    @ResponseStatus(HttpStatus.CREATED)
    private Animal registrar(@Valid @RequestBody Animal animal) {
        return animalService.salvar(animal);
    }
}
