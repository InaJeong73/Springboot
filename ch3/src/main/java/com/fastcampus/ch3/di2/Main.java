package com.fastcampus.ch3.di2;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

class Car{
    @Autowired Engine engine;//byType으로 맵 내에서 자동 검색해서 대입시킴.
//    @Resource(name="door")
    @Resource Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
class SportsCar extends Car{
    @Override
    public String toString() {
        return "SportsCar{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
class Engine{
    @Override
    public String toString() {
        return "Engine{}";
    }
}
class Door{
    @Override
    public String toString() {
        return "Door{}";
    }
}
public class Main {
    public static void main(String[] args) {
        AppContext ac=new AppContext(AppConfig.class);
        Car car=(Car)ac.getBean("car");//byName
        Car car2=(Car)ac.getBean(Car.class);//byType
        Engine engine = (Engine)ac.getBean("engine");
        Door door=(Door)ac.getBean(Door.class);


        //Bean들끼리의 관계를 설정 -수동
//        car.setEngine(engine);
//        car.setDoor(door);


        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);

    }
}
