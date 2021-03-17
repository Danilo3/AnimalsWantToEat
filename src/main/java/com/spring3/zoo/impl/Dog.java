package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.VoicingMethod;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Getter
@Setter
public class Dog extends AbstractAnimal {
    private Food food = new Food(null, FoodType.MEAT, null);
    private String name= "Dog";
    private Integer eatingSpeed = 200;
    private AtomicInteger hungerLevel = new AtomicInteger(500);
    private Integer hungerCritical = 1000;
    private Integer hungerSpeed = 300;

    @Override
    @VoicingMethod
    public void voice() {
        System.out.println("gav");
    }

    @Override
    public void feed(Food food) {
        feedWithArgs(food, this.food);
    }

    @Override
    public boolean eat() {
        return eatWithArgs(food, hungerLevel, eatingSpeed);
    }

    @Async
    @Scheduled(cron = "0/5 * * * * * ")
    public void gettingHungry() {
        hungerLevel.set(hungerLevel.intValue() + hungerSpeed);
    }


    public boolean isHungry() {
        return hungerLevel.get() >= hungerCritical;
    }

    @Override
    public FoodType getFoodType() {
        return this.food.getFoodType();
    }

    public String getName() {
        return "[" + name + "] ";
    }

}
