package com.anony18.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class AnonyAopExpressions {
    // Point cut
    @Pointcut("execution(* com.anony18.aopDemo.dao.*.*(..))")
    public void forDaoPackage(){}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.anony18.aopDemo.dao.*.get*(..))")
    public void getter(){}
    // create a pointcut for setter methods
    @Pointcut("execution(* com.anony18.aopDemo.dao.*.set*(..))")
    public void setter(){}
    // create pointcut : include package ... exclude getter/ setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter(){}
}
