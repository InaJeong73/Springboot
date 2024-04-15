package com.fastcampus.ch3.di2;

import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean public Car car(){
        //map.put("car",new Car());
        Car car=new Car();
        return car;
    }
    @Bean public Engine engine(){
        Engine engine=new Engine();
        return engine;
    }

    @Bean public Door door(){
        Door door=new Door();
        return door;
    }
}
