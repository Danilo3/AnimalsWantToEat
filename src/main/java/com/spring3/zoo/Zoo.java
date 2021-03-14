package com.spring3.zoo;

import com.spring3.zoo.events.AnimalIsHungryEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@EnableAsync
public class Zoo {
    private final Animal animal1;

    private final Animal animal2;

    private final Animal animal3;

    private final List<Animal> animals;

    private final ApplicationEventPublisher eventPublisher;

    @Value(value = "${name}")
    private String name;

    @Autowired
    public Zoo(
            Animal cat,
            Animal dog,
            Animal orca,
            List<Animal> animals,
            ApplicationEventPublisher eventPublisher) {
        this.animal1 = cat;
        this.animal2 = dog;
        this.animal3 = orca;
        this.animals = animals;
        this.eventPublisher = eventPublisher;
    }

    public Animal getAnimal1() {
        return animal1;
    }

    public Animal getAnimal2() {
        return animal2;
    }

    public Animal getAnimal3() {
        return animal3;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public String getName() {
        return name;
    }


    @Async
    @Scheduled(cron = "0/5 * * * * * ")
    public void checkIfHungry() {
        for (Animal animal:
             animals) {
            if (animal.isHungry()) {
                animal.voice();
                eventPublisher.publishEvent(new AnimalIsHungryEvent(animal));
            }
        }
    }

    public void start() {
        System.out.println("Starting zoo, wait for animals want to eat.....");
    }
}



