package com.fastcampus.ch3.di1;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class Car{
    @Autowired
    Engine engine;
    @Autowired
    Door door;

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
class Engine{}
class Door{}
class SportsCar extends Car{}
class Truck extends Car{}
public class Main {
    public static void main(String[]args)throws Exception{
        Car car = (Car)getObject("car");
        System.out.println("car = " + car);

        Engine engine = (Engine) getObject("engine");
        System.out.println("engine = " + engine);
        
        Door door = (Door) getObject("door");
        System.out.println("door = " + door);
    }
    static Car getCar(){
        return new Truck();
    }
    static Object getObject(String key)throws Exception{
        Properties prop=new Properties();
            Class clazz=null;

            prop.load(new FileReader("config.txt"));
            String className=prop.getProperty(key);
            clazz = Class.forName(className);

            //이름을 지정하면 이름에 해당하는 클래스 객체를 얻는다.
            return clazz.newInstance();
    }
}
