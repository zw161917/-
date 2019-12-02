package com.imooc.aspectj.demo1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面类
 */
@Aspect
public class MyAspectAnno {

    //通过传入JoinPoint对象，用来获得切入点信息
//    @Before(value = "execution(* com.imooc.aspectj.demo1.ProductDao.save(..))")
    @Before(value = "myPointcut1()")
    public void before(JoinPoint joinPoint){
        System.out.println("前置通知=============================="+joinPoint);
    }

    //后置通知 returning = "result"属性可获得返回值
    @AfterReturning(value = "execution(* com.imooc.aspectj.demo1.ProductDao.update(..))",returning = "result")
    public void afterReturing(Object result){
        System.out.println("后置通知====================="+result);
    }

    //环绕通知
    @Around(value = "execution(* com.imooc.aspectj.demo1.ProductDao.delete(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕前通知===================");
        Object obj = joinPoint.proceed();//执行目标方法
        System.out.println("环绕后通知===================");

        return obj;


    }

    //异常抛出通知
    @AfterThrowing(value = "execution(* com.imooc.aspectj.demo1.ProductDao.findOne(..))",throwing = "e")
    public void afterThrowing(Throwable e){

        System.out.println("异常抛出通知====================="+e.getMessage());

    }

    //最终通知（无论是否有异常都会进行通知）
    @After(value = "execution(* com.imooc.aspectj.demo1.ProductDao.findAll(..))")
    public void  after(){

        System.out.println("最终通知=================");

    }

    //切点命名
    @Pointcut(value = "execution(* com.imooc.aspectj.demo1.ProductDao.save(..))")
    private void myPointcut1(){

    }

}
