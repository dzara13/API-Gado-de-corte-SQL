package com.prog2.registrofazenda.service;

import com.prog2.registrofazenda.model.AnimalModel;
import com.prog2.registrofazenda.model.MetricasModel;
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

    private MetricasModel metricasModel;
    private AnimalRepository animalRepository;

    public List<AnimalModel> listar() {
        return animalRepository.findAll();
    }

    @Transactional
    public AnimalModel salvar(AnimalModel animalModel) throws NegocioException {
        if (!animalRepository.existsByNumero(animalModel.getNumero())) {
            return animalRepository.save(animalModel);
        } else {
            throw new NegocioException("O numero do Animal ja Existe na base de dados!");
        }
    }

    public AnimalModel buscarId(Long id) throws NegocioException {

        Optional<AnimalModel> animal = animalRepository.findById(id);
        if (animal.isPresent()) {
            return animal.get();
        } else {
            throw new NegocioException(String.format("Nenhum animal encontrado com o ID: %d", id));
        }
    }

    public AnimalModel buscarNumero(Integer numero) throws NegocioException {

        Optional<AnimalModel> resultado = animalRepository.findByNumero(numero);
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

    public List<AnimalModel> buscarPorPeriodo(Date inicio, Date fim) throws NegocioException {
        List<AnimalModel> animalModels = animalRepository.findByNascimentoBetween(inicio, fim);
        if (animalModels.isEmpty()) {
            throw new NegocioException("Nenhum Registro no Periodo especificado");
        } else {
            return animalModels;
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
        List<AnimalModel> quantidade = buscarPorPeriodo(inicio, fim);
        if (quantidade.isEmpty()) {
            throw new NegocioException("Nenhum Registro no periodo especificado");
        } else
            return quantidade.size();
    }

    public long metrics(Date inicio, Date fim) throws NegocioException {
        return contagemPorPeriodo(inicio, fim) / animalRepository.findByNascimentoTamBetween(inicio, fim);
    }

}