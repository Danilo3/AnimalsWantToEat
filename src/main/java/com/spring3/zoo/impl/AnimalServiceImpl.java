package com.spring3.zoo.impl;

import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalService;
import com.spring3.zoo.food.Food;

import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {


    @Override
    public void feed(Animal animal, Food food) {
        animal.feed(food);
        animal.eat();
    }
}
