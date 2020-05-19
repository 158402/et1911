package com.etoak.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {

	@Override
	public ModelAndView handleRequest
	(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取参数
		String name = request.getParameter("name");
		
		//调用service服务层
		
		//向request域中赋值
		if(StringUtils.isEmpty(name)){
			name = "World";
		}		
		
		//返回页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		//向request域传值 相当于 request.setAttribute(k,v)
		mv.addObject("result","hello"+name);
		return mv;
	}

}
