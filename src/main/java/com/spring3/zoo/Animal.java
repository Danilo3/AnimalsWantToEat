package com.spring3.zoo;

import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodRottenException;
import com.spring3.zoo.food.FoodType;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public interface Animal {

    void voice();

    void feed(Food food);

    Integer getAge();

    void throwException(Exception e);

    void gettingHungry();

    boolean isHungry();

    FoodType getFoodType();

    String getName();

    boolean eat();

    default boolean eatWithArgs(Food food, AtomicInteger hungerLevel, Integer eatingSpeed) {
        if (food != null) {
            if (food.getExpiredDate().isAfter(LocalDateTime.now())) {
                throwException(new FoodRottenException("[EAT ERROR]"+ getName() + " wont eat expired food"));
            }
            if (food.getValue() <= 0 ) {
                System.out.println("[EAT ERROR]" + getName() + "food amount < 0");
                return false;
            }
            var foodRemain = food.getValue();
            int newHungerLevel = hungerLevel.intValue() - foodRemain;
            hungerLevel.set(Math.max(newHungerLevel, 0));
            foodRemain -= eatingSpeed;

            if (foodRemain < 0) {
                foodRemain = 0;
                System.out.println("[EAT INFO]" + getName() + "food amount < 0 since now");
            }
            food.setValue(foodRemain);
            return true;
        }
        System.out.println("[EAT ERROR] " + getName() + "have no food at all!");
        return false;
    }

    AtomicInteger getHungerLevel();

    Integer getHungerCritical();
}
