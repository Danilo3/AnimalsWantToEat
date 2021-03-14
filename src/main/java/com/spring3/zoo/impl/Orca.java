package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.VoicingMethod;
import com.spring3.zoo.Animal;
import com.spring3.zoo.food.FoodTypeMismatchedException;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodRottenException;
import com.spring3.zoo.food.FoodType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.concurrent.atomic.AtomicInteger;


@Component
@Getter
@Setter
@EnableAsync
public class Orca implements Animal {
    private Food food;
    private Integer age = 23;
    private Integer eatingSpeed = 750;
    private FoodType foodType = FoodType.FISH;
    private AtomicInteger hungerLevel = new AtomicInteger(1000);
    private Integer hungerCritical = 3000;
    private Integer hungerSpeed = 300;


    @Override
    @VoicingMethod
    public void voice() {
        System.out.println("ultrasound");
    }

    @Override
    public void feed(Food food) {
        if (food.getFoodType().equals(this.foodType)) {
            this.food = food;
        }
        else {
            throwException(new FoodTypeMismatchedException("[FEED ERROR]" + getName() + "dont eat food :" + food.getFoodType().toString()));
        }
    }

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
        return "[" + this.getClass().getSimpleName() + "] ";
    }


    @Override
    public void throwException(Exception e) {

    }
}
