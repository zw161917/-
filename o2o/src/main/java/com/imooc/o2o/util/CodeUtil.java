package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;

//获取验证码进行判读是否符合
public class CodeUtil {
	
	public static boolean checkVerifyCode(HttpServletRequest request) {
		 String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
	        System.out.println("生成的验证码为"+verifyCodeExpected);
	        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
	        System.out.println("输入的验证码为"+verifyCodeActual);
	        if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected) ) {
	        	
	            return false;
	        } else {
	            return true;
	        }
	}

}
