package com.imooc.ioc.demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring容器中Bean的生命周期
 * 1.instantiate bean对象实例化
 * 2.populate properties封装属性
 * 3.如果Bean实现BeanNameAware执行setBenaName（获得Spring在配置中的名称）
 * 4.如果Bean实现BeanFactoryAware 或者 ApplicationContextAware设置工厂setBeanFactory 或者上下文对象setApplicationContext（了解工厂）
 * 5.如果存在类实现BeanPostProcessor（后处理Bean），执行postProcessBeforeInitialization
 * 6.如果Bean实现InitializingBean执行afterPropertierSet
 * 7.执行init-method="init"所指定的初始化方法init
 * 8.如果存在类实现BeanPostProcessor（后处理Bean），执行postProcessAfterInitialization
 * 9.执行类本身的方法
 * 10.如果Bean实现DisprsableBean 执行destroy销毁方法
 * 11.执行init-method="init"所指定的初始化方法init
 */
public class Man implements BeanNameAware, ApplicationContextAware ,InitializingBean, DisposableBean {
    private String name;

    public void setName(String name) {
        System.out.println("第二步：设置属性");
        this.name = name;
    }

    public  Man(){
        System.out.println("第一步：Man被实例化");
    }

    public void setup(){
        System.out.println("第七步：调用Man被初始化方法");
    }

    public void teardown(){
        System.out.println("第十一步：执行Man被销毁方法");
    }

    @Override
    public void setBeanName(String namr){
        System.out.println("第三步：设置Bean的名称"+namr);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        System.out.println("第四步：了解工厂信息");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第六步：属性设置后");
    }

    public void run(){
        System.out.println("第九步：执行自身的方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("第十步：执行Spring的销毁方法");
    }
}
