package com.etoak.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etoak.bean.User;
import com.etoak.exception.Et1911LoginException;
import com.etoak.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/getById")
	@ResponseBody
	public User getById(int id){
		return userService.getById(id);
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		
		//销毁session
		request.getSession().invalidate();
		//退出之后重定向到登录页面
		return "redirect:/user/toLogin";
		
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String name,
			@RequestParam String password,
			@RequestParam String code,
			HttpServletRequest request){
		//取出session中的验证码 和请求参数中的验证码进行比对
		HttpSession session = request.getSession();
		Object sessionCode = session.getAttribute("code");
		if(!code.equals(sessionCode)){
			throw new Et1911LoginException("验证码不正确");
		}
		//验证用户
		password = DigestUtils.md5Hex(password);
		User user = userService.getByNameAndPassword(name, password);
		if(ObjectUtils.isEmpty(user)){
			throw new Et1911LoginException("用户名或密码不正确");

		}
		//销毁之前的session
		session.invalidate();
		session = request.getSession();
		//将用户密码设置为null 重新获取session
		user.setPassword(null);
		session.setAttribute("user", user);
		//登陆成功 跳到index页面
		return "redirect:/";
	}
	
}







