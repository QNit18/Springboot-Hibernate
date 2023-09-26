package com.anony18.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // this is where we add all of our related advices for logging

    // let's start with a @Before advice
    //@Before("execution(public void addAccount())")
    //@Before("execution(* addAccount())")
    //@Before("execution(* add*())")
    //@Before("execution(* add*(com.anony18.aopDemo.Account))")
    //@Before("execution(* add*(..))")

    @Before("execution(* com.anony18.aopDemo.dao.*.*(..))")
    public void beforeAddAccountAdvice(){
        System.out.println("\n====> Executing @Before advice on addAccount() <====");
    }

}
