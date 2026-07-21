package dev.kaldiroglu.fpj.ch10.api;

import dev.kaldiroglu.fpj.ch10.shape.Triangle;

import java.lang.reflect.RecordComponent;

public class ApiExample {

    public static void main(String[] args) {
        Class clazz = Triangle.class;
        boolean isRecord = clazz.isRecord();
        System.out.println("Is it a record: " + isRecord);
        if(isRecord){
            System.out.println("Its components");
            RecordComponent[] components = clazz.getRecordComponents();
            for( RecordComponent comp : components)
                System.out.println(comp.getType() + " " + comp.getName());
        }
    }
}
