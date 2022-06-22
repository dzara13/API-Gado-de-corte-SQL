package com.prog2.registrofazenda.repository;


import com.prog2.registrofazenda.model.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalModel, Long> {

    Optional<AnimalModel> findByNumero(int numero);

    boolean existsByNumero(int numero);

    @Override
    void deleteById(Long aLong);

    void deleteByNumero(int numero);

    List<AnimalModel> findByNascimentoBetween(Date inicio, Date fim);

    int findByNascimentoTamBetween(Date inicio, Date fim);
}