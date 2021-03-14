package com.spring3.zoo.events;

import com.spring3.zoo.Animal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class AnimalIsHungryEvent extends ApplicationEvent {
    private Animal animal;

    public AnimalIsHungryEvent(Object animal) {
        super(animal);
        this.animal = (Animal) animal;
    }
}
