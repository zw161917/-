package com.imooc.demo2;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("bean1")
public class Bean1 {

    @PostConstruct
    public void init(){
        System.out.println("12init初始化bean。。。");
    }

    public void say(){
        System.out.println("say...");
    }

    @PreDestroy
    public void destory(){
        System.out.println("destory销毁bean。。。");
    }

}
