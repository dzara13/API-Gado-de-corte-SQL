package com.prog2.registrofazenda.repository;

import com.prog2.registrofazenda.model.Maes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MaesRepository extends JpaRepository<Maes, Long> {

    Optional<Maes> findByNumero(int numero);

    boolean existsByNumero(int numero);

    List<Maes> findByNascimentoBetween(Date inicio, Date fim);

    int countByNascimentoBetween(Date inicio, Date fim);
}
