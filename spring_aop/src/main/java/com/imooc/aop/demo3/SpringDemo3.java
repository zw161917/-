package com.imooc.aop.demo3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDemo3 {

//    @Resource(name="studentDaoImpl")
    @Resource(name = "studentDaoProxy")
    private StudentDao studentDao;

    /**
     * 传统aop：一般的切面
     * 对目标类里的所有方法都进行增强
     */
    @Test
    public void demo1(){
        studentDao.find();
        studentDao.save();
        studentDao.delete();
        studentDao.update();

    }



}
