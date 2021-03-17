package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.EatMethod;
import com.spring3.aspect.annotationMarker.VoicingMethod;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Qualifier("catQualifier")
@Getter
@Setter
@EnableAsync
public class Cat extends AbstractAnimal {
    private Food food = new Food(null, FoodType.FISH, null);
    private Integer eatingSpeed = 60;
    private FoodType foodType = FoodType.FISH;
    private AtomicInteger hungerLevel = new AtomicInteger(50);
    private Integer hungerCritical = 150;
    private Integer hungerSpeed = 50;
    private String name = "Cat";

    @Override
    @VoicingMethod
    public void voice() {
        System.out.println("meow");
    }

    @Override
    public void feed(Food food) {
        feedWithArgs(food, this.food);
    }

    @Override
    @EatMethod
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


    public String getName() {
        return "[" + name + "] ";
    }

}
