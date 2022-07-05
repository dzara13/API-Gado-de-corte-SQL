package com.prog2.registrofazenda.service;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.model.MetricasModel;
import com.prog2.registrofazenda.model.exception.NegocioException;
import com.prog2.registrofazenda.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.prog2.registrofazenda.dateutils.DateUtils.convertToLocalDateViaInstant;

@Service
@AllArgsConstructor
public class AnimalService {
    private AnimalRepository animalRepository;

    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    @Transactional
    public Animal salvar(Animal animal) throws NegocioException {

        if (!animalRepository.existsByNumeroAndDesmama((animal.getNumero()), false)) {

            return animalRepository.save(animal);

        } else {
            throw new NegocioException("O numero da mãe do animal já esta em uso por outro animal que nao foi desmamado");
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

    public List<Animal> buscarNumero(Integer numero) throws NegocioException {

        List<Animal> resultado = animalRepository.findByNumero(numero);
        if (!resultado.isEmpty()) {
            return resultado;
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

        long animais = animalRepository.count();
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

    @Transactional
    public void desmama(Long id) throws NegocioException {
        Optional<Animal> animalBuscado = animalRepository.findById(id);
        if (animalBuscado.isPresent()) {
            Animal animal = animalBuscado.get();
            animal.setDesmama(true);
            animalRepository.save(animal);
        } else {
            throw new NegocioException("ID não encontrado ou animal ja desmamado");
        }
    }

    public MetricasModel metricas(Date inicio, Date fim) {

        var metricas = new MetricasModel();
        var agora = new Date();

        double periodoContagem = contagemPorPeriodo(inicio, fim);


        //conversão de date para temporal
        LocalDate inicioConvert = convertToLocalDateViaInstant(inicio);
        LocalDate fimConvert = convertToLocalDateViaInstant(fim);

        var diffFinal = agora.getTime() - fim.getTime();
        TimeUnit timeFinal = TimeUnit.DAYS;
        long diferencaEmDias = timeFinal.convert(diffFinal, TimeUnit.MILLISECONDS);
        long anos = diferencaEmDias / 365;

        var diffInicio = agora.getTime() - inicio.getTime();
        TimeUnit timeInicio = TimeUnit.DAYS;
        var diferencaEmDiasFinal = timeInicio.convert(diffInicio, TimeUnit.MILLISECONDS);
        var anosFinal = diferencaEmDiasFinal / 365;

        //pegando o tamanho do periodo em meses
        var periodoMensal = ChronoUnit.MONTHS.between(inicioConvert, fimConvert);
        var periodoAnual = ChronoUnit.YEARS.between(inicioConvert, fimConvert);
        //media de nascimentos mensal no periodo especificado
        var mediaMensalPeriodo = periodoContagem / periodoMensal;
        //media de nascimentos anual no periodo especificado
        var mediaAnualPeriodo = periodoContagem / periodoAnual;

        List<Long> idades = new ArrayList<>();
        idades.add(anos);
        idades.add(anosFinal);

        var porPeriodo = contagemPorPeriodo(inicio, fim);
        var contagemTotal = contadorDeRegistros();

        metricas.setMediaPeriodo(mediaMensalPeriodo);
        metricas.setMediaAnual(mediaAnualPeriodo);
        metricas.setIdades(idades);
        metricas.setNumeroDeNascimentosPeriodo(porPeriodo);
        metricas.setContagemTotal(contagemTotal);

        return metricas;
    }
}