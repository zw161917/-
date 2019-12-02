package com.imooc.aop.demo1;
import org.junit.Test;
public class SpringDemo1 {
    /**
     * JDK动态代理
     * 只能对带有接口的类实现动态代理
     */
    @Test
    public void demo1(){

        UserDao userDao = new UserDaoImpl();

        UserDao proxy = (UserDao) new MyJdkProxy(userDao).createProxy();

        proxy.save();
        proxy.uadate();
        proxy.delete();
        proxy.find();

    }





}
