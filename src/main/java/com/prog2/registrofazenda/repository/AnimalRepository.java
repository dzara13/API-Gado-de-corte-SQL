package com.prog2.registrofazenda.repository;


import com.prog2.registrofazenda.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByNumero(int numero);

    boolean existsByNumero(int numero);

    @Override
    void deleteById(Long aLong);

    void deleteByNumero(int numero);

    List<Animal> findByNascimentoBetween(Date inicio, Date fim);

    int countByNascimentoBetween(Date inicio, Date fim);
}