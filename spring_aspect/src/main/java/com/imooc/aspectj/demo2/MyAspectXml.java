package com.imooc.aspectj.demo2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspectXml {

    //前置通知
    public void before(JoinPoint joinPoint){
        System.out.println("前置通知==============》"+joinPoint);
    }

    //后置通知
    public void afterReturing(Object result){
        System.out.println("XML方式配置的后置通知=========。"+result);
    }

    //环绕通知
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("XML环绕前通知===========");
        Object obj = joinPoint.proceed();
        System.out.println("XML环绕后通知===========");

    }

    //异常抛出通知
    public void afterThrowing(Throwable e){
        System.out.println("XML方式的异常抛出通知============"+e.getMessage());
    }

    //最终通知
    public void after(){
        System.out.println("XML方式的最终通知=======");
    }

}
