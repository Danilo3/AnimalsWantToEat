package com.spring3.zoo.food;

public class FoodTypeMismatchedException  extends RuntimeException {
    public FoodTypeMismatchedException(String message) {
        super(message);
    }
}
