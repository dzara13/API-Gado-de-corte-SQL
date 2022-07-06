package com.prog2.registrofazenda.controller;

import com.prog2.registrofazenda.model.Animal;
import com.prog2.registrofazenda.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/animais/debug")
@AllArgsConstructor
public class DebugController {

    private AnimalRepository animalRepository;

    @Transactional
    @PostMapping("/registrar")
    public void registrar() {
        Date hoje = new Date();
        LocalDate localDate = LocalDate.of(2010, 01, 01);
        Date dataAntiga = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Animal animal1 = new Animal(5555, 'M', hoje, "AC");
        animal1.setDesmama(true);
        Animal animal2 = new Animal(5544, 'M', hoje, "BC");
        animal2.setDesmama(true);
        Animal animal3 = new Animal(5533, 'M', dataAntiga, "BC");
        Animal animal4 = new Animal(5522, 'F', dataAntiga, "AC");
        animal4.setDesmama(true);
        Animal animal5 = new Animal(5511, 'F', hoje, "AC");
        Animal animal6 = new Animal(5511, 'F', dataAntiga, "AC");
        animal6.setDesmama(true);
        Animal animal7 = new Animal(5511, 'M', hoje, "AC");
        Animal animal8 = new Animal(5511, 'F', hoje, "AC");
        Animal animal9 = new Animal(5514, 'F', hoje, "AC");
        Animal animal10 = new Animal(5511, 'F', dataAntiga, "AC");
        Animal animal11 = new Animal(5512, 'M', dataAntiga, "AC");
        Animal animal12 = new Animal(5513, 'F', hoje, "AC");

        ArrayList<Animal> animais = new ArrayList<Animal>();
        animais.add(animal1);
        animais.add(animal2);
        animais.add(animal3);
        animais.add(animal4);
        animais.add(animal5);
        animais.add(animal6);
        animais.add(animal7);
        animais.add(animal8);
        animais.add(animal9);
        animais.add(animal10);
        animais.add(animal11);
        animais.add(animal12);

        animalRepository.saveAll(animais);
    }

    @Transactional
    @DeleteMapping("/deletar")
    public void deletar() {

        animalRepository.deleteAll();
    }

}
