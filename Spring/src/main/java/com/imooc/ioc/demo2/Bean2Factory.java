package com.imooc.ioc.demo2;

/**
 * Bean2的静态工厂
 */
public class Bean2Factory {
    //提供一个静态的方法并返回一个Bean2的实例
    public static Bean2 createBean2(){
        System.out.println("Bean2Factory方法已经执行了");
        return new Bean2();
    }
}
