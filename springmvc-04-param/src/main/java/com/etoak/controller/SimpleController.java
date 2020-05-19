package com.etoak.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.regexp.internal.recompile;

/**
 * 用于测试简单的参数传递
 * 使用HttpServletRequest接收参数
 * 使用基本数据类型 String接收参数
 * **/
@Controller
@RequestMapping("/simple")
public class SimpleController {
	/**
	 * 第一种接收参数的方式使用HttpServletRequest
	 * 第一种向request传值方式 也是 HttpServletRequest
	 * **/
	@RequestMapping("/servlet")
	public String servlet(HttpServletRequest request){
		//接收前端传入得参数
		String name = request.getParameter("name");
		
		if(StringUtils.isEmpty(name)){
			name = "World";
		}
		//使用request对象向request域传值
		request.setAttribute("result","Hello" + name);
		//webapp/pages/param.jsp
		return "param";
		
	}

	@RequestMapping(value = "/simple",method = {RequestMethod.GET})
	public ModelAndView simple(
			@RequestParam(value="a",required = false,defaultValue = "1") int id,
			String name){
		System.out.println("id param - " + id);
		System.out.println("name param - " + name);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result","ModelAndView传值");
		mv.setViewName("param");
		return mv;

	}

}













