package com.spring3.zoo;

import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;

import java.util.concurrent.atomic.AtomicInteger;

public interface Animal {

    void voice();

    void feed(Food food);

    void gettingHungry();

    boolean isHungry();

    Food getFood();

    Integer getEatingSpeed();

    FoodType getFoodType();

    String getName();

    boolean eat();

    AtomicInteger getHungerLevel();

    Integer getHungerCritical();
}
