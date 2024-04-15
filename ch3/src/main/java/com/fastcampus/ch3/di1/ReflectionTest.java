package com.fastcampus.ch3.di1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static <Annotations> void main(String[]args) throws Exception{
        Car car=new Car();
        Class carClass=car.getClass();//1. 객체로부터 Class객체(설계도)얻기
        carClass=Car.class;//2.객체 리터럴로부터 Class객체 열기
        carClass = Class.forName("com.fastcampus.ch3.di1.Car");

        //1. 설계도 객체로부터 객체 생성하기
        Car car2=(Car)carClass.newInstance();
        System.out.println("car2 = " + car2);

       //2. 클래스에 선언된 멤버변수(field)와 method 목록 열기
        Field[]myArr=carClass.getDeclaredFields();
        Method[]methodArr=carClass.getDeclaredMethods();
        //Method[]methodArr=carClass.getMethods();

        for(Field mv:myArr) System.out.println(mv.getName());
        for(Method method:methodArr) System.out.println(method.getName());

        Method method=carClass.getMethod("setEngine", Engine.class);
        method.invoke(car, new Engine());
        System.out.println("car = " + car);

        //3. mv에 set붙여서 setter를 호출하기
        for(Field mv:myArr){
            System.out.println("mv = " + mv);
            String methodName = "set" + StringUtils.capitalize(mv.getName());//"set"+"Engine"
            System.out.println("methodName = " + methodName);
            method=carClass.getMethod(methodName,mv.getType()); // carClass.getMethod("setEngine",Engine.class)
            method.invoke(car,mv.getType().newInstance());//car.setEngine(new Engine());
        }

        System.out.println("car = " + car);
        for(Field mv:myArr){
            Annotation[]annoArr=mv.getDeclaredAnnotations();
            for(Annotation anno:annoArr){
                System.out.println("mv.getName() = " + mv.getName());
                System.out.println("anno.annotationType().getSimpleName()= " + anno.annotationType().getSimpleName());
                System.out.println(anno.annotationType() == Autowired.class);
            }
        }
        car=new Car();
        carClass=car.getClass();
        Field[]carArr=carClass.getDeclaredFields();
        for(Field mv:carArr){
           Annotation[]annoArr= mv.getDeclaredAnnotations();
           for(Annotation anno:annoArr){
               if(anno.annotationType()==Autowired.class){
                  String methodName="set"+StringUtils.capitalize(mv.getName());
                  method= carClass.getMethod(methodName,mv.getType());
                  method.invoke(car,mv.getType().newInstance());
               }
           }
        }
        System.out.println("car = " + car);
    }
}
