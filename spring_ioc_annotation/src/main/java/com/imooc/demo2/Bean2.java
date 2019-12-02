package com.imooc.demo2;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 可通过@Scope（）来实现多例
 */

@Component("bean2")
@Scope("prototype")
public class Bean2 {
}
