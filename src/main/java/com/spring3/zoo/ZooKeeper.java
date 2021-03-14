package com.spring3.zoo;

import com.spring3.zoo.events.AnimalIsHungryEvent;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@EnableAsync
public class ZooKeeper {

    AnimalAsyncService animalService;

    @Autowired
    ZooKeeper(AnimalAsyncService animalService) {
        this.animalService = animalService;
    }

    @Async
    @EventListener
    public void anyAnimalIsHungryEventListener(AnimalIsHungryEvent anyAnimalIsHungryEvent) {
        Animal animal = anyAnimalIsHungryEvent.getAnimal();
        Random random = new Random();
        LocalDateTime expiredDate = LocalDateTime.now().plusSeconds(random.nextInt(50) + 10);
        Integer value = random.nextInt(500) + 100;
        FoodType foodType = animal.getFoodType();
        Food food = Food.builder().foodType(foodType).expiredDate(expiredDate).value(value).build();
        animalService.feed(animal, food);
    }

}
