package com.spring3;

import com.spring3.configuration.AnnotationConfiguration;
import com.spring3.zoo.Zoo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = getAnnotationContext("annotationConfiguration");
        Zoo setterZoo = context.getBean("zoo", Zoo.class);
        setterZoo.start();
    }

    private static ApplicationContext getAnnotationContext(String profile) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.getEnvironment().setActiveProfiles(profile);
        annotationConfigApplicationContext.register(AnnotationConfiguration.class);
        annotationConfigApplicationContext.refresh();
        return annotationConfigApplicationContext;
    }

}
