package com.imooc.aop.demo2;

import org.junit.Test;

public class SpringDemo2 {

    /**
     * CGLIB生成代理
     * 底层的字节码增强技术
     * 可以为类动态的去增加一些方法，也可以生成一个类去继承一个类
     */
    @Test
    public void demo1(){
        //目标类
        ProductDao productDao = new ProductDao();
        //生成代理类
        ProductDao proxy = (ProductDao) new MyCglibProxy(productDao).createProxy();

        proxy.save();
        proxy.delete();
        proxy.uadate();
        proxy.find();

    }

    /**
     * 代理知识总结
     * 应优先对接口创建代理，便于程序解耦维护
     * JDK动态代理，接口中不能使用final修饰
     * CGLIB是针对目标类生产子类，因此类和方法，不能使用final修饰
     */

}
