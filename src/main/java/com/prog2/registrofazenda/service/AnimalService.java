package com.prog2.registrofazenda.service;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalService {
    private AnimalRepository animalRepository;

    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    public Animal salvar(Animal animal) {

        return animalRepository.save(animal);
    }
}