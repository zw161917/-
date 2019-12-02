package com.imooc.ioc.demo1;


import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class SpringDemo1 {

    @Test
    public void demo1(){
        UserService userService = new UserServiceImpl();
        userService.sayHello();

        //设置属性：
        UserServiceImpl userService2 = new UserServiceImpl();
        userService2.setName("张三");
        userService2.sayHello();
    }

    @Test
    /**
     * Spring的方法实现
     */
    public void demo2(){
        //创建工厂
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //通工厂获得类
        UserService userService = (UserService)applicationContext.getBean("userService");

        userService.sayHello();

    }

    @Test
    /**
     * 读取磁盘系统中的配置文件
     */
    public void demo3(){
        //创建Spring的工厂类
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("f:\\applicationContext.xml");
        UserService userService = (UserService)applicationContext.getBean("userService");

        userService.sayHello();
    }

    @Test
    /**
     * 传统方式的工厂类：BeanFactory
     */
    public void dem04(){
        //创建工厂类
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        //读取本地磁盘
        BeanFactory beanFactorys = new XmlBeanFactory(new FileSystemResource("f:\\applicationContext.xml"));
        //通过工厂类获得类
        UserService userService = (UserService)beanFactorys.getBean("userService");
        userService.sayHello();

    }

}
