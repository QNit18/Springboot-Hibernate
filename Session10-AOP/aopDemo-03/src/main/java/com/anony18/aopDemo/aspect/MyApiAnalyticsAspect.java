package com.anony18.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-2)
public class MyApiAnalyticsAspect {
    @Before("com.anony18.aopDemo.aspect.AnonyAopExpressions.forDaoPackage()")
    public void performApiAnalystics(){
        System.out.println("==> Executing performApiAnalystics() <==");
    }
}
