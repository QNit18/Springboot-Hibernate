package com.anony18.aopDemo.service;

import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class TrafficeFortuneServiceImpl implements TrafficFortuneService{
    @Override
    public String getFortune() {
        // simulate a delay
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // return a fortune
        return "Expect heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean tripWiere) {
        if(tripWiere){
            throw new RuntimeException("Major accident! Highway is closed!");
        }
        return getFortune();
    }
}
