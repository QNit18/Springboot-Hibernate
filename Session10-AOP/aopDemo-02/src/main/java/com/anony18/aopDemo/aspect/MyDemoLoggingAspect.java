package com.anony18.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // Point cut
    @Pointcut("execution(* com.anony18.aopDemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Before("forDaoPackage()")
    public void performApiAnalystics(){
        System.out.println("==> Executing performApiAnalystics() <==");
    }

    // create a pointcut for getter methods
    @Pointcut("execution(* com.anony18.aopDemo.dao.*.get*(..))")
    private void getter(){}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.anony18.aopDemo.dao.*.set*(..))")
    private void setter(){}

    // create pointcut : include package ... exclude getter/ setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter(){}



    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(){
        System.out.println("\n==> Executing beforeAddAccountAdvice() --- TO pointcut Declaration<==");
    }

}
