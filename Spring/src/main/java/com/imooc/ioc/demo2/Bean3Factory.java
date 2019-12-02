package com.imooc.ioc.demo2;

/**
 * Bean3的实例工厂
 */
public class Bean3Factory {
    //和静态工厂的区别是有没有静态方法
    public Bean3 createBeab3(){
        System.out.println("Bean3Factory执行了");
        return new Bean3();
    }
}
