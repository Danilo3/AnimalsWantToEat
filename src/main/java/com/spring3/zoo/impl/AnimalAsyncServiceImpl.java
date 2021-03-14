package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.FeedMethod;
import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalAsyncService;
import com.spring3.zoo.food.Food;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class AnimalAsyncServiceImpl implements AnimalAsyncService {

    @Async
    @Override
    @FeedMethod
    public void feed(Animal animal, Food food) {
        animal.feed(food);
    }
}
