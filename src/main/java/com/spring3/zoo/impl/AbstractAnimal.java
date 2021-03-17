package com.spring3.zoo.impl;

import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodTypeMismatchedException;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractAnimal  implements Animal {

    boolean eatWithArgs(Food food, AtomicInteger hungerLevel, Integer eatingSpeed) {
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

    void feedWithArgs(Food food, Food myFood) {
        if (food.getFoodType().equals(myFood.getFoodType())) {
            myFood.setValue(food.getValue());
            myFood.setExpiredDate(food.getExpiredDate());
        }
        else {
            throw new FoodTypeMismatchedException("[FEED ERROR]" + getName() + "dont eat food: " + food.getFoodType().toString());
        }
    }
}
