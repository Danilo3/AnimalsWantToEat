package com.spring3.configuration;

import com.spring3.zoo.impl.Orca;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
@ComponentScan(value = {"com"})
@PropertySource("classpath:application.yml")
@Profile("annotationConfiguration")
@EnableAspectJAutoProxy()
@EnableScheduling
@EnableAsync
public class AnnotationConfiguration {

    @Bean
    TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
}
