package com.luv2code.springboot.thymeleafdemo.aspect;

import com.mysql.cj.log.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declaration
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    // do the same for service and dao
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}


    // before Adivce
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        // display method we are advising
        String theMethod = joinPoint.getSignature().toLongString();
        myLogger.info("===> in @Before: " + theMethod);

        // display the arguments to the method

        // get the arguments
        Object[] args = joinPoint.getArgs();
        // loop thru and display args
        for (Object arg : args){
            myLogger.info("===> Argument: " +arg);
        }

    }


}
