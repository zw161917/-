package com.imooc.demo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Spring的Bean管理的注解方式：
 * 传统方式需要去XML中配置<bean id="" class=""></bean>
 *
 * 注解方式
 * 1.@Component() 描述Spring框架中的Bean
 * 2.@Repository()  用于对Dao实现类进行标注
 * 3.@Service()  用于对Service实现类进行标注
 * 4.@Controller()  用于对Controller实现类进行标注
 */

@Component("userService")

public class UserService {
    @Value("米饭")
    private String something;

    /**@Autowired
    @Qualifier("userDao")
    */
    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * @param name
     * @return
     */

    public String sayHello(String name){
        return "Hello"+name;
    }

    public void eat(){
        System.out.println("eat"+something);
    }

    public void save(){
        System.out.println("Server中保存方法");
        userDao.save();
    }

}
