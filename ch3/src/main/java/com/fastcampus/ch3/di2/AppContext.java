package com.fastcampus.ch3.di2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import com.fastcampus.ch3.di2.Car;
import com.fastcampus.ch3.di2.Engine;
import com.fastcampus.ch3.di2.Door;
import com.fastcampus.ch3.di2.SportsCar;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class AppContext {
    Map map=new HashMap();
     AppContext(){
        map.put("car",new SportsCar());
        map.put("engine", new Engine());
        map.put("door", new Door());
    }
    //AppConfig 설계도를 받아와서 동적으로 처리하기
    AppContext(Class clazz){
        Object config= null;
        try {
            config = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Method[]method=clazz.getDeclaredMethods();
        for(Method m:method){
            System.out.println("m.getName() = " + m.getName());
            for(Annotation anno:m.getDeclaredAnnotations()){
                //Annotation 타입이 Bean.class라면
                if(anno.annotationType()== Bean.class){
                    try {
                        map.put(m.getName(),m.invoke(config,null));
                        //map.put("car",config.car());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
        doAutoWired();//@AutoWired를 찾아서 맵 내 Bean객체간의 자동 연결 처리
        doResource();//@Resource를 찾아서 맵 내 Bean 객체 간의 자동 연결 처리
     }

    private void doResource() {
        for(Object bean: map.values()){
            for(Field fld:bean.getClass().getDeclaredFields()){
                if(fld.getAnnotation(Resource.class)!=null){
                    try {
                        fld.set(bean,getBean(fld.getType()));//car.engine=obj
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void doAutoWired() {
         for(Object bean: map.values()){
             for(Field fld:bean.getClass().getDeclaredFields()){
                 if(fld.getAnnotation(Autowired.class)!=null){
                     try {
                         fld.set(bean,getBean(fld.getType()));//car.engine=obj
                     } catch (IllegalAccessException e) {
                         throw new RuntimeException(e);
                     }
                 }
             }
         }
    }

    public Object getBean(String id) {
        return map.get(id);
    }
    public Object getBean(Class clazz) {
         for(Object obj:map.values())
             if(clazz.isInstance(obj))
                 return obj;
        return null;
    }
}
