package com.etoak.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.regexp.internal.recompile;

import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;

//全局异常处理器
//注解@ControllerAdvice会在所有被@Controller注解的方法上加一个拦截器 
//当控制器的方法有一场抛出时会被这个异常处理器拦截
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Et1911LoginException.class)
	public ModelAndView handlLoginException(Et1911LoginException e){
		String msg= e.getMessage();
		log.error(msg,e);
		ModelAndView mv = new ModelAndView();
		mv.addObject("error",msg);
		mv.setViewName("login");
		return mv;
	}
	
	
	@ExceptionHandler(ParamException.class)
	/**
	 * 表示这个方法只拦截ParanException异常
	 * **/
	public ModelAndView handleParanException(ParamException e){
		//异常信息
		String message = e.getMessage();
		log.error(message,e);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("paramError",message);
		//页面：/templates/car/add.html
		mv.setViewName("car/add");
		return mv;
	}

}
