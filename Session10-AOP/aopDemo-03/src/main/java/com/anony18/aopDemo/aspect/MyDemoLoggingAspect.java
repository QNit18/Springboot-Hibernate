package com.anony18.aopDemo.aspect;

import com.anony18.aopDemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component@Order(-100)
public class MyDemoLoggingAspect {

    // add a new method for @AroundAfter on the findAccounts method
    @Around("execution(* com.anony18.aopDemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toLongString();
        System.out.println("==> Executing @Around on method: " + method);
        // get begin timestamp
        long begin = System.currentTimeMillis();
        // now, let's execute the method
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed();
        }catch (Exception exc){
            System.out.println(exc.getMessage());
            // give user a custom message
            result = "Major accident! But no worries, your private AOP helicopter is no the way! ";
        }
        // get end timestamp
        long end =  System.currentTimeMillis();
        // compute duration and display it
        long duration = end - begin;
        System.out.println("===> Duration: " + duration /1000.0 + " seconds!");
        return result;
    }

    // add a new methods for @After on the findAccounts method
    @After("execution(* com.anony18.aopDemo.dao.AccountDAO.findAccount(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint joinPoint){
        // print which method we are advising on
        String method = joinPoint.getSignature().toLongString();
        System.out.println("==> Executing @After on method: " + method);

    }

    // add a new methods for @AfterThrowing on the findAccounts method
    @AfterThrowing(
            pointcut = "execution(* com.anony18.aopDemo.dao.AccountDAO.findAccount(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable theExc){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toLongString();
        System.out.println("\n==> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("===> The exception: "+ theExc);
    }

    // add a new methods for @AfterReturing on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.anony18.aopDemo.dao.AccountDAO.findAccount(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toLongString();
        System.out.println("\n==> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n==> Result is: " + result);
        // let's post-process the data ... let's modify it :-)

        // convert the account names to uppercase
        convertAccountNameToUpperCase(result);

        System.out.println("\n==> Result is: " + result);

    }

    private void convertAccountNameToUpperCase(List<Account> result) {
        // loop through accounts
        for (Account account : result){
            // get upgrade version of name
            String theUpperCase = account.getName().toUpperCase();
            // update the name on the account
            account.setName(theUpperCase);
        }


    }

    @Before("com.anony18.aopDemo.aspect.AnonyAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("==> Executing beforeAddAccountAdvice() --- TO pointcut Declaration<==");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments
        // get args
        Object[] args = theJoinPoint.getArgs();
        // loop to show all args
        for (Object tempArg:args){
            System.out.println(tempArg);
            if(tempArg instanceof Account){
                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;
                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account levle: " + theAccount.getLevel());
            }
        }
    }
}
