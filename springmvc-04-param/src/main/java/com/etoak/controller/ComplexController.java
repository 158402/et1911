package com.etoak.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etoak.bean.Student;

import sun.net.www.content.audio.x_aiff;

/**
 * 测试java bean接收参数
 * 测试数组接收参数
 * 测试list接收参数
 * 测试Map接收参数
 * **/
@Controller
@RequestMapping("/complex")
public class ComplexController {

	//Get请求:@RequestMapping的method属性指定
	//Get请求:@GetMapping
	@GetMapping("/bean")
	public String bean(Student student,Model model){
		
		System.out.println(student);
		model.addAttribute("result2","使用Model传值");
		//请求转发到/simple/simple(这个请求在SImple中)
		return "forward:/simple/simple?id=321";
		
	}
	@PostMapping("/array")//ModelMap:作用向前端传值
	public String array(String[] hobby,ModelMap modelMap){
		for(String hobbyStr : hobby){
			System.out.println("hobby - "+ hobbyStr);
		}
		modelMap.addAttribute("result","使用ModelMap传参");
		return "param";
	}
	/**
	 * 第五种接收参数的方式 
	 * 
	 * **/
	@PostMapping("/list")//ModelMap:作用向前端传值
	public String list(Student student, Map<String,Object> map){
		List<String> hobbyList = student.getHobbyList();
		//当它不为空的时候遍历
	 	if(!CollectionUtils.isEmpty(hobbyList)){
			hobbyList.forEach(x -> System.out.println(x));
		}
		map.put("result","使用List传参");
		return "param";
	}
	/*
	 * 第六种接收参数防晒 ： 使用map封装到java bean中
	 * **/
	@RequestMapping(value="/map",produces = {"text/plain"})
	@ResponseBody //可以返回json xml 纯文本
	public String map(Student student){
		System.out.println(student.getStuMap());
		
		return "success";
	}
}













