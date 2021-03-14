package com.spring3.aspect;

import com.spring3.zoo.Animal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

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

    @After(value = "@annotation(com.spring3.aspect.annotationMarker.FeedMethod)")
    public void afterFeed(JoinPoint joinPoint) {
        Animal animal = getAnimalFromJointPoint(joinPoint);
        animal.eat();
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
    public static Animal getAnimalFromJointPoint(JoinPoint joinPoint) {
        Animal animal = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof Animal) {
                animal = (Animal) arg;
            }
        }
        if (animal == null) {
            System.out.println("[AFTER FEED ERROR]: no arg for animal");
        }
        return animal;
    }

    @AfterThrowing(value = "execution(* com.spring3.zoo.Animal.throwException())", throwing = "e")
    public void afterThrowing(Throwable e) {
        System.out.println(e.getMessage());
    }

}
