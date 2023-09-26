package com.anony18.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(12)
public class MyCloudLogAspect {
    @Before("com.anony18.aopDemo.aspect.AnonyAopExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloudAsync(){
        System.out.println("==> Executing logToCloudAsync()<==");
    }
}
