package com.spring3.aspect;

import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodEmptyException;
import com.spring3.zoo.food.FoodRottenException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class AnimalAspect {

    @Before("@annotation(com.spring3.aspect.annotationMarker.VoicingMethod)")
    public void beforeVoice(JoinPoint joinPoint) {
        Animal animal = (Animal) joinPoint.getTarget();
        System.out.print(animal.getName() + "wants to eat and say: ");
    }

    @Pointcut("execution(* com.spring3.zoo.Animal.eat())")
    public void eatPoint() {
    }

    @Before(value = "eatPoint()")
    public void beforeEat(JoinPoint joinPoint) {
        Animal animal = (Animal) joinPoint.getTarget();
        if (animal.getFood() != null) {
            if (LocalDateTime.now().isAfter(animal.getFood().getExpiredDate())) {
                throw new FoodRottenException("[EAT ERROR]" + animal.getName() + " wont eat expired food");
            }
            if (animal.getFood().getValue() <= 0) {
                throw new FoodEmptyException("[EAT ERROR]" + animal.getName() + "food amount < 0");
            }
        } else {
            throw new FoodEmptyException("[EAT ERROR] " + animal.getName() + "have no food at all!");
        }
    }

    @AfterReturning(value = "eatPoint()", returning = "returnValue")
    public void afterEat(JoinPoint joinPoint, Object returnValue) {
        boolean isAte = (boolean) returnValue;
        Animal animal = (Animal) joinPoint.getTarget();
        if (isAte) {
            System.out.println(animal.getName() + "ate and it is" + (animal.isHungry() ? " STILL " : " DON'T ") + "hungry now. Hungry level is now: " + animal.getHungerLevel().get() + "/" + animal.getHungerCritical());
        } else {
            System.out.println(animal.getName() + " didn't eat for some reasons");
        }
    }

    @AfterThrowing(value = "execution(* com.spring3.zoo.Animal.*(..))", throwing = "e")
    public void afterThrowing(Throwable e) {
        System.out.println(e.getMessage());
    }

}
