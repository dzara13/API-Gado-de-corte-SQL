package com.prog2.registrofazenda.service;

import com.prog2.registrofazenda.model.MetricasModel;
import com.prog2.registrofazenda.model.Maes;
import com.prog2.registrofazenda.model.exception.NegocioException;
import com.prog2.registrofazenda.repository.MaesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.prog2.registrofazenda.dateutils.DateUtils.convertToLocalDateViaInstant;

@Service
@AllArgsConstructor
public class MaesService {
    private MaesRepository maesRepository;


    public List listar() {

        return maesRepository.findAll();
    }

    public Maes buscarNumero(Integer numero) throws NegocioException {

        Optional<Maes> resultado = maesRepository.findByNumero(numero);
        if (resultado.isPresent()) {
            return resultado.get();
        } else {
            throw new NegocioException(String.format("Nenhum animal encontrado com o numero: %d", numero));
        }
    }

    public List<Maes> buscarPorPeriodo(Date inicio, Date fim) throws NegocioException {

        List<Maes> animals = maesRepository.findByNascimentoBetween(inicio, fim);
        if (animals.isEmpty()) {
            throw new NegocioException("Nenhum Registro no Periodo especificado");
        } else {
            return animals;
        }
    }

    public Long contadorDeRegistros() throws NegocioException {

        long animais = maesRepository.count();
        if (animais == 0) {
            throw new NegocioException("Nenhum Registro encontrado");
        } else {
            return animais;
        }
    }

    public long contagemPorPeriodo(Date inicio, Date fim) throws NegocioException {

        List<Maes> quantidade = buscarPorPeriodo(inicio, fim);
        if (quantidade.isEmpty()) {
            throw new NegocioException("Nenhum Registro no periodo especificado");
        } else
            return quantidade.size();
    }

    public MetricasModel metricas(Date inicio, Date fim) {
        var metricas = new MetricasModel();

        double periodoContagem = contagemPorPeriodo(inicio, fim);

        //conversão de date para temporal
        LocalDate inicioConvert = convertToLocalDateViaInstant(inicio);
        LocalDate fimConvert = convertToLocalDateViaInstant(fim);

        //pegando o tamanho do periodo em meses
        long periodoMensal = ChronoUnit.MONTHS.between(inicioConvert, fimConvert);
        long periodoAnual = ChronoUnit.YEARS.between(inicioConvert, fimConvert);
        //media de nascimentos mensal no periodo especificado
        double mediaMensalPeriodo = periodoContagem / periodoMensal;
        //media de nascimentos anual no periodo especificado
        double mediaAnualPeriodo = periodoContagem / periodoAnual;

        metricas.setMediaPeriodo(mediaMensalPeriodo);
        metricas.setMediaAnual(mediaAnualPeriodo);
        return metricas;
    }
}