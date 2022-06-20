package com.prog2.registrofazenda.service;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.model.exception.NegocioException;
import com.prog2.registrofazenda.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalService {
    private AnimalRepository animalRepository;

    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    @Transactional
    public Animal salvar(Animal animal) {
        if (!animalRepository.existsByNumero(animal.getNumero())) {
            return animalRepository.save(animal);
        } else {
            throw new NegocioException("O numero do Animal ja Existe na base de dados!");
        }
    }

    public ResponseEntity<Animal> buscarId(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        if (animal.isPresent()) {
            return ResponseEntity.ok(animal.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Animal> buscarNumero(int numero) {
        Optional<Animal> animal = animalRepository.findByNumero(numero);
        if (animal.isPresent()) {
            return ResponseEntity.ok(animal.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Void> deletarId(Long id) {
        if (!animalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            animalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @Transactional
    public ResponseEntity<Void> deletarNumero(int numero) {
        if (!animalRepository.existsByNumero(numero)) {
            return ResponseEntity.notFound().build();
        } else {
            animalRepository.deleteByNumero(numero);
            return ResponseEntity.noContent().build();
        }
    }

    public List<Animal> buscarPorPeriodo(Date inicio, Date fim) {
        return animalRepository.findByNascimentoBetween(inicio, fim);
    }
}