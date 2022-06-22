package com.prog2.registrofazenda.service;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.model.exception.NegocioException;
import com.prog2.registrofazenda.repository.AnimalRepository;
import lombok.AllArgsConstructor;
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
    public Animal salvar(Animal animal) throws NegocioException {
        if (!animalRepository.existsByNumero(animal.getNumero())) {
            return animalRepository.save(animal);
        } else {
            throw new NegocioException("O numero do Animal ja Existe na base de dados!");
        }
    }

    public Animal buscarId(Long id) throws NegocioException {

        Optional<Animal> animal = animalRepository.findById(id);
        if (animal.isPresent()) {
            return animal.get();
        } else {
            throw new NegocioException(String.format("Nenhum animal encontrado com o ID: %d", id));
        }
    }

    public Animal buscarNumero(Integer numero) throws NegocioException {

        Optional<Animal> resultado = animalRepository.findByNumero(numero);
        if (resultado.isPresent()) {
            return resultado.get();
        } else {
            throw new NegocioException(String.format("Nenhum animal encontrado com o numero: %d", numero));
        }
    }

    @Transactional
    public Void deletarId(Long id) throws NegocioException {
        boolean existsById = animalRepository.existsById(id);
        if (existsById) {
            animalRepository.deleteById(id);
        } else {
            throw new NegocioException(String.format("Não foi encontrado o ID: %d", id));
        }
        return null;
    }

    @Transactional
    public Void deletarNumero(int numero) throws NegocioException {
        boolean existsByNumero = animalRepository.existsByNumero(numero);
        if (existsByNumero) {
            animalRepository.deleteByNumero(numero);
        } else {
            throw new NegocioException(String.format("Não foi possivel encontrar o numero: %d", numero));
        }
        return null;
    }

    public List<Animal> buscarPorPeriodo(Date inicio, Date fim) throws NegocioException {
        List<Animal> animals = animalRepository.findByNascimentoBetween(inicio, fim);
        if (animals.isEmpty()) {
            throw new NegocioException("Nenhum Registro no Periodo especificado");
        } else {
            return animals;
        }
    }

    public Long contadorDeRegistros() throws NegocioException {
        Long animais = animalRepository.count();
        if (animais == 0) {
            throw new NegocioException("Nenhum Registro encontrado");
        } else {
            return animais;
        }
    }

    public long contagemPorPeriodo(Date inicio, Date fim) throws NegocioException {
        List<Animal> quantidade = buscarPorPeriodo(inicio, fim);
        if (quantidade.isEmpty()) {
            throw new NegocioException("Nenhum Registro no periodo especificado");
        } else
            return quantidade.size();
    }
}