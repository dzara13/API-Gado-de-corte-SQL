package com.prog2.registrofazenda.service;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.model.exception.NegocioException;
import com.prog2.registrofazenda.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (animalRepository.existsByNumero(animal.getNumero()) == true) {
            throw new NegocioException("O numero do Animal ja Existe na base de dados!");
        } else
            return animalRepository.save(animal);
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

    public ResponseEntity<Void> deletarId(Long id) {
        if (animalRepository.existsById(id) == true) {

            animalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();


        }
    }
}