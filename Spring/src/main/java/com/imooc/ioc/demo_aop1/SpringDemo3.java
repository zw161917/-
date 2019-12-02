package com.imooc.ioc.demo_aop1;

import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;

@RunWith(SpringJUnit4)
@ContextConfiguration("classpath:appAopContext.xml")
public class SpringDemo3 {

    @Resource(name="studentDaoImpl")
//    @Resource(name = "myBeforeAdvice")
    private StudentDao studentDao;

    @Test
    public void demo1(){
        studentDao.find();
        studentDao.save();
        studentDao.delete();
        studentDao.update();

    }



}
