package com.etoak.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
public class HelloController {
	/**
	 * 
	 * **/
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		//获取前端请求参数
		String name = request.getParameter("name");
		System.out.println("param name - " + name);
		
		if(StringUtils.isEmpty(name)){
			name = "World";
		}
		
		//向request域传值
		request.setAttribute("result","Hello " + name);
		//自动拼装 prefix +hello/index
		return "hello/index";
		
		
	}
}
