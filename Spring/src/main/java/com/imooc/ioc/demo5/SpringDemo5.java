package com.imooc.ioc.demo5;

import com.imooc.ioc.demo4.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo5 {

    @Test
    public void demo1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionBen collectionBen = (CollectionBen) applicationContext.getBean("collectionBen");
        System.out.println(collectionBen);
    }

}
