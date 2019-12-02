package com.imooc.ioc.demo4;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo4 {
    /**
     * 构造方法的注入
     */
    @Test
    public void demo1(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User)applicationContext.getBean("user");
        System.out.println(user);

    }

    /**
     * set方法的属性注入
     */
    @Test
    public void demo2(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

    }

    /**
     * p名称空间的属性注入
     * 为了简化xml文件配置
     */
    @Test
    public void demo3(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

    }

    /**
     * Spring的属性注入--SpEL注入
     */
    @Test
    public void demo4(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Product product = (Product) applicationContext.getBean("product");
        System.out.println(product);

    }


}
