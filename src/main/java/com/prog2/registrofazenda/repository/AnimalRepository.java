package com.prog2.registrofazenda.repository;


import com.prog2.registrofazenda.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByNumero(int numero);

    boolean existsByNumero(int numero);

    @Override
    void deleteById(Long aLong);

    void deleteByNumero(int numero);

    List<Animal> findByNascimentoBetween(Date inicio, Date fim);

    boolean existsByNumeroAndDesmama(int numero, boolean desmama);

    List<Animal>findByDesmama(boolean desmamados);
}