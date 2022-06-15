package com.prog2.registrofazenda.repository;


import com.prog2.registrofazenda.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Override
    Optional<Animal> findById(Long aLong);

    Optional<Animal> findByNumero(int numero);

    boolean existsByNumero(int numero);
}
