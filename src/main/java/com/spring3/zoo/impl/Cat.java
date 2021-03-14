package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.VoicingMethod;
import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodRottenException;
import com.spring3.zoo.food.FoodType;
import com.spring3.zoo.food.FoodTypeMismatchedException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Qualifier("catQualifier")
@Getter
@Setter
@EnableAsync
public class Cat implements Animal {
    private Food food;
    private Integer age = 1;
    private Integer eatingSpeed = 60;
    private FoodType foodType = FoodType.FISH;
    private AtomicInteger hungerLevel = new AtomicInteger(50);
    private Integer hungerCritical = 150;
    private Integer hungerSpeed = 50;

    @Override
    @VoicingMethod
    public void voice() {
        System.out.println("meow");
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
